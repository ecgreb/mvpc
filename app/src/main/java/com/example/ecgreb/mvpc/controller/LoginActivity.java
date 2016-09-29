package com.example.ecgreb.mvpc.controller;

import com.example.ecgreb.mvpc.R;
import com.example.ecgreb.mvpc.presenter.LoginPresenter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginController {

  // UI references.
  private AutoCompleteTextView mEmailView;
  private EditText mPasswordView;
  private View mProgressView;
  private View mLoginFormView;

  private LoginPresenter loginPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this);

    // Set up the login form.
    mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

    mPasswordView = (EditText) findViewById(R.id.password);
    mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        if (id == R.id.login || id == EditorInfo.IME_NULL) {
          attemptLogin();
          return true;
        }
        return false;
      }
    });

    Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mEmailSignInButton.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        attemptLogin();
      }
    });

    mLoginFormView = findViewById(R.id.login_form);
    mProgressView = findViewById(R.id.login_progress);
  }

  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  private void attemptLogin() {
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
      mPasswordView.setError(getString(R.string.error_invalid_password));
      focusView = mPasswordView;
      cancel = true;
    }

    // Check for a valid email address.
    if (TextUtils.isEmpty(email)) {
      mEmailView.setError(getString(R.string.error_field_required));
      focusView = mEmailView;
      cancel = true;
    } else if (!isEmailValid(email)) {
      mEmailView.setError(getString(R.string.error_invalid_email));
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
      loginPresenter.onLoginButtonClick(email, password);
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

  /**
   * Shows the progress UI and hides the login form.
   */
  @Override public void showProgress(final boolean show) {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        showProgressInternal(show);
      }
    });
  }

  private void showProgressInternal(final boolean show) {
    int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    mLoginFormView.animate()
        .setDuration(shortAnimTime)
        .alpha(show ? 0 : 1)
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
          }
        });

    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    mProgressView.animate()
        .setDuration(shortAnimTime)
        .alpha(show ? 1 : 0)
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
          }
        });
  }

  @Override public void showError() {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        showErrorInternal();
      }
    });
  }

  private void showErrorInternal() {
    mPasswordView.setError(getString(R.string.error_incorrect_password));
    mPasswordView.requestFocus();
  }

  @Override public void showSuccess() {
    runOnUiThread(new Runnable() {
      @Override public void run() {
        showSuccessInternal();
      }
    });
  }

  private void showSuccessInternal() {
    finish();
  }
}
