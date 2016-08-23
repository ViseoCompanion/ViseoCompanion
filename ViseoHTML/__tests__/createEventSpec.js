describe('CreateEvent', function() {
    beforeEach(module('app'));
    var ctrl, mockBackend, location;
    beforeEach(inject(function($controller, $httpBackend, $location) {
        mockBackend = $httpBackend;
        ctrl = $controller('CreateEventController');
        location = $location;
        location.url('/createEvent');
    }));
    afterEach(function() {
        mockBackend.verifyNoOutstandingExpectation();
        mockBackend.verifyNoOutstandingRequest();
    });
    it('valid information and go to new page', function() {
        ctrl.event="aaaaaa";
        ctrl.date="2000-01-01 00:00:00";
        ctrl.lieu="aaaaaa";
        ctrl.description="aaaaaa";
        ctrl.motclefs="aaaaaaaa";
        mockBackend.expectPOST("http://localhost:8080/C360/api/event/addEvent").respond(true);
        ctrl.createEvent();
        mockBackend.flush();
        expect(ctrl.dataLoading).toBe(true);
        expect(location.path()).toBe('/blank')
    });
    it('valid information and go to new page', function() {
        ctrl.event="aaaaaa";
        ctrl.date="2000-01-01 00:00:00";
        ctrl.lieu="aaaaaa";
        ctrl.description="aaaaaa";
        ctrl.motclefs="aaaaaaaa";
        mockBackend.expectPOST("http://localhost:8080/C360/api/event/addEvent").respond(false);
        ctrl.createEvent();
        mockBackend.flush();
        expect(location.path()).toBe('/createEvent');
        expect(ctrl.error).toBe(true);
        expect(ctrl.dataLoading).toBe(false);
    });
    it('valid information and go to new page', function() {
        ctrl.event="aaaaaa";
        ctrl.date="2000-01-01 00:00:00";
        ctrl.lieu="aaaaaa";
        ctrl.description="aaaaaa";
        ctrl.motclefs="aaaaaaaa";
        mockBackend.expectPOST("http://localhost:8080/C360/api/event/addEvent").respond(false);
        ctrl.createEvent();
        mockBackend.flush();
        expect(location.path()).toBe('/createEvent');
        expect(ctrl.error).toBe(true);
        expect(ctrl.dataLoading).toBe(false);
    });
});
