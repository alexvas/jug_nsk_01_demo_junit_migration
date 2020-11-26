package jug.nsk.try_kotlin1;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

// java, JUnit4
public class LibraryTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("setUp");
    }

    @Test public void testSomeLibraryMethod() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown");
    }

}
