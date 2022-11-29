package com.ntlcheckroot

import android.os.Build
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.scottyab.rootbeer.RootBeer
import java.io.File

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
    var isSuspicious = false
    val pm = reactApplicationContext.getPackageManager()
    val packages = pm.getInstalledPackages(0)

    for (packageInfo in packages) {
      val appInfo = packageInfo.applicationInfo
      val suspiciousPaths = SUSPECT_LIBRARYS.map {
        File(appInfo.nativeLibraryDir + it)
      }

      isSuspicious = suspiciousPaths.any {
        it.exists()
      }

      if (isSuspicious) {
        break
      }
    }

    promise.resolve(isOnEmulator || rootBeer.isRooted() || isSuspicious)
  }

  companion object {
    const val NAME = "NtlCheckRoot"
    private val SUSPECT_LIBRARYS = listOf(
      "/libmagisk64.so",
      "/libmagiskinit.so",
      "/libmagisk32.so",
      "/libbusybox.so",
      "/libmagiskboot.so",
      "/libstub.so",
    )
  }
}
