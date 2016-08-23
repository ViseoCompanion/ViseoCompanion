
angular.module('app')
    .controller('ChangeEventController', ['$location','$http','$scope','$interval','$rootScope',function($location,$http,$scope,$interval,$rootScope){

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

        self.updateEvent = updateEvent;



        $scope.comments = [
          {
            name: "dinghao1",
            date: "15/08/2016 12:30"
          },
          {
            name: "dinghao7",
            date: "15/08/2016 14:00"
          },
          {
            name: "dinghao4",
            date: "15/08/2016 14:30"
          },
          {
            name: "dinghao3",
            date: "15/08/2016 17:25"
          },
          {
            name: "lalala",
            date: "01/01/2016 12:00"
          },
          {
            name: "lalala",
            date: "01/01/2016 12:00"
          },
        ];



            $scope.people = [

            ];

            $scope.loadPeople = function() {
              $http({
                method: 'GET',
                url: 'http://localhost:8080/C360/api/admin/getYesList/1/'
              }).then(function successCallback(response1) {

                $http({
                  method: 'GET',
                  url: 'http://localhost:8080/C360/api/admin/getNoList/1/'
                }).then(function successCallback(response2) {
                  console.log(response1.data);
                  console.log(response1.data.length);
                  console.log(response2.data);
                  console.log(response2.data.length);
                  var response = response1.data.concat(response2.data);
                  console.log(response);
                  $scope.people=response;
                  $scope.peopleNumY=response1.data.length;
                  $scope.peopleNumN=response2.data.length;
                }, function errorCallback(response) {
                });
              }, function errorCallback(response) {

              });

            };
            $scope.stop = $interval($scope.loadPeople, 1000);
            $scope.loadPeople();
            var dereg = $rootScope.$on('$locationChangeSuccess', function() {
                $interval.cancel($scope.stop);
                dereg();
              });

        function deleteEvent(){
          $http({
            method: 'POST',
            url: 'http://localhost:8080/C360/api/event/delete/X/Y/'
          }).then(function successCallback(response) {
            if(response==true){
                $location.path('/blank');
                console.log(data);
            }
            else{
                console.log("**************");
                console.log(data);
                self.dataLoading = false;
                self.error=true;
            }
          }, function errorCallback(response) {
          });
        }
        function updateEvent() {
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
          console.log("**********************");
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
