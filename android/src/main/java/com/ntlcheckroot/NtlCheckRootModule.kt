package com.ntlcheckroot

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.scottyab.RootBeer

class NtlCheckRootModule internal constructor(context: ReactApplicationContext) :
  NtlCheckRootSpec(context) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  override fun checkRootJail(promise: Promise) {
    RootBeer rootBeer = new RootBeer(context)
    promise.resolve(rootBeer.isRooted())
  }

  companion object {
    const val NAME = "NtlCheckRoot"
  }
}
