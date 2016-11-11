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

  private AutoCompleteTextView mEmailView;
  private EditText mPasswordView;
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

    mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
    mPasswordView = (EditText) findViewById(R.id.password);

    Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        validateInput();
      }
    });

    loginFormPresenter = new LoginFormPresenter(this);
  }

  private void validateInput() {
    final String email = mEmailView.getText().toString();
    final String password = mPasswordView.getText().toString();
    loginFormPresenter.onLoginButtonClick(email, password);
  }

  public String getEmail() {
    return mEmailView.getText().toString();
  }

  public String getPassword() {
    return mPasswordView.getText().toString();
  }

  public void displayLoginError() {
    mPasswordView.setError(getContext().getString(R.string.error_incorrect_password));
    mPasswordView.requestFocus();
  }

  @Override public void clearAllErrors() {
    mEmailView.setError(null);
    mPasswordView.setError(null);
  }

  @Override public void showEmailError(ErrorType errorType) {
    switch (errorType) {
      case EMPTY:
        mEmailView.setError(getContext().getString(R.string.error_field_required));
        break;
      case INVALID:
        mEmailView.setError(getContext().getString(R.string.error_invalid_email));
        break;
    }

    mEmailView.requestFocus();
  }

  @Override public void showPasswordError(ErrorType errorType) {
    switch (errorType) {
      case EMPTY:
        mPasswordView.setError(getContext().getString(R.string.error_field_required));
        break;
      case INVALID:
        mPasswordView.setError(getContext().getString(R.string.error_invalid_password));
        break;
    }

    mPasswordView.requestFocus();
  }

  @Override public void processValidInput() {
    callOnClick();
  }
}
