package jug.nsk.try_kotlin1;

import junit.framework.TestCase;

public class LibraryTest3 extends TestCase {
    private int a = 1;

    @Override
    public void setUp() {
        System.out.println("setUp");
        a = 2;
    }

    public void testSomeLibraryMethod() {
        // given
        Library classUnderTest = new Library();
        // when
        boolean result = classUnderTest.someLibraryMethod();
        // then
        assertTrue("just 'true'", result);
        assertEquals("a initialized to 2 in setUp", 2, a);
    }

    @Override
    public void tearDown() {
        System.out.println("tearDown");
        a = 3;
    }

}
