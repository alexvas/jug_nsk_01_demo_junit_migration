package jug.nsk.try_kotlin1

import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test

class LibraryTest {

    @Test
    fun testSomeLibraryMethod() {
        val classUnderTest = Library()
        assertTrue("just 'true'", classUnderTest.someLibraryMethod())
    }

    companion object {

        @BeforeClass
        @JvmStatic
        fun setUp() {
            println("setUp")
        }

        @AfterClass
        @JvmStatic
        fun tearDown() {
            println("tearDown")
        }
    }

}
