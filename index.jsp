<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>

<%
    if(Session.authenticateTeacher(request))        { response.sendRedirect("quiz_list.jsp"); }
    else if(Session.authenticateStudent(request))   { response.sendRedirect("available_quizzes.jsp"); }
    else                                            { response.sendRedirect("login.jsp"); }

%>