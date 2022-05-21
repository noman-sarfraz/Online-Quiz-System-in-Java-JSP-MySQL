<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>

<%-- Session --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>



<html>
<head>
<title>Add Question</title>
<link rel="stylesheet" href="css/add_quiz_question.css">

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

    <div class="content">
        <h1>Add Question</h1>

        <div class="container">
            <form action='add_quiz_question_processing.jsp' method='POST'>
                <table>
                    <tr> 
                        <td  align='right'></td> 
                        <td >Question:</td>
                    </tr>
                    
                    <tr> 
                        <td  align='right'></td> 
                        <td ><textarea name='question' cols="21" rows="5" required></textarea></td>
                    </tr>
                    
                    <tr>
                        <td  align='right'>A: </td>
                        <td ><input type='text' name='option1' id="option1" required></td>
                    </tr> 

                    <tr>
                        <td  align='right'>B: </td>
                        <td ><input type='text' name='option2'  id="option2" required></td>
                    </tr> 

                    <tr>
                        <td  align='right'>C: </td>
                        <td ><input type='text' name='option3' id="option3" required></td>
                    </tr> 

                    <tr>
                        <td  align='right'>D: </td>
                        <td ><input type='text' name='option4' id="option4" required></td>
                    </tr> 

                    <tr>
                        <td></td>
                        <td >Right Option: 
                            <select id="answer" required>
                                <option value="option1" >A</option>
                                <option value="option2" >B</option>
                                <option value="option3" >C</option>
                                <option value="option4" >D</option>
                                
                            </select>
                        </td>
                    </tr> 

                    <tr>
                        <td></td>
                        <td></td>
                    </tr>

                <%-- Hidden field --%>
                    <% String quizId = request.getParameter("quiz_id"); %>
                    <input type="hidden" name="quiz_id" value= <%= quizId %> >
                    <input type="hidden" name="right_option" id="right_option" value="">

                    <tr>
                        <td></td>
                        <td><input type="submit" class="submit" onclick="resolve_answer_value()" name='request_type' value='Save' style='width: 100%; padding: 1%;'></td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><input type="submit" class="submit" onclick="resolve_answer_value()" name='request_type' value='Save and Add Another' style='width: 100%; padding: 1%;'></td>
                    </tr>

                    
                </table>
            </form>
        </div>
    </div>
</body>
</html>