package com.bootloadercheck

import com.bootloadercheck.magiskkiller.MagiskKiller.*

class BootloaderCheck {
    // @SuppressLint("QueryPermissionsNeeded")
    fun isBootloaderUnlocked(): Boolean {
      val result = detectBootloaderProperties()
      return (result and FOUND_BOOTLOADER_UNLOCKED) != 0 || (result and FOUND_BOOTLOADER_SELF_SIGNED) != 0
    }
}
