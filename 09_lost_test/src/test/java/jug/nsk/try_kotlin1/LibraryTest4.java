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
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
        assertEquals("a initialized to 2 in setUp", 2, a);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown");
        a = 3;
    }

}
