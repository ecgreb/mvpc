package com.example.ecgreb.mvpc.controller;

import com.example.ecgreb.mvpc.R;
import com.example.ecgreb.mvpc.presenter.LoginPresenter;
import com.example.ecgreb.mvpc.view.LoginForm;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginController {

  // UI references.
  private View mProgressView;
  private LoginForm mLoginFormView;

  private LoginPresenter loginPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this);

    mLoginFormView = (LoginForm) findViewById(R.id.login_form);
    mLoginFormView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        attemptLogin();
      }
    });

    mProgressView = findViewById(R.id.login_progress);
  }

  /**
   * Attempts to sign in or register the account specified by the login form.
   * If there are form errors (invalid email, missing fields, etc.), the
   * errors are presented and no actual login attempt is made.
   */
  private void attemptLogin() {
    loginPresenter.onLoginButtonClick(mLoginFormView.getEmail(), mLoginFormView.getPassword());
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
        mLoginFormView.displayLoginError();
      }
    });
  }

  @Override public void showSuccess() {
    finish();
  }
}
