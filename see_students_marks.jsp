<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*, java.util.*, Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>

<html>
<head>
<title>Marks of students</title>
<link rel="stylesheet" href="css/see_students_marks.css">
</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="marksheets" />
    </jsp:include>
    

    <div class="">
        <%
            int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
            
            GradeDAO gradeDAO = new GradeDAO();
            ArrayList<GradeInfo> grades = gradeDAO.searchGrades(quiz_id);
            GradeInfo grade = null;

            QuizDAO quizDAO = new QuizDAO();
            String quiz_name = quizDAO.searchQuizName(quiz_id);
            StudentDAO studentDAO = new StudentDAO();
            StudentInfo student;
            
            String student_name = "";
            String student_email = "";
            int total_marks, obtained_marks;
        %>

        <h1>Marksheet</h1>
        <h2>Quiz: <%= quiz_name %></h2>
        <ul>
            <%
                for(int i=0;i<grades.size();i++) {
                    grade = grades.get(i);
                    student = studentDAO.searchStudent(grade.getStudent_id());
                    student_name = student.getName();
                    student_email = student.getEmail();
                    total_marks = grade.getTotal_marks();
                    obtained_marks = grade.getObtained_marks();
            %>

                <li>
                    <%= student_name %> (<%= student_email %>)
                    <a class="functional-link-type-2" > <%= obtained_marks %> / <%= total_marks %> </a>
                </li>

            <%
                }
            %>
            

        </ul>
    </div>
</body>
</html>