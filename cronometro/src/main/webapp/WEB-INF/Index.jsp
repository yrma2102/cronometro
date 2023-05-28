<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page import="java.util.*" %>
        <%@ page import = "javax.servlet.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-2">
  <div class="row">
    <div class="col-sm">
     <a href="?start=<%= (new java.util.Date()).toLocaleString()%>">Start</a>
    </div>
    <div class="col-sm">
      <a href="?stop=<%= (new java.util.Date()).toLocaleString()%>">Stop</a>
    </div>
    <div class="col-sm">
      <a href="?reset">Reset</a>
    </div>
  </div>
    <div class="row mt-3">
    <div class="col-sm">
      <p>Start: ${fechaInicio}</p>
    </div>
    <div class="col-sm">
         <p>Current time: ${fechaCurrent}</p>
    </div>
    <div class="col-sm">
        <p>Running time: ${runningTime}</p>
    </div>
  </div>

  
  <table class="table table-striped mt-3">
	  <thead>
	    <tr>
	      <th scope="col">START</th>
	      <th scope="col">STOP</th>
	      <th scope="col">TOTAL</th>
	    </tr>
	  </thead>
	  <tbody>
	     <c:forEach items="${listTimer}" var="current">
		  <tr>
		       <td>${current.getHourInicio()}</td>
				    <td>${current.getHourFin()}</td>
				    <td>${current.getHourTotal()}</td>
		  </tr>
  		</c:forEach>
	  </tbody>
	</table>
</div>
</body>
</html>