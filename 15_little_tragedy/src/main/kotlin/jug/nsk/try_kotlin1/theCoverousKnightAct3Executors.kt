package jug.nsk.try_kotlin1


import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.function.Supplier

private val pool = Executors.newFixedThreadPool(PROC_NUM)

class ExecutorDeposit(farm: Supplier<Int>, private val chest: Chest, private val amount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    @Throws(DropOut::class)
    override fun saveHandfulOfGold() {
        val futures = deposits.map {
            pool.submit { it.saveHandfulOfGold() }
        }

        val lost = mutableListOf<Int>()
        futures.map {
            try {
                it.get()
            } catch (e: ExecutionException) {
                when (val c = e.cause!!) {
                    is DropOut -> lost.addAll(c.coins)
                    else -> throw c
                }
            }
        }
        if (lost.isNotEmpty()) throw DropOut(lost)
    }

    override fun farmed() = deposits.sumBy { it.farmed() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = ExecutorDeposit(farm, chest, left)
    }
}
