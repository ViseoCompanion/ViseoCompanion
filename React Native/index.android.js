/** VISEO COMPANION **/
'use strict';
import React, {
  Component,
} from 'react';

import{
Animated,
AppRegistry,
StyleSheet,
Text,
Image,
Navigator,
NavMenu,
ScrollView,
View,
TouchableOpacity,
ToastAndroid,
TouchableNativeFeedback,
ListView,
Dimensions,
} from 'react-native';


/*var InfiniteScrollView = require('react-native-infinite-scroll-view');*/

var NewPage = require('./NewPage');
var CreateAccount=require('./CreateAccount');
var LoginPage=require('./LoginPage');
var EventDetails=require('./EventDetails');
var HomePage=require('./HomePage');

var {
height: deviceHeight,
width : deviceWidth,
} = Dimensions.get('window');



/*fetch('./customData.json').then((response) => response.json()) .then((responseJson) =>
{ console.log(responseJson); }) .catch((error) => { console.warn(error); });*/

/*var icon = require('react-native-iconic-font/fontawesome');*/


var project_companion = React.createClass({


statics: {
title: '<Navigator>',
description: 'JS-implemented navigation',
},

renderScene: function(route, navigator) {
switch (route.id) {
case 'newpage':
return <NewPage navigator={navigator} {...route.passProps} />;
case 'homepage':
        return (
          <HomePage navigator={navigator} {...route.passProps} />
        );
case 'loginpage':
        return (
          <LoginPage navigator={navigator} {...route.passProps} />
        );
case 'createaccount' :
        return (
          <CreateAccount navigator={navigator} {...route.passProps}/>
        );
case 'eventdetails' :
        return (
          <EventDetails navigator={navigator} {...route.passProps}/>
        );
case 'passwordErrorMessage':
        return (
          <PasswordErrorMessage navigator={navigator} {...route.passProps}/>
        );


}
},

render: function() {
return (
<Navigator
style={styles.container}
initialRoute={{id:'loginpage'}}
renderScene={this.renderScene}
configureScene={(route) => {
if (route.sceneConfig) {
return route.sceneConfig;
}

return Navigator.SceneConfigs.FadeAndroid;
}}
/>
);
}

});

const styles = StyleSheet.create({
container: {

justifyContent: 'center'

}
});


AppRegistry.registerComponent('project_companion', () => project_companion);
