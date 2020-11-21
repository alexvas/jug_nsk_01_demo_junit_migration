package jug.nsk.try_kotlin1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DistributeInEqualSharesTest {


    @Test
    fun `num = n_proc x k - 1`() {
        // given
        val amount = 4 * 3 - 1
        val procNum = 4

        // when
        val result = distributeInEqualShares(amount, procNum)
        // then
        assertEquals(
                listOf(3, 3, 3, 3 - 1),
                result
        )
    }

    @Test
    fun `num = 1`() {
        // given
        val amount = 1
        val procNum = 4

        // when
        val result = distributeInEqualShares(amount, procNum)
        // then
        assertEquals(
                listOf(1),
                result
        )
    }


}