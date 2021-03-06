@file:Suppress("NonAsciiCharacters")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.function.Supplier
import kotlin.random.Random
import kotlin.system.measureTimeMillis

const val COIN_GENERATION_DELAY_IN_NANOS = 10

class SimpleCoinFarm: Supplier<Int> {
    private var counter = 0

    override fun get(): Int {
//        Thread.sleep(0, COIN_GENERATION_DELAY_IN_NANOS)
        counter++
        return Random.nextInt()
    }

    fun count() = counter
}

internal fun gold(size: Int = CHEST_SIZE) = IntArray(size) { Random.nextInt() }

fun initialSimpleChests(): List<Chest> = listOf(
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
        // given
        val toAdd = 100500
        val farm = SimpleCoinFarm()

        val vault: Vault = SimpleVault(farm, SimpleChest, initialSimpleChests())
        val startCount = vault.count()

        val elapsed = measureTimeMillis {
            // when
            vault.saveFistfulOfGold(toAdd)
        }
        println("saved $toAdd coins in $elapsed milliseconds")

        // then
        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
