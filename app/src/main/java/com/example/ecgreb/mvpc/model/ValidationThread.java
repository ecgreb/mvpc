package com.example.ecgreb.mvpc.model;

class ValidationThread extends Thread {
  private final Network network;
  private final String username;
  private final String password;
  private final AccountValidator.Callback callback;

  /**
   * A dummy authentication store containing known user names and passwords.
   * TODO: remove after connecting to a real authentication system.
   */
  private static final String[] DUMMY_CREDENTIALS = new String[] {
      "foo@example.com:hello", "bar@example.com:world"
  };

  ValidationThread(Network network, String username, String password,
      AccountValidator.Callback callback) {
    this.network = network;
    this.username = username;
    this.password = password;
    this.callback = callback;
  }

  @Override public void run() {
    network.connect();

    for (String credential : DUMMY_CREDENTIALS) {
      String[] pieces = credential.split(":");
      if (pieces[0].equals(username)) {
        // Account exists, return true if the password matches.
        callback.onValidationComplete(pieces[1].equals(password));
        return;
      }
    }

    // TODO: register the new account here.
    callback.onValidationComplete(true);
  }
}
