package jug.nsk.try_kotlin1


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.function.Supplier

class StructuredConcurrencyDeposit(farm: Supplier<Int>, private val chest: Chest, private val amount: Int) {

    private val deposits: List<Deposit>

    init {
        require(amount > 0) { "amount must be positive: $amount" }
        deposits = distributeInEqualShares(amount, PROC_NUM).map {
            AsyncDeposit(farm, chest, it)
        }
    }

    @Throws(DropOut::class)
    fun saveHandfulOfGold(cs: CoroutineScope) {
        deposits.forEach {
            cs.launch { it.saveHandfulOfGold() }
        }
    }

    fun farmed() = deposits.sumBy { it.farmed() }
}


class StructuredConcurrencyVault(
        private val farm: Supplier<Int>,
        private val chestFactory:Supplier<Chest>,
        initialChests: List<Chest>
) {
    private val chests = initialChests.toMutableList()

    suspend fun saveHandfulOfGold(amount: Int) {
        var left = amount
        while (left > 0) {
            val deposit = StructuredConcurrencyDeposit(farm, chests.last(), left)
            try {
                withContext(Dispatchers.Default) {
                    deposit.saveHandfulOfGold(this)
                }
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                chests += newChest
                newChest.deepSave(e)
            } finally {
                left -= deposit.farmed()
            }
        }
    }

    fun count() = chests.sumBy { it.count() }
}
