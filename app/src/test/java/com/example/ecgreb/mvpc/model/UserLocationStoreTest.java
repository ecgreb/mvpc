package com.example.ecgreb.mvpc.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ContextCompat.class)
@PowerMockIgnore("javax.net.ssl.*")
public class UserLocationStoreTest {
  private UserLocationStore userLocationStore;

  @Before public void setUp() throws Exception {
    userLocationStore = new UserLocationStore(PowerMockito.mock(Context.class));
  }

  @Test public void getLocation_shouldReturnDeviceLocation() throws Exception {
    PowerMockito.mockStatic(ContextCompat.class);
    PowerMockito.when(ContextCompat.checkSelfPermission(any(Context.class), anyString()))
        .thenReturn(PackageManager.PERMISSION_GRANTED);
    assertThat(userLocationStore.getLocation().getSource()).isEqualTo("device");
  }

  @Test public void getLocation_shouldReturnWebLocation() throws Exception {
    PowerMockito.mockStatic(ContextCompat.class);
    PowerMockito.when(ContextCompat.checkSelfPermission(any(Context.class), anyString()))
        .thenReturn(PackageManager.PERMISSION_DENIED);
    assertThat(userLocationStore.getLocation().getSource()).isEqualTo("web");
  }
}
