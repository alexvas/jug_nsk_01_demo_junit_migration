package jug.nsk.try_kotlin1


import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.function.Supplier

private val pool = Executors.newFixedThreadPool(PROC_NUM)

class ExecutorDeposit(farm: Supplier<Int>, private val chest: Chest, private val plannedAmount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
        deposits = distributeInEqualShares(plannedAmount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    @Throws(DropOut::class)
    override fun saveHandfulOfGold() {
        val futures = deposits.map {
            pool.submit { it.saveHandfulOfGold() }
        }

        val thrown = mutableListOf<DropOut>()
        futures.forEach {
            try {
                it.get()
            } catch (e: ExecutionException) {
                when (val cause = e.cause!!) {
                    is DropOut -> thrown.add(cause)
                    else -> throw cause
                }
            }
        }
        if (thrown.isEmpty()) return

        val root = thrown.removeAt(0)
        thrown.forEach { root.addSuppressed(it) }
        throw root
    }

    override fun farmed() = deposits.sumBy { it.farmed() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = ExecutorDeposit(farm, chest, left)
    }
}
