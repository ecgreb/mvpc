package com.example.ecgreb.mvpc.presenter;

import com.example.ecgreb.mvpc.controller.LoginController;

public class LoginPresenter {
  private LoginController loginController;

  public LoginPresenter(LoginController loginController) {
    this.loginController = loginController;
  }

  public void onLoginButtonClick(String username, String password) {
    loginController.showProgress(true);
  }
}
