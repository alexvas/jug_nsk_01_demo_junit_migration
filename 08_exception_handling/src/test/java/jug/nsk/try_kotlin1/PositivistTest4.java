package jug.nsk.try_kotlin1;

import org.junit.Test;

public class PositivistTest4 {

  // then!
  @Test(expected = IllegalArgumentException.class)
  public void zeroInput() {
    // given
    Positivist positivist = new Positivist();
    // when
    positivist.gimmePositive(0);
  }

}
