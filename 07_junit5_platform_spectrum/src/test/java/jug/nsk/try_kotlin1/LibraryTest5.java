package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
class LibraryTest5 {

    private int a = 1;

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
        a = a + 1;
    }

    @Test
    void testSomeLibraryMethod() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
        System.out.println("a = " + a);
    }

    @Test
    void testSomeLibraryMethod2() {
        System.out.println("a = " + a);
    }

    @AfterAll
    void tearDown() {
        System.out.println("tearDown");
        assertEquals("a was incremented twice", 3, a);
    }

}
