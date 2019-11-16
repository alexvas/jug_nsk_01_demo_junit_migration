package jug.nsk.try_kotlin1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("setUp");
    }

    @Test public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("just 'true'", classUnderTest.someLibraryMethod());
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown");
    }

}
