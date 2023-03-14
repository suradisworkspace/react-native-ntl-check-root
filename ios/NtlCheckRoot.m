#import "NtlCheckRoot.h"
#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>


@implementation NtlCheckRoot
RCT_EXPORT_MODULE()

static BOOL hasCydiaInstalled()
{
    BOOL canOpenScheme = NO;
    
    if([[UIApplication sharedApplication] canOpenURL:[NSURL URLWithString:@"cydia://"]]){
        canOpenScheme = YES;
    }
    return canOpenScheme;
}

static BOOL checkSuspeciousApps()
{
    NSArray *suspiciousApps = @[ @"/Applications/Cydia.app",
                                 @"/Applications/blackra1n.app",
                                 @"/Applications/FakeCarrier.app",
                                 @"/Applications/Icy.app",
                                 @"/Applications/IntelliScreen.app",
                                 @"/Applications/MxTube.app",
                                 @"/Applications/RockApp.app",
                                 @"/Applications/SBSettings.app",
                                 @"/Applications/WinterBoard.app" ];

                                 
    for(NSString * appPath in suspiciousApps) {
        if([[NSFileManager defaultManager] fileExistsAtPath: appPath]){
            return YES;
        }
    }
    return NO;
}

static BOOL checkSuspeciousFiles()
{
    NSArray *suspeciousFiles = @[ @"/Library/MobileSubstrate/DynamicLibraries/LiveClock.plist",
                                  @"/Library/MobileSubstrate/DynamicLibraries/Veency.plist",
                                  @"/private/var/lib/apt",
                                  @"/private/var/lib/apt/",
                                  @"/private/var/lib/cydia",
                                  @"/private/var/mobile/Library/SBSettings/Themes",
                                  @"/private/var/stash",
                                  @"/private/var/tmp/cydia.log",
                                  @"/System/Library/LaunchDaemons/com.ikey.bbot.plist",
                                  @"/System/Library/LaunchDaemons/com.saurik.Cydia.Startup.plist",
                                  @"/usr/bin/sshd",
                                  @"/usr/libexec/sftp-server",
                                  @"/usr/libexec/ssh-keysign",
                                  @"/usr/sbin/sshd",
                                  @"/var/lib/cydia",
                                  @"/usr/sbin/frida-server",
                                  @"/usr/bin/cycript",
                                  @"/usr/local/bin/cycript",
                                  @"/usr/lib/libcycript.dylib",
                                  @"/var/cache/apt",
                                  @"/var/lib/apt",
                                  @"/var/tmp/cydia.log",
                                  @"/var/log/syslog",
                                  @"/etc/apt",
                                  @"/etc/ssh/sshd_config",
                                  @"/bin/bash",
                                  @"/bin/sh",
                                  @"/Library/MobileSubstrate/MobileSubstrate.dylib" ];
    for(NSString * filePath in suspeciousFiles) {
        if([[NSFileManager defaultManager] fileExistsAtPath: filePath]){
            return YES;
        }
    }
    return NO;
}

static BOOL canWriteSystemFile()
{
    NSString *jailBreakText = @"Developer Insider";
    BOOL ok = [jailBreakText writeToFile:@"jailBreakText.txt" atomically:YES encoding:NSUTF8StringEncoding error:nil];
    if(ok){
        return YES;
    }
    return NO;
}

// Example method
// See // https://reactnative.dev/docs/native-modules-ios
RCT_EXPORT_METHOD(checkRootJail:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    if(TARGET_IPHONE_SIMULATOR || hasCydiaInstalled() || checkSuspeciousApps() || checkSuspeciousFiles() || canWriteSystemFile()){
        resolve(@YES);
    } else {
        resolve(@NO);
    }
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
(const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeNtlCheckRootSpecJSI>(params);
}
#endif

@end
