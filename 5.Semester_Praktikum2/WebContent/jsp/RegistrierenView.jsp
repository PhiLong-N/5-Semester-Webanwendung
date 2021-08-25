<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrierung</title>
</head>
<body>
<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />

Erstellen Sie bitte ihren Account!

<form action="./RegistrierenAppl.jsp" method="get">

<table>
	<tr>
		<td>Email-Adresse: </td>
		<td><input type="text" name="email" value="<%if (acb.getEmail()==null)out.print("");
														else out.print(acb.getEmail()); %>" /></td>
	</tr>
	<tr>
		<td>Benutzername: </td>
		<td><input type="text" name="username" value="<%if (acb.getUsername()==null)out.print("");
														else out.print(acb.getUsername()); %>" /></td>
	</tr>
	<tr>
		<td>Passwort: </td>
		<td><input type="password" name="password" value="" /></td>
	</tr>
	<tr>
		<td>Passwort Bestätigen: </td>
		<td><input type="password" name="passwordwdh" value="" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" name="btnRegistrieren" value="registrieren" /></td>
	</tr>
</table>

</form>

<a href="./StartView.jsp">Zurück zur Startseite</a>

</body>
</html>