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
<link rel="stylesheet" href="css/unattempted_quizzes.css">

</head>
<body>
    <jsp:include page="student_navbar.jsp">	
        <jsp:param name="active" value="unattempted_quizzes" />
    </jsp:include>

    <h1>Unattempted Quizzes</h1>
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

        <%  
            if(grade == null) { 
                attempt_url = "attempt_quiz.jsp?id=" + Integer.toString(quiz.getId());
        %>
        <li>    
            <b> <%= quiz.getName() %> </b>
            <a class="functional-link-type-2" href= <%= attempt_url %>> Attempt </a>
        </li>
        <%
            } 
        %> 

        <%
            }
        %>
        
        

    </ul>
</body>
</html>