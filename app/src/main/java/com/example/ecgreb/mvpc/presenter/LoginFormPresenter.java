package com.example.ecgreb.mvpc.presenter;

import com.example.ecgreb.mvpc.model.InputValidator;
import com.example.ecgreb.mvpc.view.ILoginForm;

public class LoginFormPresenter {
  private final ILoginForm loginForm;

  public LoginFormPresenter(ILoginForm loginForm) {
    this.loginForm = loginForm;
  }

  public void onLoginButtonClick(String email, String password) {
    loginForm.clearAllErrors();

    final InputValidator inputValidator = new InputValidator();

    final InputValidator.Response emailResponse = inputValidator.validateEmail(email);
    if (!emailResponse.isSuccess()) {
      loginForm.showEmailError(emailResponse.getErrorType());
      return;
    }

    final InputValidator.Response passwordResponse = inputValidator.validatePassword(password);
    if (!passwordResponse.isSuccess()) {
      loginForm.showPasswordError(passwordResponse.getErrorType());
      return;
    }

    loginForm.processValidInput();
  }
}
