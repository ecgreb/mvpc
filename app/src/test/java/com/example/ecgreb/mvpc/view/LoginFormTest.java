package com.example.ecgreb.mvpc.view;

import com.example.ecgreb.mvpc.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(manifest="src/main/AndroidManifest.xml", sdk=23)
public class LoginFormTest {
  private LoginForm loginForm = new LoginForm(RuntimeEnvironment.application);

  @Test public void shouldNotBeNull() throws Exception {
    assertThat(loginForm).isNotNull();
  }

  @Test public void onClickSignInButton_shouldNotInvokeListenerForInvalidInput() throws Exception {
    TextView emailView = (TextView) loginForm.findViewById(R.id.email);
    TextView passwordView = (TextView) loginForm.findViewById(R.id.password);
    Button signInButton = (Button) loginForm.findViewById(R.id.email_sign_in_button);
    TestListener listener = new TestListener();

    emailView.setText("bad_email");
    passwordView.setText("blah");
    loginForm.setOnClickListener(listener);
    signInButton.performClick();
    assertThat(listener.click).isFalse();
  }

  @Test public void onClickSignInButton_shouldInvokeListenerForValidInput() throws Exception {
    TextView emailView = (TextView) loginForm.findViewById(R.id.email);
    TextView passwordView = (TextView) loginForm.findViewById(R.id.password);
    Button signInButton = (Button) loginForm.findViewById(R.id.email_sign_in_button);
    TestListener listener = new TestListener();

    emailView.setText("foo@example.com");
    passwordView.setText("hello");
    loginForm.setOnClickListener(listener);
    signInButton.performClick();
    assertThat(listener.click).isTrue();
  }

  private class TestListener implements View.OnClickListener {
    private boolean click;

    @Override public void onClick(View v) {
      click = true;
    }
  }
}
