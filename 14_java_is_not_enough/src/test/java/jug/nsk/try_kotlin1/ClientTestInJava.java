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
    // given
    Client client = new Client();
    // when
    Object result = client.call(cont);
    // then
    assertTrue(result instanceof Enum);
//    assertEquals(result, CoroutineSingletons.COROUTINE_SUSPENDED);
  }

  private final Continuation<String> cont = mockContinuation();

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

  @Test
  void Алекс_вызывает_Юстаса2() throws InterruptedException {
    // given
    Client client = new Client();
    // when
    String result = runBlocking(
        EmptyCoroutineContext.INSTANCE,
        (coroutineScope, continuation) -> client.call(continuation)
    );
    // then
    assertEquals(result, "done");
  }

}
