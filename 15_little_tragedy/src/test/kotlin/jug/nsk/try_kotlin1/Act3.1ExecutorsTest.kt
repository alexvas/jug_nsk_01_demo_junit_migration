@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis


class Act3_1ExecutorsTest {
    @Test
    fun `проверяем, что всё сошлось`() {
        // given
        val toAdd = 100500
        val farm = CoinFarm()

        val vault: Vault = DeepVault(farm, AsyncChest, ExecutorDeposit, initialAsyncChests())
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
