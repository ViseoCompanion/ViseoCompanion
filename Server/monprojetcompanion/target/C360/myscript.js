

function submitform(){


          // var xhr = new XMLHttpRequest();
          // xhr.open(form.method, form.action, true);
          // xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');


              var myevent=document.getElementById('event').value;
              var date=document.getElementById('date').value;
              var lieu=document.getElementById('lieu').value;
              var description=document.getElementById('description').value;
              var motclefs=document.getElementById('motclefs').value;
              console.log(myevent);
var Event = new Object;

Event.event=myevent;
Event.date=date;
Event.lieu=lieu;
Event.description=description;
Event.motclefs=motclefs;
console.log(Event);
// var list =JSON.stringify(Event);

// console.log(list);

          // xhr.send(JSON.stringify(Event));
            //return(list);
console.log(JSON.stringify(Event));
            $.ajax({
       url : 'http://localhost:8080/C360/api/helloworld6', // La ressource ciblée
       data:JSON.stringify(Event),
       method :'POST',
       contentType:"application/json",
       complete: function() {console.log('envoyé!');}

    });




}
