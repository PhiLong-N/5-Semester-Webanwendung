<%@page import="beans.AccountBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Shop Anmelden</title>
</head>
<body>

<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="655"/></a>	

	<h4>Bitte melden Sie sich an</h4>
	<form action="./AnmeldenAppl.jsp" method="get">
		<table>
			<tr>
				<td>Username:</td>
				<td><input type="text" name="username"
					value="<%if (acb.getUsername()==null) out.print("");
							else out.print(acb.getUsername());%>" />
				</td>
			</tr>
			<tr>
				<td>Passwort:</td>
				<td><input type="password" name="password" value="" /></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="submit" name="btnLogin" value="anmelden" />Anmelden</button></td>
			</tr>
		</table>
	</form>
	<a href="http://localhost:8080/5.Semester_Praktikum/jsp/RegistrierenView.jsp">Noch kein Account? Registrieren Sie sich hier</a> <br>
	<a href="./StartView.jsp">Zurück zur Startseite</a>
</body>
</html>