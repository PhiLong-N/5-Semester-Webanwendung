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

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>

<h4>Erstellen Sie bitte ihren Account!</h4>

<form action="./RegistrierenAppl.jsp" method="get">

<table>
	<tr>
		<td>Email-Adresse: </td>
		<td><input type="text" name="email" value="<%if (acb.getEmail()==null)out.print("");
														else out.print(acb.getEmail()); %>" /></td>
		<td><%out.print(acb.getEmailMsg()); %></td>
	</tr>
	<tr>
		<td>Benutzername: </td>
		<td><input type="text" name="username" value="<%if (acb.getUsername()==null)out.print("");
														else out.print(acb.getUsername()); %>" /></td>
		<td><%out.print(acb.getUsernameMsg()); %></td>
	</tr>
	<tr>
		<td>Passwort: </td>
		<td><input type="password" name="password" value="" /></td>
		<td><%out.print(acb.getPasswortMsg()); %></td>
	</tr>
	<tr>
		<td>Passwort Bestätigen: </td>
		<td><input type="password" name="passwordwdh" value="" /></td>
	</tr>
	<tr>
		<td>Adresse: </td>
		<td><input type="text" name="adresse" value="<%if (acb.getAdresse()==null)out.print("");
														else out.print(acb.getAdresse()); %>" /></td>
	</tr>
	<tr>
		<td>Stadt: </td>
		<td><input type="text" name="stadt" value="<%if (acb.getStadt()==null)out.print("");
														else out.print(acb.getStadt()); %>" /></td>
	</tr>
	<tr>
		<td>Postleitzahl: </td>
		<td><input type="text" name="plz" value="" /></td>
	</tr>
	<tr>
		<td></td>
		<td><button type="submit" name="btnRegistrieren" value="registrieren" />Registrieren</button></td>
	</tr>
</table>

</form>
<br><br>
	<a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>