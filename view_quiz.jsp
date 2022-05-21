<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*, java.util.*, Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>

<html>
<head>
<title>View Quiz</title>
<link rel="stylesheet" href="css/view_quiz.css">
</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="view_quizzes" />
    </jsp:include>

    <div class="content">
        <%
            int id = Integer.parseInt(request.getParameter("id"));
            String add_question_url = "add_quiz_question.jsp?quiz_id=" + request.getParameter("id"); 
            String delete_quiz_url = "Controller?action=delete_quiz&quiz_id=" + request.getParameter("id");
            String see_students_marks = "see_students_marks.jsp?quiz_id=" + request.getParameter("id");
            String upload_quiz = "Controller?action=upload_quiz&quiz_id=" + request.getParameter("id");
            String edit_question_url, delete_question_url;

            QuizDAO quizDAO = new QuizDAO();
            QuizInfo quizInfo = quizDAO.searchQuiz(id);
            boolean uploaded = (quizInfo.getUploaded() == 1);

        %>

        <h1> <%= quizInfo.getName() %> </h1>
        <% if(uploaded) { %>
            <div class="warning">Note: You have uploaded the quiz. So you can't make changes to it anymore.</div>
        <%  } %>
        
        <%
            ArrayList<QuizQuestionInfo> questions = quizDAO.fetchQuestions(id);
            for(int i=0;i<questions.size();i++) {
                QuizQuestionInfo question = questions.get(i);
        %>
        
        <div class="mcq-container">
                <h3> <%= i+1 %>. <%= question.getQuestion() %> </h3>
                <ol type="A">
                    <li><%= question.getOption1() %></li>
                    <li><%= question.getOption2() %></li>
                    <li><%= question.getOption3() %></li>
                    <li><%= question.getOption4() %></li>
                </ol>
                <ul type="none"> <li> Answer: <%= question.getRight_option() %> </li> </ul>
                <div>
                
                    <%
                        if(!uploaded) {
                        edit_question_url =  "edit_quiz_question.jsp?quiz_id=" + request.getParameter("id") + "&id=" + Integer.toString(question.getId()); 
                        delete_question_url = "Controller?action=delete_question&quiz_id=" + request.getParameter("id") + "&id=" + Integer.toString(question.getId()); 
                    %>
                        <a class="functional-link-type-2" href=<%= edit_question_url %>>edit</a>
                        <a class="functional-link-type-2" href=<%= delete_question_url %>>delete</a>
                    <%
                        }
                    %>

                </div>
        </div>
                    
        <%
            }
        %>

            
        <%   if(uploaded) {    %>
            <a class="functional-link" href= <%= see_students_marks %> > See Marksheet </a>
            <a class="functional-link" href= <%= delete_quiz_url %> > Delete Quiz </a> 
        <%  } else { %>
            <a class="functional-link" href= <%= add_question_url %> > Add Question </a> 
            <a class="functional-link" href= <%= delete_quiz_url %> > Delete Quiz </a> 
            <a class="functional-link" id="uploadd" href=<%= upload_quiz %> > Upload Quiz </a>
        <%  } %>

            <br><br>
    </div>
</body>
</html>