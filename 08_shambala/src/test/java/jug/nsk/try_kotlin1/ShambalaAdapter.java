package jug.nsk.try_kotlin1;

class ShambalaAdapter implements AutoCloseable {
  private final Shambala shambala;

  ShambalaAdapter(Shambala shambala) {
    this.shambala = shambala;
  }

  void open() {
    shambala.access();
  }

  @Override
  public void close() {
    shambala.leave();
  }
}
