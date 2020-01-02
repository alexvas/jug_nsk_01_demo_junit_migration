package jug.nsk.try_kotlin1

import java.lang.Integer.min
import java.util.function.Supplier

class DropOut(val coin: Int) : Exception("coin drop-out")

internal const val CHEST_SIZE: Int = 100

interface Chest {
    @Throws(DropOut::class)
    fun put(coin: Int)

    fun count(): Int
}

interface Vault {
    fun saveHandfulOfGold(amount: Int)

    fun count(): Int
}

class SimpleChest(content: IntArray): Chest {
    private val content: IntArray = IntArray(CHEST_SIZE)
    private var count: Int = content.size

    init {
        require(content.size <= CHEST_SIZE)
        System.arraycopy(content, 0, this.content, 0, content.size)
    }

    override fun put(coin: Int) = try {
        content[count++] = coin
    } catch (e: IndexOutOfBoundsException) {
        throw DropOut(coin)
    }

    override fun count() = min(count, CHEST_SIZE)

    companion object Companion : Supplier<Chest> {
        override fun get() = SimpleChest(IntArray(0))
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
