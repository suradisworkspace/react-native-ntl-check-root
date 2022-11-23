package com.ntlcheckroot

import android.content.pm.PackageManager
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
    var isSuspect = false
    val pm = reactApplicationContext.getPackageManager()
    val packages = pm.getInstalledPackages(0)

    for (packageInfo in packages) {
      val appInfo = packageInfo.applicationInfo
      val suspectPaths = SUSPECT_LIBRARYS.map {
        File(appInfo.nativeLibraryDir + it)
      }

      isSuspect = suspectPaths.any {
        it.exists()
      }
    }

    promise.resolve(rootBeer.isRooted() || isSuspect)
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
