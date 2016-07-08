(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location','$http'];
    function LoginController($location,$http) {
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
                var errorMessage = document.getElementById("errorMessage").inner;
                var errorMessage1 = "Veuillez entrer des données de connexion valides.";
                errorMessage=errorMessage1.fontcolor("red");
                document.getElementById("errorMessage").innerHTML = errorMessage;
              }
          })
          .error(function(data, status, headers, config) {
            console.log("**************");
          });
        };
    }

})();
