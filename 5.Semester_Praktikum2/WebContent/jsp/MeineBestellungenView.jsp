<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meine Bestellungen</title>
</head>
<body>
<jsp:useBean id="abb" class="beans.AllBestellungBean"
		scope="session" />
<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />

<a href="StartView.jsp">Zurück zur Startseite</a>
<br>(Artikelbewertung)


<h1>Meine Bestellungen</h1>

	<% int kundennr= acb.getAccNr();
	out.print(abb.getAllBestellung(kundennr));%>
	
	
<style>
/* Sternebewertung */

span#Bewertung label {
 line-height: 45px;
 cursor: Pointer;
}

span#Bewertung input[type="radio"] {
 /*display: None;*/ 
}

p.sternebewertung {
 float: Left;
}

p.sternebewertung:not(:checked) > input {
 display: None;
}

p.sternebewertung:not(:checked) > label {
 float: Right;
 width: 1em;
 padding: 0 .1em;
 overflow: Hidden;
 white-space: Nowrap;
 cursor: Pointer;
 font-size: 200%;
 line-height: 1.2;
 color: #D0D0D0;
 text-shadow: 1px 1px #B0B0B0, 2px 2px #606060, .1em .1em .2em rgba(0,0,0,.5);
 transition: all .5s;
}

p.sternebewertung:not(:checked) > label::before {
 content: '⧫ ';
}

p.sternebewertung > input:checked ~ label {
 color: #FFD700;
 text-shadow: 1px 1px #C06000, 2px 2px #904000, .1em .1em .2em rgba(0,0,0,.5);
 animation: twinkle 1s ease-in-out infinite alternate;
}

p.sternebewertung:not(:checked) > label:hover,
p.sternebewertung:not(:checked) > label:hover ~ label {
 color: #FECF41;
 text-shadow: 1px 1px #F29E02, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

p.sternebewertung > input:checked + label:hover,
p.sternebewertung > input:checked + label:hover ~ label,
p.sternebewertung > input:checked ~ label:hover,
p.sternebewertung > input:checked ~ label:hover ~ label,
p.sternebewertung > label:hover ~ input:checked ~ label {
 color: #FBB600;
 text-shadow: 1px 1px #F8BA01, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
}

@keyframes twinkle {
 to {
  text-shadow: 1px 1px #FFE653, 2px 2px #FFF5BF, .1em .1em .2em rgba(0,0,0,.5);
 }
}
</style>

<p class="sternebewertung">
 <input type="radio" id="stern5" name="bewertung" value="5"><label for="stern5" title="5 Sterne">5 Sterne</label>
 <input type="radio" id="stern4" name="bewertung" value="4"><label for="stern4" title="4 Sterne">4 Sterne</label>
 <input type="radio" id="stern3" name="bewertung" value="3"><label for="stern3" title="3 Sterne">3 Sterne</label>
 <input type="radio" id="stern2" name="bewertung" value="2"><label for="stern2" title="2 Sterne">2 Sterne</label>
 <input type="radio" id="stern1" name="bewertung" value="1"><label for="stern1" title="1 Stern">1 Stern</label>
 <span id="Bewertung" title="Keine Bewertung">
  <label><input type="radio" name="bewertung" value="0" checked="checked"> Bewertung:</label>
 </span>
</p>
	
	<br><a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>