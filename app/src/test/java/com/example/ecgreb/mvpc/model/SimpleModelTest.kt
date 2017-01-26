package com.example.ecgreb.mvpc.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SimpleModelTest {
  private val simpleModel = SimpleModel()

  @Test fun shouldNotBeNull() {
    assertThat(simpleModel).isNotNull()
  }
}
