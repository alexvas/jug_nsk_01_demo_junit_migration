package jug.nsk.try_kotlin1;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

// Java, JUnit4
@RunWith(ShambalaRunner.class)
public class ShambalaTest {

  @Test
  public void testShambalaIsReal() {
    System.out.println("running testShambalaIsReal");
    // given
    Shambala shambala = Shambala.getInstance();
    // when
    boolean result = shambala.isAuthentic();
    // then
    assertTrue("Shambala is a real one", result);
  }

  @Test
  public void testShambalaIsStillReal() {
    System.out.println("running testShambalaIsStillReal");
    // given
    Shambala shambala = Shambala.getInstance();
    // when
    boolean result = shambala.isAuthentic();
    // then
    assertTrue("Shambala is still a real one", result);
  }

}
