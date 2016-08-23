  angular.module('app', ['ngRoute', 'ngCookies','ui.bootstrap', 'ui.bootstrap.datetimepicker','perfect_scrollbar'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/event', {
                controller: 'EventController',
                templateUrl: 'event/event.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/forgotPassword', {
                controller: 'ForgotPasswordController',
                templateUrl: 'forgotPassword/forgotPassword.view.html',
                controllerAs: 'vm'
            })

            .when('/createEvent', {
                controller: 'CreateEventController',
                templateUrl: 'createEvent/createEvent.view.html',
                controllerAs: 'CE'
            })

            .when('/blank', {
                controller: 'BlankController',
                templateUrl: 'blank/blank.view.html',
                controllerAs: 'vm'
            })

            .when('/changeEvent', {
                controller: 'ChangeEventController',
                templateUrl: 'changeEvent/changeEvent.view.html',
                controllerAs: 'CE'
            })

            // .when('/listEvent', {
            //     controller: 'ListEventController',
            //     templateUrl: 'listEvent/listEvent.view.html',
            //     controllerAs: 'LE'
            // })
            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            //var restrictedPage = $.inArray($location.path(), ['/login', '/forgotPassword']) === -1;
            var restrictedPage = $.inArray($location.path(), ['/login', '/forgotPassword','/event','/createEvent','/blank','/changeEvent','/listEvent']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }
