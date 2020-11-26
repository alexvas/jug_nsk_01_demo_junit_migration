package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

// Java, JUnit5
class PositivistTest5 {

  @Test
  void zeroInput() {
    // given
    Positivist positivist = new Positivist();
    // when
    Executable lambda = () -> positivist.gimmePositive(0);
    // then
    assertThrows(
        IllegalArgumentException.class,
        lambda,
        "zero input would cause an exception thrown"
    );
  }

}
