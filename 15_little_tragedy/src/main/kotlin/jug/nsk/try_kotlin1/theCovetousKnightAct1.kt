package jug.nsk.try_kotlin1

import java.lang.Integer.min
import java.util.function.Supplier

internal const val CHEST_SIZE: Int = 100

class DropOut(val coin: Int) : Exception("coin drop-out")

interface Chest {
    @Throws(DropOut::class)
    fun put(coin: Int)

    fun count(): Int
}

internal fun Chest.save(e: DropOut) = put(e.coin)

interface Vault {
    fun saveHandfulOfGold(amount: Int)

    fun count(): Int
}

abstract class BaseChest(gold: IntArray): Chest {
    private val content: IntArray = IntArray(CHEST_SIZE)

    init {
        require(gold.size <= CHEST_SIZE)
        System.arraycopy(gold, 0, content, 0, gold.size)
    }

    override fun put(coin: Int) = try {
        content[nextTryCount()] = coin
    } catch (e: IndexOutOfBoundsException) {
        throw DropOut(coin)
    }

    override fun count() = min(getTryCount(), CHEST_SIZE)

    internal abstract fun getTryCount(): Int

    internal abstract fun nextTryCount(): Int

    override fun toString(): String = "${this::class.java.simpleName}(count=${getTryCount()})"
}

class SimpleChest(gold: IntArray): BaseChest(gold) {
    private var counter = gold.size

    override fun getTryCount() = counter

    override fun nextTryCount() = counter++

    companion object Companion : Supplier<Chest> {
        override fun get() = SimpleChest(IntArray(0))
    }
}

class SimpleVault(private val farm: Supplier<Int>, private val chestFactory:Supplier<Chest>, initialChests: List<Chest>): Vault {
    private val chests = initialChests.toMutableList()

    override fun saveHandfulOfGold(amount: Int) {
        repeat(amount) {
            try {
                val coin = farm.get()
                chests.last().put(coin)
            } catch (e: DropOut) {
                val newChest = chestFactory.get()
                chests += newChest
                newChest.save(e)
            }
        }
    }

    override fun count() = chests.sumBy { it.count() }

}
