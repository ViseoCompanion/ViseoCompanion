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

const REQUEST_URL2 = 'http://10.33.171.10:8080/C360/api/helloworld2';
const REQUEST_URL4 = 'http://10.33.171.10:8080/C360/api/helloworld4';


class CreateAccount extends React.Component{

  validateEmail = (email) => {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
  };

  constructor(props) {
    super(props);
    this.onPressButtonPOST = this.onPressButtonPOST.bind(this);
    this.state = {
      _email:'',
      _password1:'',
      _password2:'',
      errorType:''
    };
  }



  onPressButtonPOST() {
    if (this.state._email == '' || this.state._password1 == '' || this.state._password2 == ''){
      this.setState({errorType: 'Veuillez remplir tous les champs.'});
    }
    else if(!this.validateEmail(this.state._email)){
      this.setState({errorType: 'Veuillez entrer une adressse email valide.'});
    }
    else {
      fetch(REQUEST_URL4, {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json'
      },
        body: JSON.stringify({
          "email": this.state._email,
          //"password": this.state._password1
        })
      })
      .then((response) => response.json())
      .then((responseData) => {
        if(responseData==false){
          this.setState({errorType: 'Compte déja existant.'});
        }
        else if(responseData==true){
        if(this.state._password1.length>=6){
          if(this.state._password1 !== this.state._password2){
            this.setState({errorType: 'Vous navez pas saisi le même mot de passe.'});
          }
          else if(this.state._password1 == this.state._password2){
            fetch(REQUEST_URL2, {
              method: 'POST',
              headers: {
              'Content-Type': 'application/json'
            },
              body: JSON.stringify({
                "email": this.state._email,
                "password": this.state._password1
              })
            })
            this._pushToHomePage(this.state._email)
          }
        }
        else if(this.state._password1.length<6){
          this.setState({errorType: 'Le mot de passe doit comporter au moins 6 characters.'});
        }
      }
      })
      .done()
    }
      this._textInput1.setNativeProps({text:''});
      this._textInput2.setNativeProps({text:''});
    }

    _pushToHomePage(email){
      this.props.navigator.replace({
        id : 'homepage',
        passProps:{
                   email:email
                 },
      })
    }



  render(_email) {


    return (
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
        <View style={styles.titlecontainer}>
          <Text style={styles.title}> Créer un compte </Text>
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
          ref={component=>this._textInput1=component}
          onChangeText={(_password1) => this.setState({_password1})}
          placeholder="Mot de passe(6 caracters minimum)"
          password={true}
          autoCorrect={false}
          selectTextOnFocus={true}
          underlineColorAndroid={"white"}
          minLength={5}

        />
        </View>
        <View style={styles.passwordcontainer}>
        <TextInput style={styles.passwordInput}
          ref={component=>this._textInput2=component}
          onChangeText={(_password2) => this.setState({_password2})}
          placeholder="Confirmation mot de passe"
          password={true}
          autoCorrect={false}
          selectTextOnFocus={true}
          underlineColorAndroid={"white"}
          minLength={5}
          />
        </View>
        <View style={styles.errorcontainer}>
          <Text style={styles.errorText}>{this.state.errorType}</Text>
        </View>
        <TouchableOpacity onPress={() =>{this.onPressButtonPOST()}}>
            <View style={styles.okButton}>
            <Text style={styles.okText}>OK</Text>
            </View>
        </TouchableOpacity>
    </View>

</ScrollView>


</View>
          );

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
    height:0.65*deviceHeight,


},

errorcontainer:
{
    flexDirection:'row',
    alignItems:'center',
    justifyContent: 'center',
    height:0.05*deviceHeight
},

titlecontainer:
{
    flexDirection:'row',
    flex:0.6,
    alignItems:'center',
},

title:
{
  fontSize:15,
  color:'black',
  justifyContent:'center',

},

emailcontainer:
{
  flexDirection:'row',
  flex:2,
  alignItems:'center',

},

passwordcontainer:
{
    flexDirection:'row',
    flex:2,
    alignItems:'center',

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
width:0.2*deviceWidth,
height:0.1*deviceHeight,
borderColor:'black',
borderWidth:2,
justifyContent:'center',
alignItems:'center',

},

okText:{
  fontSize:25,
  color:'black',


},

scrollView:{
  height : deviceHeight,

},
errorText : {
color : 'red',

}





});



module.exports=CreateAccount;
