package jug.nsk.try_kotlin1


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.function.Supplier

class OptimalVault(
        private val farm: Supplier<Int>,
        private val chestFactory: Supplier<Chest>,
        initialChests: List<Chest>
) {
    private val chests = initialChests.toMutableList()

    suspend fun saveFistfulOfGold(amount: Int) = try {

        withContext(Dispatchers.Default) {
            val lastChest = chests.last()
            val leftInLastChest = CHEST_SIZE - lastChest.count()
            if (amount <= leftInLastChest) {
                // всё помещается в последний сундук, майним монетки параллельно
                repeat(amount) {
                    launch {
                        val coin = farm.get()
                        lastChest.put(coin)
                    }
                }
                return@withContext
            }
            // заполняем сундуки параллельно
            bgFill(lastChest, leftInLastChest)
            val amountLeft = amount - leftInLastChest
            repeat(amountLeft / CHEST_SIZE) {
                // эти сундуки надо заполнить доверху
                bgFill(newChest(), CHEST_SIZE)
            }
            val stillLeft = amountLeft % CHEST_SIZE
            if (stillLeft > 0) {
                newChest().fill(stillLeft)
            }
        }
    } catch (t: Throwable) {
        System.err.println("failed to save gold:")
        t.printStackTrace(System.err)
    }

    private fun newChest(): Chest {
        val newChest = chestFactory.get()
        chests.add(newChest)
        return newChest
    }

    private fun Chest.fill(amount: Int) = repeat(amount) {
        val coin = farm.get()
        put(coin)
    }

    private fun CoroutineScope.bgFill(chest: Chest, amount: Int) = launch { chest.fill(amount) }

    fun count() = chests.sumBy { it.count() }
}
