/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jvm.library;

import junit.framework.TestCase;

public class LibraryTest3 extends TestCase {
    private int a = 1;

    public void setUp() {
        System.out.println("setUp");
        a = 2;
    }

    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
        assertEquals("a initialized to 2 in setUp", 2, a);
    }

    public void tearDown() {
        System.out.println("tearDown");
        a = 3;
    }

}
