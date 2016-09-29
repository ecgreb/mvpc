package com.example.ecgreb.mvpc.presenter;

import com.example.ecgreb.mvpc.controller.LoginController;
import com.example.ecgreb.mvpc.model.AccountValidator;

public class LoginPresenter {
  private LoginController loginController;

  public LoginPresenter(LoginController loginController) {
    this.loginController = loginController;
  }

  public void onLoginButtonClick(String username, String password) {
    loginController.showProgress(true);
    AccountValidator accountValidator = new AccountValidator();
    accountValidator.validate(username, password, new AccountValidator.Callback() {
      @Override public void onValidationComplete(boolean success) {
        loginController.showProgress(false);
        if (success) {
          loginController.showSuccess();
        } else {
          loginController.showError();
        }
      }
    });
  }
}
