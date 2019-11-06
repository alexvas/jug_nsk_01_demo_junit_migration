@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClientTest {

    @Test
    fun `Алекс вызывает Юстаса!`() {
        val client = Client()
        val result = runBlocking {
            client.call()
        }
        assertEquals(result, "done")
    }

}
