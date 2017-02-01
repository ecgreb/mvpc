package com.example.ecgreb.mvpc.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class UserLocationStore {
  private final Context context;

  public UserLocationStore(Context context) {
    this.context = context;
  }

  public UserLocation getLocation() {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {

      UserLocation userLocation = new UserLocation("device");
      // Fetch current device location from the LocationManager.
      return userLocation;

    } else {
      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .connectTimeout(10, TimeUnit.SECONDS)
          .writeTimeout(10, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS);

      OkHttpClient okHttpClient = builder.build();

      UserLocation userLocation = new UserLocation("web");
      // Fetch user's default location from a web service.
      return userLocation;
    }
  }

  public class UserLocation {
    private final String source;
    private double lat;
    private double lon;

    public UserLocation(String source) {
      this.source = source;
    }

    public String getSource() {
      return source;
    }

    public double getLat() {
      return lat;
    }

    public void setLat(double lat) {
      this.lat = lat;
    }

    public double getLon() {
      return lon;
    }

    public void setLon(double lon) {
      this.lon = lon;
    }
  }
}
