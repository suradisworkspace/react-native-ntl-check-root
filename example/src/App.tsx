import * as React from 'react';

import { StyleSheet, View, Text, Alert } from 'react-native';
import { checkRootJail } from 'react-native-ntl-check-root';

export default function App() {
  const [checkRootRes, setCheckRootRes] = React.useState<boolean | undefined>();

  React.useEffect(() => {
    init();
  }, []);

  const init = async () => {
    const jailRes = await checkRootJail();
    setCheckRootRes(jailRes);
    Alert.alert('jail res', `${jailRes}`);
  };

  return (
    <View style={styles.container}>
      <Text>checkRootRes: {checkRootRes}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
