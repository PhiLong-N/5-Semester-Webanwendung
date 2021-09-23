
function bewertungBestatigen(){
	var text="Vielen Dank für Ihre Bewertung.";
	alert('Vielen Dank für Ihre Bewertung.');
}


function geek() {
            var doc;
            var result = confirm("Press a button!");
            if (result == true) {
                doc = "OK was pressed.";
            } else {
                doc = "Cancel was pressed.";
            }
            document.getElementById("g").innerHTML = result;
        }

function test(){
	var ergbenis = window.confirm("Wollen Sie wirklich Ihren Account Loeschen?");
	var baum="blaaa";
	document.getElementById("btnAdresse").buttonClicked = ergbenis;
	document.getElementById("aaa").innerText = baum;
	return true;
}

function hello(name){
	alert(name);
	var myObject = {
		attr1: "haio",
		attr2: "roeckle"
	};
	
	
	myObject.job = "HWG Lu";
	myObject.toString = function(){
		return this.attr1 + this.attr2 + this.job;
	}
	alert(myObject.toString());
//	alert("this: " + this);
	var ergebnis = window.confirm("möchtest Du das?");
	alert("ergebnis: " + ergebnis);
	var ergebnis1 = prompt("wie heisst Du?");
	alert("ergebnisPrompt: " + ergebnis1);
}
