import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  checkRootJail(): Promise<boolean>;
  checkBootloaderUnlocked(): Promise<boolean>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('NtlCheckRoot');
