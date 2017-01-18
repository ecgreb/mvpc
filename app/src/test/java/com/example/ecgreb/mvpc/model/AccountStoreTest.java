package com.example.ecgreb.mvpc.model;

import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountStoreTest {

  private AccountStore accountStore = new TestAccountStore(new Activity());

  @Test public void shouldNotBeNull() throws Exception {
    assertThat(accountStore).isNotNull();
  }

  @Test
  public void shouldStoreCredentials() throws Exception {
    accountStore.storeCredentials("john.doe", "12345");
    assertThat(accountStore.retrieveUsername()).isEqualTo("john.doe");
    assertThat(accountStore.retrievePassword()).isEqualTo("12345");
  }

  private static class TestAccountStore extends AccountStore {
    public TestAccountStore(Context context) {
      super(context);
    }

    @Override protected SharedPreferences prefs() {
      return TestSharedPreferences.instance();
    }
  }
}
