<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Quiz.*,Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>


<html>
<head>
<title>Edit Question</title>
<link rel="stylesheet" href="css/edit_quiz_question.css">

<script>
    function resolve_answer_value () {
        let option1 = document.getElementById("option1").value;
        let option2 = document.getElementById("option2").value;
        let option3 = document.getElementById("option3").value; 
        let option4 = document.getElementById("option4").value;
        let answer = document.getElementById("answer");
        let right_option = document.getElementById("right_option");
        
        // console.log(option2);

        if(answer.value == "option1") {
            right_option.value = option1; 
        } else if(answer.value == "option2") {
            right_option.value = option2;
        } else if(answer.value == "option3") {
            right_option.value = option3;
        } else if(answer.value == "option4") {
            right_option.value = option4;
        }
    }
</script>

</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="view_quizzes" />
    </jsp:include>

    <%
        int id = Integer.parseInt(request.getParameter("id"));
        int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
        QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
        QuizQuestionInfo Question = quizQuestionDAO.fetchQuestion(quiz_id, id);
        // 
        String selected = "selected";
        String nothing = "";
    %>
    <div class="content">
        <h1>Edit Question</h1>

        <div class="container">
            <form action='edit_quiz_question_processing.jsp' method='POST'>
                <table>
                    <tr> 
                        <td></td> 
                        <td>Question: </td>
                    </tr>
                    
                    <tr> 
                        <td></td> 
                        <td ><textarea name='question' cols="21" rows="5" required><%= Question.getQuestion() %></textarea></td>
                    </tr>
                    
                    <tr>
                        <td  align='right'>A: </td>
                        <td ><input type='text' name='option1' id="option1" required value=<%= Question.getOption1() %> ></td>
                    </tr> 

                    <tr>
                        <td  align='right'>B: </td>
                        <td ><input type='text' name='option2'  id="option2" required value=<%= Question.getOption2() %> ></td>
                    </tr> 

                    <tr>
                        <td  align='right'>C: </td>
                        <td ><input type='text' name='option3' id="option3" required value=<%= Question.getOption3() %> ></td>
                    </tr> 

                    <tr>
                        <td  align='right'>D: </td>
                        <td ><input type='text' name='option4' id="option4" required value=<%= Question.getOption4() %> ></td>
                    </tr> 

                    <tr>
                        <td  align='right'></td>
                        <td >Right Option: 
                            <select id="answer" required>
                                <option value="option1" <%= Question.getOption1().equals(Question.getRight_option()) ? selected: nothing %>>A</option>
                                <option value="option2" <%= Question.getOption2().equals(Question.getRight_option()) ? selected: nothing %>>B</option>
                                <option value="option3" <%= Question.getOption3().equals(Question.getRight_option()) ? selected: nothing %>>C</option>
                                <option value="option4" <%= Question.getOption4().equals(Question.getRight_option()) ? selected: nothing %>>D</option>
                                
                            </select>
                        </td>
                    </tr> 

                    <tr>
                        <td></td>
                        <td></td>
                    </tr>

                    <%-- Hidden field --%>
                    <% String quizId = request.getParameter("quiz_id"); %>
                    <% String Id = request.getParameter("id"); %>
                    <input type="hidden" name="quiz_id" value= <%= quizId %> >
                    <input type="hidden" name="id" value= <%= Id %> >
                    <input type="hidden" name="right_option" id="right_option" value="">

                    <tr>
                        <td></td>
                        <td><input type="submit" class="submit" onclick="resolve_answer_value()" value='Save' style='width: 100%; padding: 1%;'></td>
                    </tr>
                    
                </table>
            </form>
        </div>
    </div>
</body>
</html>