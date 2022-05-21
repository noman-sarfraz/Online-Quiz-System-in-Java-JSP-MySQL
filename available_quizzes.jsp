<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,java.util.*,Session.Session"%>

<%-- Session --%>
<% 
    String student_id = "";
    if(!Session.authenticateStudent(request)) { 
        response.sendRedirect("login.jsp"); 
    } else {
        student_id = Session.getStudentId(request);
    } 
%>


<html>
<head>
<title>Quizzes</title>
<link rel="stylesheet" href="css/available_quizzes.css">

</head>
<body>
    <jsp:include page="student_navbar.jsp">	
        <jsp:param name="active" value="available_quizzes" />
    </jsp:include>

    <h1>Available Quizzes</h1>
    <ul>
        <%
            QuizDAO quizDAO = new QuizDAO();
            QuizInfo quiz = null;
            GradeDAO gradeDAO = new GradeDAO();
            GradeInfo grade = null;
            ArrayList<QuizInfo> quizzes = quizDAO.fetchAvailableQuizzes();
            String attempt_url;
            for(int i=0;i<quizzes.size();i++) {
                quiz = quizzes.get(i);
                grade = gradeDAO.searchGrade(student_id, quiz.getId());

        %>

        <li>
            <b> <%= quiz.getName() %> </b>
            <%  if(grade != null && (grade.getIsAttempted() == 0 || grade.getIsAttempted() == 1)) { %>
            <div class="functional-link-type-2" > <%= grade.getObtained_marks() %> / <%= grade.getTotal_marks() %> </div>
            <% } else { attempt_url = "attempt_quiz.jsp?id=" + Integer.toString(quiz.getId()); %>
            <a class="functional-link-type-2" href= <%= attempt_url %>> Attempt </a>
            <% } %> 

        </li>

        <%
            }
        %>
        
        

    </ul>
</body>
</html>