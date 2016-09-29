package com.example.ecgreb.mvpc.presenter;

import com.example.ecgreb.mvpc.controller.LoginController;
import com.example.ecgreb.mvpc.model.AccountValidator;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPresenterTest {
  private LoginPresenter loginPresenter;
  private TestLoginController loginController;

  @Before public void setUp() throws Exception {
    loginController = new TestLoginController();
    loginPresenter = new LoginPresenter(new TestAccountValidator()).register(loginController);
  }

  @Test public void onLoginButtonClick_shouldShowSuccessOnValidInput() throws Exception {
    loginPresenter.onLoginButtonClick("valid_username", "valid_password");
    assertThat(loginController.success).isTrue();
  }

  @Test public void onLoginButtonClick_shouldShowErrorOnInvalidInput() throws Exception {
    loginPresenter.onLoginButtonClick("invalid_username", "invalid_password");
    assertThat(loginController.error).isTrue();
  }

  private class TestLoginController implements LoginController {
    boolean progress;
    boolean error;
    boolean success;

    @Override public void showProgress(boolean show) {
      progress = show;
    }

    @Override public void showError() {
      error = true;
    }

    @Override public void showSuccess() {
      success = true;
    }
  }

  private class TestAccountValidator extends AccountValidator {
    @Override public void validate(String username, String password, Callback callback) {
      if ("valid_username".equals(username) && "valid_password".equals(password)) {
        callback.onValidationComplete(true);
      } else {
        callback.onValidationComplete(false);
      }
    }

    @Override public boolean isValidating() {
      return false;
    }
  }
}
