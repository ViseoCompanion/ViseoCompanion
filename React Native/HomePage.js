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
  ListView,
  Dimensions,
  RefreshControl,
} from 'react-native';


var monthNames = ["Janv", "Fév", "Mars", "Avril", "Mai", "Juin",
  "Juill", "Août", "Sept", "Oct", "Nov", "Déc"
];
var {
height: deviceHeight,
width : deviceWidth,
} = Dimensions.
get('window');

function ThreePoints(text)
{
if(text.length>25) {
  text=text.substr(0,25) + "...";
}
return text;
}

function addZero(i) {
  if (i < 10) {
      i = "0" + i;
  }
  return i;
}

var REQUEST_URL = 'http://10.33.171.10:8080/C360/api/helloworld';

class HomePage extends React.Component {


  constructor(props) {
    super(props);
    this.state = {
      dataSource: new ListView.DataSource({
        rowHasChanged: (row1, row2) => row1 !== row2,
      }),
      loaded: false,
      refreshing: false,
    };
  }


  componentDidMount() {

    this.fetchData();
  }

  fetchData() {
    fetch(REQUEST_URL)
      .then((response) => response.json())
      .then((responseData) => {
        this.setState({
          dataSource: this.state.dataSource.cloneWithRows(responseData),
          loaded: true,
          refreshing: false
        });
      })
      .done();
  }


  _pressRow(event,email){
      this.props.navigator.push({
        id:'eventdetails',
        passProps:{
                   event,
                   email,
                 },
      })
    }

  _onRefresh() {
    this.setState({refreshing: true});
    this.fetchData()
    }

  render() {
    var email = this.props.email;
    console.log(email);
    if (!this.state.loaded) {
      return this.renderLoadingView();
    }

  return(
    <View>
        <View style={styles.topbar}>
          <View style={styles.menu0}>
          <Image  source={require('./Menu-52.png')} style={styles.icon}/>
          </View>
          <Text style={styles.viseocompanion}> VISEO COMPANION </Text>
        </View>

        <Text style={styles.title}>  Evénements:</Text>

      <ScrollView
          refreshControl={
            <RefreshControl
            refreshing={this.state.refreshing}
            onRefresh={this._onRefresh.bind(this)}
            />
          }
          scrollEventThrottle={200}
          style={styles.scrollView}>
          <ListView
            navigator={this.props.navigator}
            dataSource={this.state.dataSource}
            renderRow={this.renderRow.bind(this)}
          />
        </ScrollView>
    </View>
);
}

renderLoadingView() {
  return (
    <View style={styles.loadingContainer}>
      <Text style={styles.loadingText}>
        Chargement des événements...
      </Text>
    </View>
  );
}

renderRow(event) {
  console.log(this.props.email);
  return (
    <View style={styles.container}>
    <TouchableOpacity onPress={ () => this._pressRow(event,this.props.email)}>
      <View style={styles.rectangle} >
            <View style={styles.leftRectangle}>
            <Text  style={styles.date}> {new Date(event.date).getDate()}</Text>
            <Text  style={styles.date}> {monthNames[new Date(event.date).getMonth()]}</Text>
            <Text  style={styles.date}> {new Date(event.date).getHours()}h{addZero(new Date(event.date).getMinutes())}</Text>
        </View>
        <View style={styles.rightRectangle}>
            <Text  style={styles.name}> {ThreePoints(event.event) } </Text>
            <Text  style={styles.location}> {event.lieu} </Text>
            <Text  style={styles.location}> {ThreePoints(event.motclefs)} </Text>
            </View>
        </View>
        </TouchableOpacity>
    </View>

  );
}
}

const styles = StyleSheet.create({

  topbar:{
      height:(1/13)*deviceHeight,
      backgroundColor : 'white',
      /*justifyContent:'space-between',*/
      flexDirection:'row',

  },
  menu : {

      width:(1/14)*deviceHeight,
      height:(1/14)*deviceHeight,
      backgroundColor:'white',
      borderRadius: 3,
      borderWidth: 1,
      borderColor: 'black',
      justifyContent : 'flex-start',
      margin:5,

  },
  menu0 : {
      width : 85,
  },
  icon:{
      width:25,
      height:25,
      marginLeft:5,
      marginTop:5,
  },

  viseocompanion:{
      justifyContent:'center',
      textAlign: 'center',
      fontSize:20,
      color:'blue',
  },

  loadingText:{
      justifyContent:'center',
      textAlign: 'center',
      fontSize:20,
      color:'gray',
  },

  title:{
      textAlign:'left',
      marginLeft:3,
      fontSize:18,
      marginBottom:2,
      /*marginTop:5,*/
      backgroundColor:'white',
  },




  container: {

    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    flexDirection:'column',
  },

  loadingContainer: {

    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    flexDirection:'row',
    height:deviceHeight
  },

  /*events: {
    flex: 1,
    flexDirection : 'row',
  },*/


  rectangle: {

        width: 0.97*deviceWidth,
        height: 0.2*deviceHeight,
        backgroundColor: 'white',
        margin :3,
        borderRadius:0,
        borderWidth: 0.3,
        borderColor: 'black',
        flexDirection:'row',
        /*fontFamily: 'New Times Roman',*/


    },
    leftRectangle:{
        flex:3,

    },

    rightRectangle:{

        flex:5,

    },


    lines :{

        fontSize: 30,
        color: 'black',

    },
    toolbar :{
        /*flexDirection : 'row',*/
        backgroundColor:'white',

    },


    date: {
        textAlign: 'center',
        color: 'black',
        fontSize:20,

    },
    hour:{
        textAlign: 'left',
        color:'black',
        fontSize:20,

    },
    name: {
        textAlign: 'left',
        color: 'black',
        marginBottom: 10,
    },

    location: {
        textAlign : 'left',
        color:'black',
        marginBottom:10,
    },

    description: {
        textAlign :'left',
        color : 'black',
    },
    scrollView:{
    height: 0.85*deviceHeight,

    },

});
module.exports=HomePage;
