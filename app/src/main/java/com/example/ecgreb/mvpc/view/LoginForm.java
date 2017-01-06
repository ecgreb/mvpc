package com.example.ecgreb.mvpc.view;

import com.example.ecgreb.mvpc.R;
import com.example.ecgreb.mvpc.model.InputValidator;
import com.example.ecgreb.mvpc.model.InputValidator.ErrorType;
import com.example.ecgreb.mvpc.presenter.LoginFormPresenter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * View that handles login form including username and password fields and login button. Input
 * validation is performed using the {@link LoginFormPresenter} and {@link InputValidator} model.
 */
public class LoginForm extends ScrollView implements ILoginForm {

  private AutoCompleteTextView emailView;
  private EditText passwordView;
  private LoginFormPresenter loginFormPresenter;

  public LoginForm(Context context) {
    super(context);
    init();
  }

  public LoginForm(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public LoginForm(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    ((LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE))
        .inflate(R.layout.login_form, this, true);

    emailView = (AutoCompleteTextView) findViewById(R.id.email);
    passwordView = (EditText) findViewById(R.id.password);

    final Button emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    emailSignInButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        validateInput();
      }
    });

    loginFormPresenter = new LoginFormPresenter(this);
  }

  private void validateInput() {
    final String email = emailView.getText().toString();
    final String password = passwordView.getText().toString();
    loginFormPresenter.onLoginButtonClick(email, password);
  }

  public String getEmail() {
    return emailView.getText().toString();
  }

  public String getPassword() {
    return passwordView.getText().toString();
  }

  public void displayLoginError() {
    passwordView.setError(getContext().getString(R.string.error_incorrect_password));
    passwordView.requestFocus();
  }

  @Override public void clearAllErrors() {
    emailView.setError(null);
    passwordView.setError(null);
  }

  @Override public void showEmailError(ErrorType errorType) {
    switch (errorType) {
      case EMPTY:
        emailView.setError(getContext().getString(R.string.error_field_required));
        break;
      case INVALID:
        emailView.setError(getContext().getString(R.string.error_invalid_email));
        break;
    }

    emailView.requestFocus();
  }

  @Override public void showPasswordError(ErrorType errorType) {
    switch (errorType) {
      case EMPTY:
        passwordView.setError(getContext().getString(R.string.error_field_required));
        break;
      case INVALID:
        passwordView.setError(getContext().getString(R.string.error_invalid_password));
        break;
    }

    passwordView.requestFocus();
  }

  @Override public void processValidInput() {
    callOnClick();
  }
}
