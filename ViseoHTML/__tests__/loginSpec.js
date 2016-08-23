describe('Login', function() {
    beforeEach(module('app'));
    var ctrl, mockBackend, location;
    beforeEach(inject(function($controller, $httpBackend, $location) {
		mockBackend = $httpBackend;
		ctrl = $controller('LoginController');
        location = $location;
        location.url('/login');
	}));
	afterEach(function() {
        mockBackend.verifyNoOutstandingExpectation();
		mockBackend.verifyNoOutstandingRequest();
	});
	it('valid information and go to Event page', function() {
        ctrl.email="aa@aa.aa";
        ctrl.password="111111";
        mockBackend.expectPOST("http://localhost:8080/C360/api/admin/Authentification").respond(true);
        ctrl.login();
        mockBackend.flush();
        expect(location.path()).toBe('/event')
	});
	it('error message when information not valid', function() {
        ctrl.email="aa@aa.aa";
        ctrl.password="111111";
        mockBackend.expectPOST("http://localhost:8080/C360/api/admin/Authentification").respond(false);
        ctrl.login();
        mockBackend.flush();
        expect(location.path()).toBe('/login');
        expect(ctrl.error).toBe(true);
	});
});
