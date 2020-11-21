package jug.nsk.try_kotlin1

import java.util.function.Supplier

interface Deposit {
    fun saveFistfulOfGold()

    fun farmed(): Int

    interface Factory {
        fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit
    }
}

class SimpleDeposit(private val farm: Supplier<Int>, private val chest: Chest, private val plannedAmount: Int) : Deposit {
    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
    }

    private var farmed = 0

    @Throws(DropOut::class)
    override fun saveFistfulOfGold() {
        while (farmed < plannedAmount) {
            val coin = farm.get()
            ++farmed
            chest.put(coin)
        }
    }

    override fun farmed() = farmed

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = SimpleDeposit(farm, chest, left)
    }
}

abstract class BaseVault(
        private val farm: Supplier<Int>,
        private val chestFactory: Supplier<Chest>,
        private val depositFactory: Deposit.Factory,
        initialChests: List<Chest>
) : Vault {
    private val chests = initialChests.toMutableList()

    override fun saveFistfulOfGold(amount: Int) {
        var left = amount
        while (left > 0) {
            val deposit = depositFactory.create(farm, chests.last(), left)
            try {
                deposit.saveFistfulOfGold()
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                chests += newChest
                saveDropOut(newChest, e)
            } finally {
                left -= deposit.farmed()
            }
        }
    }

    internal abstract fun saveDropOut(chest: Chest, e: DropOut)

    override fun count() = chests.sumBy { it.count() }
}


class EnhancedVault(
        farm: Supplier<Int>,
        chestFactory: Supplier<Chest>,
        depositFactory: Deposit.Factory,
        initialChests: List<Chest>
) : BaseVault(farm, chestFactory, depositFactory, initialChests) {

    override fun saveDropOut(chest: Chest, e: DropOut) = chest.save(e)
}
