package jug.nsk.try_kotlin1

import java.util.function.Supplier

class DropOut(val coin: Int) : Exception("coin drop-out")

interface Chest {
    @Throws(DropOut::class)
    fun put(coin: Int)

    fun count(): Int
}

interface Vault {
    fun saveHandfulOfGold(amount: Int)

    fun count(): Int
}

class SimpleChest(private var count: Int = 0, size: Int = 100): Chest {
    private val content: IntArray = IntArray(size)

    override fun put(coin: Int) = try {
        content[count++] = coin
    } catch (e: IndexOutOfBoundsException) {
        count-- // на самом деле положить монету не удалось
        throw DropOut(coin)
    }

    override fun count() = count

    companion object Companion : Supplier<Chest> {
        override fun get() = SimpleChest()
    }
}

class SimpleVault(private val farm: Supplier<Int>, private val chestFactory:Supplier<Chest>, vararg initialChests: Chest): Vault {
    private val chests = mutableListOf(*initialChests)

    override fun saveHandfulOfGold(amount: Int) {
        repeat(amount) {
            try {
                saveACoin()
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                newChest.put(e.coin)
                chests += newChest
            }
        }
    }

    override fun count() = chests.sumBy { it.count() }

    private fun saveACoin() = chests.last().put(farm.get())

}
