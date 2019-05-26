package jug.nsk.try_kotlin1;

class Positivist {

  void gimmePositive(int input) {
    if (input <= 0) {
      throw new IllegalArgumentException("argument must be positive, yet the provided one is " + input);
    }
  }

}
