package jug.nsk.try_kotlin1;

import org.junit.Test;

public class PositivistTest4 {

  @Test(expected = IllegalArgumentException.class)
  public void zeroInput() {
    Positivist positivist = new Positivist();
    positivist.gimmePositive(0);
  }

}
