package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Java, JUnit5
@ExtendWith(ShambalaExtension.class)
class ShambalaTest {

  @Test
  void testShambalaIsReal(
      Shambala shambala // given
  ) {
    System.out.println("running testShambalaIsReal");
    // when
    boolean result = shambala.isAuthentic();
    // then
    assertTrue(result, "Shambala is a real one");
  }

  @Test
  void testShambalaIsStillReal(
      Shambala shambala // given
  ) {
    System.out.println("running testShambalaIsStillReal");
    // when
    boolean result = shambala.isAuthentic();
    // then
    assertTrue(result, "Shambala is still a real one");
  }

}
