<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,java.util.*,Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>


<html>
<head>
<title>Marksheets</title>
<link rel="stylesheet" href="css/all_marksheets.css">

</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="marksheets" />
    </jsp:include>
    
    <h1>Marksheets </h1>
    <ul>
        <%
            QuizDAO quizDAO = new QuizDAO();
            ArrayList<QuizInfo> quizzes = quizDAO.fetchAvailableQuizzes();
            String quiz_marksheet_url;
            for(int i=0;i<quizzes.size();i++) {
                QuizInfo quiz = quizzes.get(i);
                quiz_marksheet_url = "see_students_marks.jsp?quiz_id=" + Integer.toString(quiz.getId());
        %>
        <li>
            <b> <%= quiz.getName() %> </b>
            <a class="functional-link-type-2" href= <%= quiz_marksheet_url %>> Marksheet </a>
        </li>
        <%
            }
        %>
        
        

    </ul>
</body>
</html>