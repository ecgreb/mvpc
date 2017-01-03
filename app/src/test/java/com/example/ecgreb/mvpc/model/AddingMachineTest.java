package com.example.ecgreb.mvpc.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddingMachineTest {
  private AddingMachine addingMachine = new AddingMachine();

  @Test public void shouldNotBeNull() throws Exception {
    assertThat(addingMachine).isNotNull();
  }

  @Test public void sum_shouldReturnNullForNullAddend()
      throws Exception {
    assertThat(addingMachine.sum(null, null)).isNull();
  }

  @Test public void sum_shouldReturnNullForEmptyAddend()
      throws Exception {
    assertThat(addingMachine.sum("", "")).isNull();
  }

  @Test public void sum_shouldReturnNullForNonNumberAddend()
      throws Exception {
    assertThat(addingMachine.sum("a", "b")).isNull();
  }

  @Test public void sum_shouldReturnCorrectResultAsString() throws Exception {
    assertThat(addingMachine.sum("2", "3")).isEqualTo("5");
  }
}
