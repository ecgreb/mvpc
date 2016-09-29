package com.example.ecgreb.mvpc.model;

public class AccountValidator {
  private ValidationThread validationThread;

  public void validate(String username, String password, Callback callback) {
    validationThread = new ValidationThread(new NetworkConnection(), username, password, callback);
    validationThread.start();
  }

  public boolean isValidating() {
    return validationThread.isAlive();
  }

  public interface Callback {
    void onValidationComplete(boolean success);
  }
}
