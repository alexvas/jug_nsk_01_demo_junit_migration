package jug.nsk.try_kotlin1


import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier

internal val PROC_NUM = Runtime.getRuntime().availableProcessors()

class AsyncChest(gold: IntArray) : BaseChest(gold) {
    private val counter = AtomicInteger(gold.size)

    override fun getTryCount() = counter.get()

    override fun nextTryCount() = counter.getAndIncrement()

    companion object Companion : Supplier<Chest> {
        override fun get() = AsyncChest(IntArray(0))
    }
}

class AsyncDeposit(private val farm: Supplier<Int>, private val chest: Chest, private val plannedAmount: Int) : Deposit {
    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
    }

    private val farmed = AtomicInteger(0)

    @Throws(DropOut::class)
    override fun saveHandfulOfGold() {
        while (farmed.get() < plannedAmount) {
            val coin = farm.get()
            farmed.incrementAndGet()
            chest.put(coin)
        }
    }

    override fun farmed() = farmed.get()

    override fun toString(): String {
        return "A-Deposit(amount=$plannedAmount, farmed=${farmed.get()})"
    }

}

class MultithreadingDeposit(farm: Supplier<Int>, chest: Chest, plannedAmount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
        deposits = distributeInEqualShares(plannedAmount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    override fun saveHandfulOfGold() {
        val threads = deposits.map {
            Thread { it.saveHandfulOfGold() }
        }

        threads.forEach { it.start() }
        threads.forEach { it.join() }
    }

    override fun farmed() = deposits.sumBy { it.farmed() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = MultithreadingDeposit(farm, chest, left)
    }
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
