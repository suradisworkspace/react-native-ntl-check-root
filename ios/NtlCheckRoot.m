#import "NtlCheckRoot.h"
#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>


@implementation NtlCheckRoot
RCT_EXPORT_MODULE()

// Example method
// See // https://reactnative.dev/docs/native-modules-ios
RCT_EXPORT_METHOD(checkRootJail:(RCTPromiseResolveBlock)resolve
                 reject:(RCTPromiseRejectBlock)reject)
{
    if(TARGET_IPHONE_SIMULATOR){
        resolve(@NO);
    }
    UIDevice *currentDevice = [UIDevice currentDevice];
    resolve(@YES);
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
