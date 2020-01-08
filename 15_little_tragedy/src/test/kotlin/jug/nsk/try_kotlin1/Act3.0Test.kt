@file:Suppress("NonAsciiCharacters")

package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.Supplier
import kotlin.random.Random


fun initialAsyncChests(): List<Chest> = listOf(
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold()),
        AsyncChest(gold(87))
)

class CoinFarm: Supplier<Int> {
    private val counter = AtomicInteger(0)

    override fun get(): Int {
        counter.incrementAndGet()
        return Random.nextInt()
    }

    fun count() = counter.get()
}

class Act3Test {
    @Test
    fun `проверяем, что всё сошлось`() {

        val toAdd = 100500
        val farm = CoinFarm()

        val vault: Vault = EnhancedVault(farm, AsyncChest, MultithreadingDeposit, initialAsyncChests())
        val startCount = vault.count()

        vault.saveHandfulOfGold(toAdd)

        assertEquals(toAdd, farm.count(), "как заказывали, так и сгенерировали")
        assertEquals(startCount + toAdd, vault.count(), "Ключи, ключи мои!...")
    }

}
