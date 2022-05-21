<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*, java.util.*, Session.Session"%>

<%-- Session --%>
<%
    String student_id="";
    int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
    if (Session.authenticateStudent(request)) {
        student_id = Session.getStudentId(request);
    } else {
        response.sendRedirect("login.jsp");
    }
%>


<html>
<head>
<title>Quiz Marks</title>
<link rel="stylesheet" href="css/quiz_marks.css">

</head>
<body>
    <jsp:include page="student_navbar.jsp">	
        <jsp:param name="active" value="marksheet" />
    </jsp:include>

    <div class="content">
        <h1>Quiz Result</h1>
        <div class="container">
            <% 
                String quiz_name = new QuizDAO().searchQuizName(quiz_id);
                GradeDAO gradeDAO = new GradeDAO();
                GradeInfo grade = gradeDAO.searchGrade(student_id, quiz_id);
            %>
            <h3>Quiz Name: <%= quiz_name %> <h3>
            <h3>Quiz Marks: <%= grade.getObtained_marks() %> / <%= grade.getTotal_marks() %> <h3>

        </div>
    </div>

</body>
</html>