package jug.nsk.try_kotlin1;

// Java
class ShambalaAdapter implements AutoCloseable {
  private final Shambala shambala;

  ShambalaAdapter(Shambala shambala) {
    this.shambala = shambala;
    shambala.access();
  }

  @Override
  public void close() {
    shambala.leave();
  }
}
