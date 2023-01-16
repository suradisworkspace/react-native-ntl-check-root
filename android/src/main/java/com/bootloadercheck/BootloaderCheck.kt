package com.bootloadercheck

import top.canyie.magiskkiller.MagiskKiller.*

class BootloaderCheck {
    // @SuppressLint("QueryPermissionsNeeded")
    fun isBootloaderUnlocked(): Boolean {
      val result = detectBootloaderProperties()

      val isUnlock = if ((result and FOUND_BOOTLOADER_UNLOCKED) != 0) {
          true
      } else if ((result and FOUND_BOOTLOADER_SELF_SIGNED) != 0) {
          true
      } else {
          false
      }

      return isUnlock
    }
}