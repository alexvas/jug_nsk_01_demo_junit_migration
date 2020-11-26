package jug.nsk.try_kotlin1;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

// Java
public class ShambalaRunner extends BlockJUnit4ClassRunner {

  public ShambalaRunner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected Statement classBlock(RunNotifier notifier) {
    return wrap(super.classBlock(notifier));
  }

  private Statement wrap(final Statement statement) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        try (ShambalaAdapter ignored = new ShambalaAdapter(Shambala.getInstance())) {
          statement.evaluate();
        }
      }
    };
  }
}
