package com.example.ecgreb.mvpc.view;

import com.example.ecgreb.mvpc.model.InputValidator;

public interface ILoginForm {
  void clearAllErrors();
  void showEmailError(InputValidator.ErrorType errorType);
  void showPasswordError(InputValidator.ErrorType errorType);
  void processValidInput();
}
