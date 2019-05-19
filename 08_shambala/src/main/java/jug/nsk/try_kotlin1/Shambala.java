package jug.nsk.try_kotlin1;

public class Shambala {

  private Shambala() {
  }

  private static class Holder {
    private static final Shambala theOne = new Shambala();
  }

  public static Shambala getInstance() {
    return Holder.theOne;
  }

  private boolean avalable = false;

  public void access() {
    avalable = true;
    System.out.println("Shambala is available now");
  }

  public void leave() {
    avalable = false;
    System.out.println("Shambala has become NOT available");
  }

  public boolean isAuthentic() {
    if (!avalable) {
      throw new IllegalStateException("not available!");
    }
    return true;
  }


}
