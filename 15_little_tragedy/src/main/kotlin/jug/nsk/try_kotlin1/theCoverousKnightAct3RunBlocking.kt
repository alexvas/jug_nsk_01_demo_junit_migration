package jug.nsk.try_kotlin1


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.function.Supplier

class RunBlockingDeposit(farm: Supplier<Int>, private val chest: Chest, private val amount: Int) : Deposit {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
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


class CoroutineVault(
        farm: Supplier<Int>,
        chestFactory: Supplier<Chest>,
        depositFactory: Deposit.Factory,
        vararg initialChests: Chest
) : AsyncVault(farm, chestFactory, depositFactory, *initialChests) {

    override fun saveDropOutInTheChest(e: DropOut, chest: Chest) {
        super.saveDropOutInTheChest(e, chest)
        e.suppressed.forEach {
            when (it) {
                is DropOut -> super.saveDropOutInTheChest(it, chest)
                else -> throw it
            }
        }
    }
}
