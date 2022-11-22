package com.ntlcheckroot

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class NtlCheckRootModule internal constructor(context: ReactApplicationContext) :
  NtlCheckRootSpec(context) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  override fun checkRootJail(promise: Promise) {
    const val result = true
    promise.resolve(result)
  }

  companion object {
    const val NAME = "NtlCheckRoot"
  }
}
