package jug.nsk.try_kotlin1


import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier
import kotlin.math.min

internal val PROC_NUM = Runtime.getRuntime().availableProcessors()

class AsyncChest(content: IntArray) : Chest {
    private val content: IntArray = IntArray(CHEST_SIZE)
    private val count = AtomicInteger(content.size)

    init {
        require(content.size <= CHEST_SIZE)
        System.arraycopy(content, 0, this.content, 0, content.size)
    }

    override fun put(coin: Int) = try {
        content[count.getAndIncrement()] = coin
    } catch (e: IndexOutOfBoundsException) {
        throw DropOut(coin)
    }

    override fun count() = min(count.get(), CHEST_SIZE)

    companion object Companion : Supplier<Chest> {
        override fun get() = AsyncChest(IntArray(0))
    }
}

class ThreadDeposit(farm: Supplier<Int>, chest: Chest, amount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
            SimpleDeposit(farm, chest, it)
        }
    }

    override fun saveHandfulOfGold() {
        val threads = deposits.map {
            Thread { it.saveHandfulOfGold() }
        }

        threads.map { it.start() }
        threads.map { it.join() }
    }

    override fun left() = deposits.sumBy { it.left() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = ThreadDeposit(farm, chest, left)
    }
}

class AsyncVault(
        farm: Supplier<Int>,
        chestFactory: Supplier<Chest>,
        depositFactory: Deposit.Factory,
        vararg initialChests: Chest
) : EnhancedVault(farm, chestFactory, depositFactory, *initialChests) {

    override val chests: MutableList<Chest>
        get() = Collections.synchronizedList(super.chests)
}

internal fun distributeInEqualShares(amount: Int, procNum: Int): List<Int> {
    val base = amount / procNum
    val mod = amount % procNum
    val size = when (base) {
        0 -> mod
        else -> procNum
    }
    return List(size) { if (it < mod) base + 1 else base }
}
