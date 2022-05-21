<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*, java.util.*, Session.Session"%>

<%-- Session --%>
<%
    String student_id="";
    if (Session.authenticateStudent(request)) {
        student_id = Session.getStudentId(request);
    } else {
        response.sendRedirect("login.jsp");
    }
%>


<html>
<head>
<title>Attempt Quiz</title>
<link rel="stylesheet" href="css/attempt_quiz.css">

</head>
<body>
    <jsp:include page="student_navbar.jsp">	
        <jsp:param name="active" value="unattempted_quizzes" />
    </jsp:include>

    <div class="content">
        <% 
            String quiz_id = request.getParameter("id");
            int id = Integer.parseInt(quiz_id);

            GradeDAO gradeDAO = new GradeDAO();
            GradeInfo grade = gradeDAO.searchGrade(student_id, id);
            QuizDAO quizDAO = new QuizDAO();
            if(grade != null) { 
        %>
            <h3>You 1 attempts are finished.</h3>
            <h4> Your score: <%= grade.getObtained_marks() %></h4>
            <% 
            } else {
                gradeDAO.addGrade(student_id, id, 0, 0);
            %>
            
        <h1> <%= new QuizDAO().searchQuizName(id) %> </h1>
        <form action="Controller" method="POST">
        <%
            
            ArrayList<QuizQuestionInfo> questions = quizDAO.fetchQuestions(id);
            String question_no;
            int optionId = 0;
            int i;
            for(i=0;i<questions.size();i++) {   
                QuizQuestionInfo question = questions.get(i);
                
        %>
            <div class="mcq-container">
                <h3> <%= i+1 %> . <%= question.getQuestion() %> </h3>
                <% 
                    question_no  = "question_" + Integer.toString(question.getId());
                %>
                <ul style="list-style:none">
                    <li>
                        <input type="radio" name=<%= question_no %> id=<%= ++optionId %> value=<%= question.getOption1() %>>
                        <label for=<%= optionId %>><%= question.getOption1() %></label>
                    </li>
                    <li>
                        <input type="radio" name=<%= question_no %> id=<%= ++optionId %> value=<%= question.getOption2() %>>
                        <label for=<%= optionId %>><%= question.getOption2() %></label>
                    </li>
                    <li>
                        <input type="radio" name=<%= question_no %> id=<%= ++optionId %> value=<%= question.getOption3() %>>
                        <label for=<%= optionId %>><%= question.getOption3() %></label>
                    </li>
                    <li>
                        <input type="radio" name=<%= question_no %> id=<%= ++optionId %> value=<%= question.getOption4() %>>
                        <label for=<%= optionId %>><%= question.getOption4() %></label>
                    </li>
                </ul>
            </div>
            
            <%
                }
            %>
            
            <input type="hidden" name="action" value="grade_quiz" >
            <input type="hidden" name="student_id" value= <%= student_id %>>
            <input type="hidden" name="quiz_id" value= <%= quiz_id %>>
            <input type="hidden" name="total_marks" value= <%= i %>>
            <input type="submit" value="Submit Quiz" class="functional-link" >
            </form> 
        <%
            }
        %>
    </div>
</body>
</html>