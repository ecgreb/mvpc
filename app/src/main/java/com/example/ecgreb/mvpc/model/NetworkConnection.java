package com.example.ecgreb.mvpc.model;

class NetworkConnection implements Network {
  @Override public void connect() {
    try {
      // Simulate network access.
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
