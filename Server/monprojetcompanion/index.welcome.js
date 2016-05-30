'use strict';
  
var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Component
} = React;
 
class ReactLayouts extends Component{
    render() {
        return (
            <View style={styles.mainContainer}>
               <View style={styles.toolbar}>
                    <Text style={styles.toolbarButton}>Add</Text>
                    <Text style={styles.toolbarTitle}>This is the title</Text>
                    <Text style={styles.toolbarButton}>Like</Text>
                </View>
                <View style={styles.content}>
  
                    {/* START NEW CODE */}
 
                    <View style={styles.messageBox}>
                        <View>
                            <Text style={styles.messageBoxTitleText}>A simple mesage</Text>
                        </View>
                        <View>
                            <Text style={styles.messageBoxBodyText}>This is just a dummy sample it will help us to see the alignment in action.</Text>
                        </View>
                    </View>

              {/* END NEW CODE */}
  
                </View>
            </View>
        );
    }
}