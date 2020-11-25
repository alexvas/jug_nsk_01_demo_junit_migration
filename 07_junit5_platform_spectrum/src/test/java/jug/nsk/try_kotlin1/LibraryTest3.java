package jug.nsk.try_kotlin1;

import junit.framework.TestCase;

public class LibraryTest3 extends TestCase {
    private int a = 1;

    @Override
    public void setUp() {
        System.out.println("setUp, a = " + a);
        a = a + 1;
    }

    public void testSomeLibraryMethod() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
        System.out.println("method1: a = " + a);
    }

    public void testSomeLibraryMethod2() {
        System.out.println("method2: a = " + a);
    }

    @Override
    public void tearDown() {
        System.out.println("tearDown, a = " + a);
    }

}
