package com.example.ecgreb.mvpc.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AccountStore {

  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  private final Context context;

  public AccountStore(Context context) {
    this.context = context;
  }

  public void storeCredentials(String username, String password) {
    SharedPreferences.Editor editor = prefs().edit();
    editor.putString(USERNAME, username);
    editor.putString(PASSWORD, password);
    editor.apply();
  }

  public String retrieveUsername() {
    return prefs().getString(USERNAME, null);
  }

  public String retrievePassword() {
    return prefs().getString(PASSWORD, null);
  }

  protected SharedPreferences prefs() {
    return PreferenceManager.getDefaultSharedPreferences(context);
  }
}
