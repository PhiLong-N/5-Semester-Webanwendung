<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>


 
    <button onclick="geek()">Click me!</button>
  
    <p id="g"></p>
  
    <script>
        function geek() {
            var doc;
            var result = confirm("Press a button!");
            if (result == true) {
                doc = "OK was pressed.";
            } else {
                doc = "Cancel was pressed.";
            }
            document.getElementById("g").innerHTML = doc;
        }
    </script>

</body>
</html>