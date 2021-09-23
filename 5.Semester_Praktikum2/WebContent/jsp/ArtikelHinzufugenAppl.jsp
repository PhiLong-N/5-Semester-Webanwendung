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
String artikel = request.getParameter("name");
String kategorie = request.getParameter("kategorie");
String preisString = request.getParameter("preis");
String beschreibung = request.getParameter("beschreibung");
String lagerString = request.getParameter("lager");

String btnArtikelHinzufugen = request.getParameter("btnArtikelHinzufugen");
if(btnArtikelHinzufugen==null)btnArtikelHinzufugen="";

if(artikel.equals("") || kategorie.equals("") || beschreibung.equals("") || preisString.equals("") || lagerString.equals("")){
	response.sendRedirect("ArtikelHinzufugenView.jsp");
}
else if(btnArtikelHinzufugen.equals("artikelHinzufugen")){
	boolean check = true;
	try{
		double preis = Double.parseDouble(preisString);
		int lager = Integer.parseInt(lagerString);
		ab.insertNewArtikel(artikel,kategorie,preis,beschreibung,lager);
	}catch(Exception e){check = false;
	}
	if (check==false)response.sendRedirect("ArtikelHinzufugenView.jsp");
	else response.sendRedirect("EinstellungView.jsp");
}
else response.sendRedirect("StartView.jsp");

%>

</body>
</html>