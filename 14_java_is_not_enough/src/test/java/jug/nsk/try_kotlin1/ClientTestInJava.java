package jug.nsk.try_kotlin1;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static kotlinx.coroutines.BuildersKt.runBlocking;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
class ClientTestInJava {

  @Test
  void Алекс_вызывает_Юстаса1() {
    Client client = new Client();

    Continuation<String> cont = mockContinuation();
    Object result = client.call(cont);
    assertTrue(result instanceof Enum);
//    assertEquals(result, CoroutineSingletons.COROUTINE_SUSPENDED);
  }

  @Test
  void Алекс_вызывает_Юстаса2() throws InterruptedException {
    Client client = new Client();

    String result = runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (coroutineScope, continuation) -> client.call(continuation)
    );
    assertEquals(result, "done");
  }

  @NotNull
  private Continuation<String> mockContinuation() {
    return new Continuation<String>() {
      @NotNull
      @Override
      public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
      }

      @Override
      public void resumeWith(@NotNull Object o) {

      }
    };
  }


}
