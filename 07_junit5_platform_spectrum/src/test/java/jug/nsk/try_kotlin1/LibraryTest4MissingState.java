package jug.nsk.try_kotlin1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LibraryTest4MissingState {
    private int a = 1;

    @Before
    public void setUp() {
        System.out.println("setUp");
        a = a + 1;
    }

    @Test
    public void testSomeLibraryMethod1() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
        System.out.println("a = " + a);
    }

    @Test
    public void testSomeLibraryMethod2() {
        System.out.println("a = " + a);
    }

    @After
    public void tearDown() {
        System.out.println("tearDown");
    }

}
