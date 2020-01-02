package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DistributeInEqualSharesTest {


    @Test
    fun `num = n_proc x k - 1`() {
        val result = distributeInEqualShares(4 * 3 - 1, 4)
        assertEquals(
                listOf(3, 3, 3, 3 - 1),
                result
        )
    }

    @Test
    fun `num = 1`() {
        val result = distributeInEqualShares(1, 4)
        assertEquals(
                listOf(1),
                result
        )
    }


}