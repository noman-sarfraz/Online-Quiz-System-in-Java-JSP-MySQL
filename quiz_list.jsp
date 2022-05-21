<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,java.util.*,Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>

<html>
<head>
<title>Quizzes</title>
<link rel="stylesheet" href="css/quiz_list.css">

</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="view_quizzes" />
    </jsp:include>

    <h1>Quizzes</h1>
    <ul>
        <%
            QuizDAO quizDAO = new QuizDAO();
            ArrayList<QuizInfo> quizzes = quizDAO.fetchQuizzes();
            String view_url, quizStatus = "";
            for(int i=0;i<quizzes.size();i++) {   
                QuizInfo quiz = quizzes.get(i);
                view_url = "view_quiz.jsp?id=" + Integer.toString(quiz.getId());
                if(quiz.getUploaded() == 1) {
                    quizStatus = "Uploaded";
                } else {
                    quizStatus = "Private ";
                }
        %>
        <a href=<%= view_url %>>
            <li>
                <b> <%= quiz.getName() %> </b>
                <div class="functional-link-type-2"> <%= quizStatus %> </div>

            </li>
        </a>
        <%
            }
        %>
        
        
        <a href="create_quiz.jsp" class="functional-link" > Create a New Quiz </a>

    </ul>
</body>
</html>