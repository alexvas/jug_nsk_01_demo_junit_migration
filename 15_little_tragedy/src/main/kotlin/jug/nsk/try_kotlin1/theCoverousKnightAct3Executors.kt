package jug.nsk.try_kotlin1


import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.function.Supplier

private val pool = Executors.newFixedThreadPool(PROC_NUM)

class ExecutorDeposit(farm: Supplier<Int>, chest: Chest, amount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
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
                // todo: суммировать вылетевшие монеты
                throw e.cause!!
            }
        }
    }

    override fun left() = deposits.sumBy { it.left() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = ExecutorDeposit(farm, chest, left)
    }
}
