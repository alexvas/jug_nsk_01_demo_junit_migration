package jug.nsk.try_kotlin1


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.function.Supplier

class RunBlockingDeposit(farm: Supplier<Int>, private val chest: Chest, private val plannedAmount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
        deposits = distributeInEqualShares(plannedAmount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    @Throws(DropOut::class)
    override fun saveHandfulOfGold() {
        runBlocking {
            deposits.forEach {
                launch(Dispatchers.Default) { it.saveHandfulOfGold() }
            }
        }
    }

    override fun farmed() = deposits.sumBy { it.farmed() }

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = RunBlockingDeposit(farm, chest, left)
    }
}
