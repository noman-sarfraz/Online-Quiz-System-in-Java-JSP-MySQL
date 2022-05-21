<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>
<%
    Session.invalidate(request);
    response.sendRedirect("login.jsp");
%>