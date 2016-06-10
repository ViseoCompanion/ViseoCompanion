

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
var string0 = Event.event;
var string1 = Event.lieu;
var string2 = Event.description;
var string3 = Event.moclefs;

var monRegEx = /^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._-\s]{2,255}$/;
if(string0==="" || Event.date==="" || string1==="" || string2==="" || string3===""){
      console.log("* veuillez remplir tous les champs!!!");

      var errorMessage = document.getElementById("errorMessage").inner;
      errorMessageAll = "* veuillez remplir tous les champs!!!";
      errorMessage=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage").innerHTML = errorMessage;
      document.getElementById("errorMessage-titre").innerHTML ="";
      document.getElementById("errorMessage-lieu").innerHTML ="";
      document.getElementById("errorMessage-description").innerHTML ="";
      document.getElementById("errorMessage-motclefs").innerHTML ="";
}
else if(!string0.match(monRegEx)){
      var errorMessage_titre = document.getElementById("errorMessage-titre").inner;
      errorMessage0 =  "* 2 caractères minimum, caractères spéciaux autorisés : -, ', ?, !, ., /, virgule, +, *, #";
      errorMessage_titre=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage-titre").innerHTML = errorMessage_titre;
      document.getElementById("errorMessage").innerHTML = "";
      document.getElementById("errorMessage-lieu").innerHTML ="";
      document.getElementById("errorMessage-description").innerHTML ="";
      document.getElementById("errorMessage-motclefs").innerHTML ="";
}

else if(!string1.match(monRegEx)){
      var errorMessage_lieu = document.getElementById("errorMessage-lieu").inner;
      errorMessage0 =  "* 2 caractères minimum, caractères spéciaux autorisés : -, ', ?, !, ., /, virgule, +, *, #";
      errorMessage_lieu=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage-lieu").innerHTML = errorMessage_lieu;
      document.getElementById("errorMessage").innerHTML ="";
      document.getElementById("errorMessage-titre").innerHTML ="";
      document.getElementById("errorMessage-description").innerHTML ="";
      document.getElementById("errorMessage-motclefs").innerHTML ="";
}
else if(!string2.match(monRegEx)){
      var errorMessage_description = document.getElementById("errorMessage-description").inner;
      errorMessage0 =  "* 2 caractères minimum, caractères spéciaux autorisés : -, ', ?, !, ., /, virgule, +, *, #";
      errorMessage_description=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage-description").innerHTML = errorMessage_description;
      document.getElementById("errorMessage").innerHTML = "";
      document.getElementById("errorMessage-titre").innerHTML ="";
      document.getElementById("errorMessage-lieu").innerHTML ="";
      document.getElementById("errorMessage-motclefs").innerHTML ="";
}
else if(!string3.match(monRegEx)){
      var errorMessage_motclefs = document.getElementById("errorMessage-motclefs").inner;
      errorMessage0 =  "* 2 caractères minimum, caractères spéciaux autorisés : -, ', ?, !, ., /, virgule, +, *, #";
      errorMessage_motclefs=errorMessage0.fontcolor("red");
      document.getElementById("errorMessage-motclefs").innerHTML = errorMessage_motclefs;
      document.getElementById("errorMessage").innerHTML = "";
      document.getElementById("errorMessage-titre").innerHTML ="";
      document.getElementById("errorMessage-lieu").innerHTML ="";
      document.getElementById("errorMessage-description").innerHTML ="";
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
