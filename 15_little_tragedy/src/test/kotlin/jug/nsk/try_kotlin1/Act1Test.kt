@file:Suppress("NonAsciiCharacters")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier
import kotlin.random.Random

class CoinFarm: Supplier<Int> {
    private val counter = AtomicInteger(0)

    override fun get(): Int {
        counter.incrementAndGet()
        return Random.nextInt()
    }

    fun count() = counter.get()
}

internal fun gold(size: Int = CHEST_SIZE) = IntArray(size) { Random.nextInt() }

fun initialSimpleChests(): Array<Chest> = arrayOf(
        SimpleChest(gold()),
        SimpleChest(gold()),
        SimpleChest(gold()),
        SimpleChest(gold()),
        SimpleChest(gold()),
        SimpleChest(gold(87))
)

class Act1Test {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
        val farm = CoinFarm()

        val vault: Vault = SimpleVault(farm, SimpleChest, *initialSimpleChests())
        val startCount = vault.count()

        vault.saveHandfulOfGold(toAdd)

        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
