@file:Suppress("NonAsciiCharacters")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


fun initialAsyncChests(): Array<Chest> = arrayOf(
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold(87))
)


class Act3Test {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
        val farm = CoinFarm()

        val vault: Vault = AsyncVault(farm, AsyncChest, MultithreadingDeposit, *initialAsyncChests())
        val startCount = vault.count()

        vault.saveHandfulOfGold(toAdd)

        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
