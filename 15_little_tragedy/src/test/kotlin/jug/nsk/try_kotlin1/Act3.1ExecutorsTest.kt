@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class Act3_1ExecutorsTest {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
        val farm = CoinFarm()

        val vault: Vault = AsyncVault(farm, AsyncChest, ExecutorDeposit, *initialAsyncChests())
        val startCount = vault.count()

        vault.saveHandfulOfGold(toAdd)

        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}