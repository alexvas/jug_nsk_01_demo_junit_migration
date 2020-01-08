package jug.nsk.try_kotlin1

import java.util.function.Supplier

interface Deposit {
    fun saveHandfulOfGold()

    fun farmed(): Int

    interface Factory {
        fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit
    }
}

class SimpleDeposit(private val farm: Supplier<Int>, private val chest: Chest, private val plannedAmount: Int): Deposit {
    init {
        require(plannedAmount > 0) { "amount must be positive: $plannedAmount" }
    }

    private var farmed = 0

    @Throws(DropOut::class)
    override fun saveHandfulOfGold() {
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

open class EnhancedVault(
        private val farm: Supplier<Int>,
        private val chestFactory:Supplier<Chest>,
        private val depositFactory: Deposit.Factory,
        vararg initialChests: Chest
): Vault {
    internal open val chests = mutableListOf(*initialChests)

    override fun saveHandfulOfGold(amount: Int) {
        var left = amount
        while (left > 0) {
            val deposit = depositFactory.create(farm, chests.last(), left)
            try {
                deposit.saveHandfulOfGold()
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

    override fun count() = chests.sumBy { it.count() }
}
