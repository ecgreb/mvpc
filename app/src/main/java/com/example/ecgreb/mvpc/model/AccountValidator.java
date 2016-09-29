package com.example.ecgreb.mvpc.model;

public class AccountValidator {

  public void validate(String username, String password, Callback callback) {
    new ValidationThread(new NetworkConnection(), username, password, callback).start();
  }

  public interface Callback {
    void onValidationComplete(boolean success);
  }
}
