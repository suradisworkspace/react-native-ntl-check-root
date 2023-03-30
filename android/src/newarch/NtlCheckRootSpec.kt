package com.ntlcheckroot

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.Promise

abstract class NtlCheckRootSpec internal constructor(context: ReactApplicationContext) :
  NativeNtlCheckRootSpec(context) {

    abstract override fun checkRootJail(promise: Promise)
}
