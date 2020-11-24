@file:Suppress("NonAsciiCharacters", "ClassName")

package jug.nsk.try_kotlin1

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClientTest {

    @Test
    fun `Алекс вызывает Юстаса!`() {
        // given
        val client = Client()
        val result = runBlocking {
            // when
            client.call()
        }
        // then
        assertEquals(result, "done")
    }

}
