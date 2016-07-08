(function () {
    'use strict';

    angular
        .module('app')
        .controller('BlankController', BlankController);

    BlankController.$inject = ['$location','$http'];
    function BlankController($location,$http) {

    }

})();
