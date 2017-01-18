package com.example.ecgreb.mvpc.model;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO: Implement put/get for non-String values.
public class TestSharedPreferences implements SharedPreferences {

  private static TestSharedPreferences instance = new TestSharedPreferences();

  public static SharedPreferences instance() {
    return instance;
  }

  private TestSharedPreferences() {

  }

  private HashMap<String, Object> map = new HashMap<>();

  @Override public Map<String, ?> getAll() {
    return null;
  }

  @Nullable @Override public String getString(String key, String defValue) {
    return (String) map.get(key);
  }

  @Nullable @Override public Set<String> getStringSet(String key, Set<String> defValues) {
    return null;
  }

  @Override public int getInt(String key, int defValue) {
    return 0;
  }

  @Override public long getLong(String key, long defValue) {
    return 0;
  }

  @Override public float getFloat(String key, float defValue) {
    return 0;
  }

  @Override public boolean getBoolean(String key, boolean defValue) {
    return false;
  }

  @Override public boolean contains(String key) {
    return false;
  }

  @Override public Editor edit() {
    return new TestEditor();
  }

  @Override public void registerOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener) {

  }

  @Override public void unregisterOnSharedPreferenceChangeListener(
      OnSharedPreferenceChangeListener listener) {

  }

  private class TestEditor implements SharedPreferences.Editor {
    @Override public Editor putString(String key, String value) {
      map.put(key, value);
      return this;
    }

    @Override public Editor putStringSet(String key, Set<String> values) {
      return null;
    }

    @Override public Editor putInt(String key, int value) {
      return null;
    }

    @Override public Editor putLong(String key, long value) {
      return null;
    }

    @Override public Editor putFloat(String key, float value) {
      return null;
    }

    @Override public Editor putBoolean(String key, boolean value) {
      return null;
    }

    @Override public Editor remove(String key) {
      return null;
    }

    @Override public Editor clear() {
      return null;
    }

    @Override public boolean commit() {
      return false;
    }

    @Override public void apply() {

    }
  }
}
