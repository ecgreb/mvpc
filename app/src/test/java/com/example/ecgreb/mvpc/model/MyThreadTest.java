package com.example.ecgreb.mvpc.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyThreadTest {
  //@Test public void start_shouldSetFlagToTrue() throws Exception {
  //  MyThread myThread = new MyThread();
  //  myThread.start();
  //  assertThat(myThread.flag).isTrue();
  //}

  @Test public void run_shouldSetFlagToTrue() throws Exception {
    MyThread myThread = new MyThread();
    myThread.run();
    assertThat(myThread.flag).isTrue();
  }
}
