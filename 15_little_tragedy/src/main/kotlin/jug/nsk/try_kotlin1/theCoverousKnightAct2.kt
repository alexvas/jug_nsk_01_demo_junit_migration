package jug.nsk.try_kotlin1

import java.util.function.Supplier

interface Deposit {
    fun saveHandfulOfGold()

    fun left(): Int

    interface Factory {
        fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit
    }
}

class SimpleDeposit(private val farm: Supplier<Int>, private val chest: Chest, private var left: Int): Deposit {
    init {
        require(left > 0) { "Negative amount: $left" }
    }

    override fun saveHandfulOfGold() {
        while (left --> 0) saveACoin()
    }

    private fun saveACoin() = chest.put(farm.get())

    override fun left() = left

    companion object Companion : Deposit.Factory {
        override fun create(farm: Supplier<Int>, chest: Chest, left: Int): Deposit = SimpleDeposit(farm, chest, left)
    }
}

class EnhancedVault(
        private val farm: Supplier<Int>,
        private val chestFactory:Supplier<Chest>,
        private val depositFactory: Deposit.Factory,
        vararg initialChests: Chest
): Vault {
    private val chests = mutableListOf(*initialChests)

    override fun saveHandfulOfGold(amount: Int) {
        var left = amount
        while (left > 0) {
            val deposit = depositFactory.create(farm, chests.last(), left)
            try {
                deposit.saveHandfulOfGold()
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                newChest.put(e.coin)
                chests += newChest
            } finally {
                left = deposit.left()
            }
        }
    }

    override fun count() = chests.sumBy { it.count() }
}
