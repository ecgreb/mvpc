package com.example.ecgreb.mvpc;

import com.example.ecgreb.mvpc.controller.LoginActivity;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

public class MvpcApplication extends Application {

  @Singleton @Component(modules = { LoginModule.class }) public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);
  }

  private ApplicationComponent component;

  @Override public void onCreate() {
    super.onCreate();
    component = DaggerMvpcApplication_ApplicationComponent.create();
  }

  public ApplicationComponent component() {
    return component;
  }
}
