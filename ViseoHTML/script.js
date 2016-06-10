

function submitform(){


          // var xhr = new XMLHttpRequest();
          // xhr.open(form.method, form.action, true);
          // xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');


              var myevent=document.getElementById('event').value;
              var date=document.getElementById('date').value;
              var time=document.getElementById('time').value;
              var lieu=document.getElementById('lieu').value;
              var description=document.getElementById('description').value;
              var motclefs=document.getElementById('motclefs').value;
              console.log(date);
              console.log(time);
              console.log(date+ " " + time);


var Event = new Object;
Event.event=myevent;
Event.date=new Date(date+ " " + time);
Event.lieu=lieu;
Event.description=description;
Event.motclefs=motclefs;
console.log(Event.date);
console.log(Event);
// var list =JSON.stringify(Event);

// console.log(list);

          // xhr.send(JSON.stringify(Event));
            //return(list);



console.log(JSON.stringify(Event));


var string = Event.event;
var monRegEx = /^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._-\s]{5,60}$/;
if(Event.event==="" || Event.date==="" || Event.lieu==="" || Event.description==="" || Event.motclefs===""){
      console.log("veuillez remplir tous les champs!!!");

      var errorMessage = document.getElementById("errorMessage").inner;
      errorMessage0 = "veuillez remplir tous les champs!!!";
      errorMessage=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage").innerHTML = errorMessage;
}
else if(Event.event.length<2){
  var errorMessage = document.getElementById("errorMessage").inner;
  errorMessage0 =  "*Tire : Il faut au moins 2 caractère.";
  errorMessage=errorMessage0.fontcolor("red");
  document.getElementById("errorMessage").innerHTML = errorMessage;
}
else if(!string.match(monRegEx)){
      var errorMessage = document.getElementById("errorMessage").inner;
      errorMessage0 =  "Les caractères spéciaux autorisés sont les accents, tiret, apostrophe, point d'interrogation, point d'exclamation, guillemets, point, slash, virgule, +, *, #, espace (trimé) et le champ doit avoir 2 caractères minimum";
      errorMessage=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage").innerHTML = errorMessage;
}

else{

    errorMessage="";
            $.ajax({
       url : 'http://localhost:8080/C360/api/event/addEvent', // La ressource ciblée
       data:JSON.stringify(Event),
       method :'POST',
       contentType:"application/json",
       authorization: 'basic',
       complete: function() {console.log('envoyé!');}
    });

}


}

function checkBlank(){

}


var app = angular.module('app', ['ui.bootstrap', 'ui.bootstrap.datetimepicker']);

app.controller('MyController', ['$scope', function($scope) {

    var that = this;

    // date picker
    this.picker1 = {
      date: new Date(),
      datepickerOptions: {
        showWeeks: false,
        startingDay: 1,
        dateDisabled: function(data) {
          return (data.mode === 'day' && (new Date().toDateString() == data.date.toDateString()));
        }
      }
    };

    // time picker
    this.picker2 = {
      date:  new Date(new Date().setHours(08,00,00)),
      timepickerOptions: {
        readonlyInput: false,
        showMeridian: false,
        defaultTime:'current',
        minuteStep: 30,
        template:'dropdown',
      }
    };

    this.openCalendar = function(e, picker) {
        that[picker].open = true;
    };




    // destroy watcher
    $scope.$on('$destroy', function() {
        unwatchMinMaxValues();
    });

}]);
