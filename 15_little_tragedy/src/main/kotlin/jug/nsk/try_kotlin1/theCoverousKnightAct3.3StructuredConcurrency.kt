package jug.nsk.try_kotlin1


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.function.Supplier

class StructuredConcurrencyDeposit(farm: Supplier<Int>, private val chest: Chest, private val amount: Int) {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "Negative amount: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    @Throws(DropOut::class)
    fun saveHandfulOfGold(cs: CoroutineScope) {
        deposits.forEach {
            cs.launch(Dispatchers.Default) { it.saveHandfulOfGold() }
        }
    }

    fun farmed() = deposits.sumBy { it.farmed() }
}


open class StructuredConcurrencyVault(
        private val farm: Supplier<Int>,
        private val chestFactory:Supplier<Chest>,
        vararg initialChests: Chest
) {
    internal open val chests = mutableListOf(*initialChests)

    suspend fun saveHandfulOfGold(amount: Int) {
        var left = amount
        while (left > 0) {
            val deposit = StructuredConcurrencyDeposit(farm, chests.last(), left)
            try {
                coroutineScope {
                    deposit.saveHandfulOfGold(this)
                }
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                saveDropOutInTheChest(e, newChest)
                chests += newChest
            } finally {
                left -= deposit.farmed()
            }
        }
    }

    internal open fun saveDropOutInTheChest(e: DropOut, chest: Chest) {
        putDropOutCoins(e, chest)
        e.suppressed.forEach {
            when (it) {
                is DropOut -> putDropOutCoins(it, chest)
                else -> throw it
            }
        }

    }

    private fun putDropOutCoins(e: DropOut, chest: Chest) {
        e.coins.forEach { coin -> chest.put(coin) }
    }

    fun count() = chests.sumBy { it.count() }
}
