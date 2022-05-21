<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*, java.util.*, Session.Session"%>

<%
    String student_id="";
    if(Session.authenticateStudent(request)) {
        student_id = Session.getStudentId(request);
    } else {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
<title>Marksheet</title>
<link rel="stylesheet" href="css/quizzes_marksheet.css">
</head>
<body>
    <jsp:include page="student_navbar.jsp">	
        <jsp:param name="active" value="marksheet" />
    </jsp:include>

    <h1>Marksheet</h1>
    <ul>
        <%
            GradeDAO gradeDAO = new GradeDAO();
            ArrayList<GradeInfo> grades = gradeDAO.searchGrades(student_id);
            QuizDAO quizDAO = new QuizDAO();
            String quiz_name = "";
            int total_marks, obtained_marks;

            for(int i=0;i<grades.size();i++) {
                GradeInfo grade = grades.get(i);
                quiz_name = quizDAO.searchQuizName(grade.getQuiz_id());
                total_marks = grade.getTotal_marks();
                obtained_marks = grade.getObtained_marks();
        %>

            <li>
                <b> <%= quiz_name %> </b>
                <a class="functional-link-type-2" > <%= obtained_marks %> / <%= total_marks %> </a>
            </li>

        <%
            }
        %>
        

    </ul>
</body>
</html>