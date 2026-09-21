package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("DPA Jordan", appName)
  }

  @Test
  fun `verify 13 camps and services data`() {
    assertEquals(13, com.example.data.model.CampsData.allCamps.size)
    assertEquals(4, com.example.data.model.ServicesData.services.size)
  }
}
