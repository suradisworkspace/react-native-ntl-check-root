package com.ntlcheckroot

import android.os.Build
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.scottyab.rootbeer.RootBeer
import com.bootloadercheck.BootloaderCheck

class NtlCheckRootModule internal constructor(context: ReactApplicationContext) :
  NtlCheckRootSpec(context) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  override fun checkRootJail(promise: Promise) {
    val rootBeer = RootBeer(reactApplicationContext)
    val isOnEmulator = Build.FINGERPRINT.contains("generic", ignoreCase = true) ||
      Build.DEVICE.contains("generic", ignoreCase = true)
    val isRooted = rootBeer.isRooted() || isOnEmulator
    promise.resolve(isRooted)
  }

  @ReactMethod
  override fun checkBootloaderUnlocked(promise: Promise) {
    val bootloaderCheck = BootloaderCheck()
    promise.resolve(bootloaderCheck.isBootloaderUnlocked())
  }

  companion object {
    const val NAME = "NtlCheckRoot"
  }
}
