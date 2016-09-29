package com.example.ecgreb.mvpc.view;

import com.example.ecgreb.mvpc.R;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class LoginForm extends ScrollView {

  private AutoCompleteTextView mEmailView;
  private EditText mPasswordView;

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
  }

  private void validateInput() {
    // Reset errors.
    mEmailView.setError(null);
    mPasswordView.setError(null);

    // Store values at the time of the login attempt.
    String email = mEmailView.getText().toString();
    String password = mPasswordView.getText().toString();

    boolean cancel = false;
    View focusView = null;

    // Check for a valid password, if the user entered one.
    if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
      mPasswordView.setError(getContext().getString(R.string.error_invalid_password));
      focusView = mPasswordView;
      cancel = true;
    }

    // Check for a valid email address.
    if (TextUtils.isEmpty(email)) {
      mEmailView.setError(getContext().getString(R.string.error_field_required));
      focusView = mEmailView;
      cancel = true;
    } else if (!isEmailValid(email)) {
      mEmailView.setError(getContext().getString(R.string.error_invalid_email));
      focusView = mEmailView;
      cancel = true;
    }

    if (cancel) {
      // There was an error; don't attempt login and focus the first
      // form field with an error.
      focusView.requestFocus();
    } else {
      // Show a progress spinner, and kick off a background task to
      // perform the user login attempt.
      callOnClick();
    }
  }

  private boolean isEmailValid(String email) {
    //TODO: Replace this with your own logic
    return email.contains("@");
  }

  private boolean isPasswordValid(String password) {
    //TODO: Replace this with your own logic
    return password.length() > 4;
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
}
