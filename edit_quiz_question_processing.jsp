<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,java.io.*, Session.Session" %>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>


<html>
<head>
<title>Processing</title>
</head>
<body>
    
	<jsp:useBean id="quizQuestionInfo" class="Quiz.QuizQuestionInfo" /> 
   	<jsp:setProperty name="quizQuestionInfo" property="*" />
    <% String quiz_id = request.getParameter("quiz_id") ; %>
    <% String right = request.getParameter("right_option") ; %>
    <%
        QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
        boolean updated = quizQuestionDAO.updateQuestion( quizQuestionInfo );
        if(updated) {
            response.sendRedirect("view_quiz.jsp?id=" + quiz_id);
        }
    %>
</body>
</html>