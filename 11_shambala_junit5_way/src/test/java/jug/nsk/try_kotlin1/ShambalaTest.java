package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ShambalaExtension.class)
class ShambalaTest {

  @Test
  void testShambalaIsReal(Shambala shambala) {
    System.out.println("running testShambalaIsReal");
    assertTrue(shambala.isAuthentic(), "Shambala is a real one");
  }

  @Test
  void testShambalaIsStillReal(Shambala shambala) {
    System.out.println("running testShambalaIsStillReal");
    assertTrue(shambala.isAuthentic(), "Shambala is still a real one");
  }

}
