package jug.nsk.try_kotlin1


import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier
import kotlin.math.min

private val PROC_NUM = Runtime.getRuntime().availableProcessors()
private val pool = Executors.newFixedThreadPool(PROC_NUM)

class AsyncChest(count: Int = 0, private val size: Int = 100) : Chest {

    private val count = AtomicInteger(count)

    private val content: IntArray = IntArray(size)

    override fun put(coin: Int) = try {
        content[count.getAndIncrement()] = coin
    } catch (e: IndexOutOfBoundsException) {
        throw DropOut(coin)
    }

    override fun count() = min(count.get(), size)

    companion object Companion : Supplier<Chest> {
        override fun get() = AsyncChest()
    }
}

class ExecutorDeposit(farm: Supplier<Int>, chest: Chest, amount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount).map {
            SimpleDeposit(farm, chest, it)
        }
    }

    override fun saveHandfulOfGold() {
        val futures = deposits.map {
            pool.submit { it.saveHandfulOfGold() }
        }

        futures.map {
            try {
                it.get()
            } catch (e: ExecutionException) {
                throw e.cause!!
            }
        }
    }

    override fun left() = deposits.sumBy { it.left() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = ExecutorDeposit(farm, chest, left)
    }
}

private fun distributeInEqualShares(amount: Int): MutableList<Int> {
    val base = amount / PROC_NUM
    val mod = amount % PROC_NUM
    val size = when (base) {
        0 -> mod
        else -> PROC_NUM
    }
    val amounts = MutableList(size) { base }
    repeat(mod) {
        amounts[it] = amounts[it] + 1
    }
    return amounts
}
