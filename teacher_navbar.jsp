<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>

<%-- which nav-link should be active? --%>
<%
    String active = request.getParameter("active");
    String nav1="", nav2="", nav3="";
    if("view_quizzes".equals(active)) {
      nav1 = "active";
    } else if("marksheets".equals(active)) {
      nav2 = "active";
    } else if("create_quiz".equals(active)) {
      nav3 = "active";
    }
%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/teacher_navbar.css">
</head>
<body id="nav-body">
  <div id="navbar">
    <a href="quiz_list.jsp" class=<%= nav1 %> > Manage Quizzes </a>
    <a href="all_marksheets.jsp" class=<%= nav2 %>> Marksheets </a>
    <a href="create_quiz.jsp" class=<%= nav3 %>> Create Quiz</a>
    <a href="logout.jsp" style="float: right;">Logout</a>
  </div>
</body>
</html>
