package com.example.ecgreb.mvpc.model;

public class MyThread extends Thread {
  public boolean flag = false;

  @Override public void run() {
    // Some long running operation.
    flag = true;
  }
}
