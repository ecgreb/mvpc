package com.example.ecgreb.mvpc.view;

import com.example.ecgreb.mvpc.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ScrollView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class LoginForm extends ScrollView {
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
  }
}
