package jug.nsk.try_kotlin1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PositivistTest5 {

  @Test
  void zeroInput() {
    Positivist positivist = new Positivist();
    assertThrows(
        IllegalArgumentException.class,
        () -> positivist.gimmePositive(0),
        "zero input would cause an exception thrown"
    );
  }

}
