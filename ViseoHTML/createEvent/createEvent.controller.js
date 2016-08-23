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
                    self.error=true;
                  }
              })
              .error(function(data, status, headers, config) {
                console.log("**************");
              });
            }
            else{
                self.error=true;
              };
          }
        }]);
