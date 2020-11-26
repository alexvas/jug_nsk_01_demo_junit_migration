package jug.nsk.try_kotlin1;

// Java
public class Shambala {

  private Shambala() {
  }

  private static class Holder {
    private static final Shambala theOne = new Shambala();
  }

  public static Shambala getInstance() {
    return Holder.theOne;
  }

  private boolean available = false;

  public void access() {
    if (available) throw new IllegalStateException("already available");
    available = true;
    System.out.println("Shambala is available now");
  }

  public void leave() {
    if (!available) throw new IllegalStateException("already NOT available");
    available = false;
    System.out.println("Shambala has become NOT available");
  }

  public boolean isAuthentic() {
    if (!available) {
      throw new IllegalStateException("not available!");
    }
    return true;
  }


}
