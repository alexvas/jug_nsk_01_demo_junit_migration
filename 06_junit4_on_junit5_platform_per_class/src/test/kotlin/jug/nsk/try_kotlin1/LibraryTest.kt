package jug.nsk.try_kotlin1

import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass

class LibraryTest {

//    @Test
    fun testSomeLibraryMethod() {
        val classUnderTest = Library()
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod())
    }

    @BeforeClass
    fun setUp() {
        println("setUp")
    }

    @AfterClass
    fun tearDown() {
        println("tearDown")
    }

}
