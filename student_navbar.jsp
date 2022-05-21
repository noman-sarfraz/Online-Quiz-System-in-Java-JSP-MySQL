<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateStudent(request)) { response.sendRedirect("login.jsp"); } %>

<%-- which nav-link should be active? --%>
<%
    String active = request.getParameter("active");
    String nav1="", nav2="", nav3="";
    if("available_quizzes".equals(active)) {
      nav1 = "active";
    } else if("unattempted_quizzes".equals(active)) {
      nav2 = "active";
    } else if("marksheet".equals(active)) {
      nav3 = "active";
    } 
%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/student_navbar.css">
</head>
<body id="nav-body">
  <div id="navbar">
    <a href="available_quizzes.jsp" class=<%= nav1 %> > Available Quizzes </a>
    <a href="unattempted_quizzes.jsp" class=<%= nav2 %>> Unattempted Quizzes </a>
    <a href="quizzes_marksheet.jsp" class=<%= nav3 %>> Marksheet</a>
    <a href="logout.jsp" style="float: right;">Logout</a>
  </div>
</body>
</html>
