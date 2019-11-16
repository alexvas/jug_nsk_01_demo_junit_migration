package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
class LibraryTest5 {

    private int a = 1;

    @BeforeAll
    void setUp() {
        System.out.println("setUp");
        a = 2;
    }

    @Test
    void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("just 'true'", classUnderTest.someLibraryMethod());
        assertEquals("a initialized to 2 in setUp", 2, a);
    }

    @AfterAll
    void tearDown() {
        System.out.println("tearDown");
        a = 3;
    }

}
