package com.example.ecgreb.mvpc.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AddingMachineTest {
  private val addingMachine = AddingMachine()

  @Test fun shouldNotBeNull() {
    assertThat(addingMachine).isNotNull()
  }

  @Test fun sum_shouldReturnNullForNullAddend() {
    assertThat(addingMachine.sum(null, null)).isNull()
  }

  @Test fun sum_shouldReturnNullForEmptyAddend() {
    assertThat(addingMachine.sum("", "")).isNull()
  }

  @Test fun sum_shouldReturnNullForNonNumberAddend() {
    assertThat(addingMachine.sum("a", "b")).isNull()
  }

  @Test fun sum_shouldReturnCorrectResultAsString() {
    assertThat(addingMachine.sum("2", "3")).isEqualTo("5")
  }
}
