package jug.nsk.try_kotlin1

import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class LibraryTest {

    @Test
    fun testSomeLibraryMethod() {
        // given
        val classUnderTest = Library()
        // when
        val result = classUnderTest.someLibraryMethod()
        // then
        assertTrue("just 'true'", result)
    }

    companion object {

        @BeforeClass
        fun setUp() {
            println("setUp")
        }

        @AfterClass
        fun tearDown() {
            println("tearDown")
        }
    }

}
