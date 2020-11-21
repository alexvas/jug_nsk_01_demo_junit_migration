package jug.nsk.try_kotlin1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibraryTest4 {
    private static int a = 1;

    @BeforeClass
    public static void setUp() {
        System.out.println("setUp");
        a = 2;
    }

    @Test public void testSomeLibraryMethod() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
        assertEquals("a initialized to 2 in setUp", 2, a);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown");
        a = 3;
    }

}
