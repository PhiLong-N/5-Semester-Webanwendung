<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="ab" class="beans.ArtikelBean" scope="session" />


<%
int btnBewerten=0;
int bewertung =0;

String btnBewertenString = request.getParameter("btnBewerten");
if (btnBewertenString==null)btnBewertenString="";
else {
	btnBewerten = Integer.parseInt(btnBewertenString);
}
String bewertungString = request.getParameter("bewertung");


if (btnBewerten!=0){
	bewertung = Integer.parseInt(bewertungString);
	ab.updateBewertung(btnBewerten, bewertung);
	response.sendRedirect("MeineBestellungenView.jsp");
}
else response.sendRedirect("StartView.jsp");

%>

</body>
</html>