package com.example.ecgreb.mvpc.model;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask {
  public boolean flag = false;

  @Override protected Object doInBackground(Object[] params) {
    flag = true;
    return null;
  }
}
