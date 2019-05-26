package jug.nsk.try_kotlin1;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(ShambalaRunner.class)
public class ShambalaTest {

  @Test
  public void testShambalaIsReal() {
    System.out.println("running testShambalaIsReal");
    assertTrue("Shambala is a real one", Shambala.getInstance().isAuthentic());
  }

  @Test
  public void testShambalaIsStillReal() {
    System.out.println("running testShambalaIsStillReal");
    assertTrue("Shambala is still a real one", Shambala.getInstance().isAuthentic());
  }

}
