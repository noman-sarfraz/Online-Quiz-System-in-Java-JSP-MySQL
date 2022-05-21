<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,java.io.*,Session.Session" %>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>


<html>
<head>
<title>Processing</title> 
</head>
<body>
    
    
	<jsp:useBean id="quizQuestionInfo" class="Quiz.QuizQuestionInfo" />
   	<jsp:setProperty name="quizQuestionInfo" property="*" />
    <% String request_type = request.getParameter("request_type") ; %>
    <% String quiz_id = request.getParameter("quiz_id") ; %>
    <%
        QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
        boolean added = quizQuestionDAO.addQuizQuestion( quizQuestionInfo );
        if(added) {
            if(request_type.equals("Save")) {
                response.sendRedirect("view_quiz.jsp?id=" + quiz_id);
            } else if(request_type.equals("Save and Add Another")) {
                response.sendRedirect("add_quiz_question.jsp?quiz_id=" + quiz_id);
            }
        } else {
            
        }
    %>
</body>
</html>