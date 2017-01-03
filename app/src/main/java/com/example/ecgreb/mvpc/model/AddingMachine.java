package com.example.ecgreb.mvpc.model;

public class AddingMachine {
  public String sum (String a, String b) {
    if (a == null || b == null) {
      return null;
    }

    if (a.length() == 0 || b.length() == 0) {
      return null;
    }

    int sumInt;

    try {
      sumInt = Integer.parseInt(a) + Integer.parseInt(b);
    } catch (NumberFormatException e) {
      return null;
    }

    return Integer.toString(sumInt);
  }
}
