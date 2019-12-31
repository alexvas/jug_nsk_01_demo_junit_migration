@file:Suppress("NonAsciiCharacters")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


fun initialAsyncChests(): Array<Chest> = arrayOf(
        AsyncChest(100),
        AsyncChest(100),
        AsyncChest(100),
        AsyncChest(100),
        AsyncChest(100),
        AsyncChest(87)
)


class Act3Test {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
//        val toAdd = 10
        val farm = CoinFarm()

        val vault: Vault = EnhancedVault(farm, AsyncChest, ExecutorDeposit, *initialAsyncChests())
        val startCount = vault.count()

        vault.saveHandfulOfGold(toAdd)

//        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
