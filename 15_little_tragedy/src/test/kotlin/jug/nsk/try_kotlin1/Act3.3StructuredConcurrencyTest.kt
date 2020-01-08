@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis


class Act3_3StructuredConcurrencyTest {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
        val farm = CoinFarm()

        val vault = StructuredConcurrencyVault(farm, AsyncChest, initialAsyncChests())
        val startCount = vault.count()

        runBlocking {
            val elapsed = measureTimeMillis {
                vault.saveHandfulOfGold(toAdd)
            }
            println("saved $toAdd coins in $elapsed milliseconds")
        }

        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
