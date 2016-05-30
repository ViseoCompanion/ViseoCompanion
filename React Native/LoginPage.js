/** VISEO COMPANION **/
'use strict';
import React, {
  Component,
} from 'react';

import{
AppRegistry,
StyleSheet,
Text,
TextInput,
Navigator,
Image,
NavMenu,
ScrollView,
View,
TouchableOpacity,
ListView,
Dimensions,
} from 'react-native';
var{
height: deviceHeight,
width: deviceWidth
} = Dimensions.get('window');

//var REQUEST_URL = 'http://10.0.2.2:8080/C360/api/he10.33.170.239lloworld2';
const REQUEST_URL3 = 'http://10.33.171.10:8080/C360/api/helloworld3';

class LoginPage extends React.Component{

  validateEmail = (email) => {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
  };

  constructor(props) {
    super(props);
    this.state = {
      _email:'',
      _password:'',
      errorType:''
    };
  }

onPressButtonPOST() {

  if (this.state._email == '' || this.state._password == ''){
    this.setState({errorType: 'Veuillez remplir tous les champs.'});
  }
  else if(!this.validateEmail(this.state._email)){
    this.setState({errorType: 'Veuillez entrer une adressse email valide.'});
  }
  else{
    fetch(REQUEST_URL3, {
      method: 'POST',
      headers: {
      'Content-Type': 'application/json'
    },
      body: JSON.stringify({
        "email": this.state._email,
        "password": this.state._password
      })
    })
    .then((response) => response.json())
    .then((responseData) => {
      if(responseData==true){
        this._pushToHomePage(this.state._email)
      }
      else{
        this.setState({errorType: 'Les données entrées ne sont pas valides.'});
      }
    })
    .done()
  }
  this._textInput1.setNativeProps({text:''});
}

_pushToHomePage(email){
  this.props.navigator.replace({
    id : 'homepage',
    passProps:{
               email:email
             },
  })
}

onPressCreate(){
  this.props.navigator.push({
    id:'createaccount',
  })
}

onPressPasswordForgot(){
  this.props.navigator.push({
    id:'newpage',
  })
}

render(){
  return(
    <View>
        <View>
            <View style={styles.topbar}>
              <Text style={styles.viseocompanion}> VISEO COMPANION </Text>
            </View>
        </View>
        <ScrollView
        contentInset={{top : -50}}
        style={styles.scrollView}>
        <View style={styles.container}>
          <View style={styles.blankcontainer}>
          </View>
          <View style={styles.titlecontainer}>
            <Text style={styles.title}> Connexion </Text>
          </View>
          <View style={styles.blankcontainer2}>
          </View>
          <View style={styles.emailcontainer}>
          <TextInput style={styles.emailInput}
            onChangeText={(_email) => this.setState({_email})}
            placeholder="Email"
            keyboardType="email-address"
            autoCorrect={false}
            selectTextOnFocus={true}
            underlineColorAndroid={"white"}
          />
          </View>
          <View style={styles.passwordcontainer}>
          <TextInput style={styles.passwordInput}
            onChangeText={(_password) => this.setState({_password})}
            placeholder="Mot de passe"
            ref={component=>this._textInput1=component}
            password={true}
            autoCorrect={false}
            selectTextOnFocus={true}
            underlineColorAndroid={"white"}
            minLength={5}
          />
          </View>
          <View style={styles.blankcontainer3}>
          </View>
              <View style={styles.passwordforgotcontainer}>
                      <TouchableOpacity onPress={() =>{this.onPressPasswordForgot()}}>
                          <Text style={styles.forgotText}>Mot de passe oublié ?</Text>
                      </TouchableOpacity>
              </View>

              <View style={styles.buttoncontainer}>
                      <TouchableOpacity onPress={() =>{this.onPressButtonPOST()}}>
                          <View style={styles.okButton}>
                          <Text style={styles.okText}>OK</Text>
                          </View>
                      </TouchableOpacity>

              </View>
              <View style={styles.errorcontainer}>
                <Text style={styles.errorText}>{this.state.errorType}</Text>
              </View>
              <View style={styles.createaccountcontainer}>
                      <TouchableOpacity onPress={() =>{this.onPressCreate()}}>
                          <Text style={styles.createText}>Créer un compte</Text>
                      </TouchableOpacity>
                      </View>
              </View>
        </ScrollView>
    </View>
  )
}
}

const styles = StyleSheet.create({

topbar:{
    backgroundColor: 'grey',
    height:0.1*deviceHeight,

},

viseocompanion:
{
    justifyContent:'center',
    textAlign: 'center',
    fontSize:20,
    color:'black',

},

container:
{
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    flexDirection:'column',
    height:0.85*deviceHeight,


},
blankcontainer:
{
    flexDirection:'row',
    height:0.02*deviceHeight
},

blankcontainer2:
{
    flexDirection:'row',
    height:0.1*deviceHeight
},
blankcontainer3:
{
    flexDirection:'row',
    height:0.05*deviceHeight
},

titlecontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.05*deviceHeight
},

title:
{
  fontSize:20,
  color:'black',
  justifyContent:'center',
  alignItems:'center',

},

emailcontainer:
{
  flexDirection:'row',
  alignItems:'center',
  justifyContent: 'center',
  height:0.1*deviceHeight
},

passwordcontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.1*deviceHeight
},

buttoncontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.1*deviceHeight
},

passwordforgotcontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.1*deviceHeight
},

errorcontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.02*deviceHeight
},

createaccountcontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.1*deviceHeight
},

emailInput:{
    alignItems:'center',
    width: 0.7*deviceWidth,
    textAlign:'center',
},

passwordInput:{

  alignItems:'center',
  width: 0.7*deviceWidth,
  textAlign:'center',
},

okButton:{
width:0.25*deviceWidth,
height:0.08*deviceHeight,
borderColor:'black',
borderWidth:2,
justifyContent:'center',
alignItems:'center',

},

okText:{
  fontSize:25,
  color:'black',
},

errorText:{
  fontSize:15,
  color:'red',
},

createText:{
  fontSize:15,
  color:'black',
},

forgotText:{
  fontSize:15,
  color:'brown',
  fontStyle:'italic',
  textDecorationLine:'underline',
  textDecorationStyle:'dotted'
},

scrollView:{
  height : deviceHeight,

},
error_message : {
color : 'red',

}





});



module.exports=LoginPage;
