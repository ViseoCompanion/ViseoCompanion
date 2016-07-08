angular.module('app')
        .controller('CreateEventController', ['$location','$http',function($location,$http){

          var self = this;

          self.picker1 = {
            date: new Date(),
            datepickerOptions: {
              showWeeks: false,
              minDate: new Date(),
              startingDay: 1,
              dateDisabled: function(data) {
                return (data.mode === 'day' && (new Date().toDateString() == data.date.toDateString()));
              }
            }
          };

          self.picker2 = {
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
        console.log(picker);
        self[picker].open = true;
    };

          self.createEvent = createEvent;


          function createEvent() {
            // console.log(self.event);
            // console.log(self.event.length);
            // console.log(self.lieu);
            // console.log(self.lieu.length);
            // console.log(self.description);
            // console.log(self.description.length);
            // console.log(self.motclefs);
            // console.log(self.motclefs.length);

            //if(self.event.length<2 || self.lieu.length<2 || self.description.length<2 || self.motclefs.length<2){
            if(self.event && self.lieu && self.description && self.motclefs){

              var url='http://localhost:8080/C360/api/event/addEvent';
              self.dataLoading = true;
              var Event = new Object;
              var date = self.picker1.date.toString().substring(0,16);
              var time = self.picker2.date.toString().substring(16);
              console.log(date);
              console.log(time);
              Event.date=new Date(date + time);
              Event.event = self.event;
              Event.lieu = self.lieu;
              Event.description = self.description;
              Event.motclefs =  self.motclefs;
              console.log(Event.date);
              console.log(Event);
              var Event2=JSON.stringify(Event);
              $http.post(url, Event2)
              .success(function(data) {
                  if(data==true){
                    $location.path('/blank');
                    console.log(data);
                  }
                  else{
                    console.log("**************");
                    console.log(data);
                    self.dataLoading = false;
                    var errorMessage = document.getElementById("errorMessage").inner;
                    var errorMessage1 = "Veuillez entrer des données valides.";
                    errorMessage=errorMessage1.fontcolor("red");
                    document.getElementById("errorMessage").innerHTML = errorMessage;
                  }
              })
              .error(function(data, status, headers, config) {
                console.log("**************");
              });
            }
            else{
              var errorMessage = document.getElementById("errorMessage").inner;
              var errorMessage1 = "Veuillez entrer des données valides.";
              errorMessage=errorMessage1.fontcolor("red");
              document.getElementById("errorMessage").innerHTML = errorMessage;
              };
          }
        }]);
