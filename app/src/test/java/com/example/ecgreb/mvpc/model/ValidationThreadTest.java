package com.example.ecgreb.mvpc.model;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ValidationThreadTest {
  @Test public void run_shouldConnectToNetwork() throws Exception {
    Network network = mock(Network.class);
    AccountValidator.Callback callback = new TestCallback();
    ValidationThread thread = new ValidationThread(network, "username", "password", callback);
    thread.run();
    verify(network).connect();
  }

  private class TestCallback implements AccountValidator.Callback {
    @Override public void onValidationComplete(boolean success) {
    }
  }
}
