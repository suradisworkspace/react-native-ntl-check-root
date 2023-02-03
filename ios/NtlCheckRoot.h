
#ifdef RCT_NEW_ARCH_ENABLED
#import "NtlCheckRootSpec.h"

@interface NtlCheckRoot : NSObject <NativeNtlCheckRootSpec>
#else
#import <React/RCTBridgeModule.h>

@interface NtlCheckRoot : NSObject <RCTBridgeModule>
#endif

@end
