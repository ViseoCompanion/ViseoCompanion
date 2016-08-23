angular.module('app')
        .controller('LoginController', ['$location','$http',function($location,$http){

        var vm = this;

        vm.login = login;

        function login() {
          var url='http://localhost:8080/C360/api/admin/Authentification';
          vm.dataLoading = true;
          var Admin = new Object;
          Admin.email=vm.email;
          Admin.password=vm.password;
          console.log(vm.email);
          console.log(vm.password);
          console.log(Admin);

          var Admin2=JSON.stringify(Admin);
          $http.post(url, Admin2)
          .success(function(data) {
              if(data==true){
                $location.path('/event');
                console.log(data);
              }
              else{
                console.log("**************");
                console.log(data);
                vm.dataLoading = false;
                vm.error=true;
              }
          })
          .error(function(data, status, headers, config) {
            console.log("**************");
          });
        }
      }]);
