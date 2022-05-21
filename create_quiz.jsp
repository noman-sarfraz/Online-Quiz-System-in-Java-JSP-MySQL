<%-- imports --%>
<%@page errorPage="error_page.jsp" %> 
<%@page import="Session.Session"%>

<%-- Search --%>
<% if(!Session.authenticateTeacher(request)) { response.sendRedirect("login.jsp"); } %>

<html>
<head>
<title>Create Quiz</title>
<link rel="stylesheet" href="css/create_quiz.css">

</head>
<body>
    <jsp:include page="teacher_navbar.jsp">	
        <jsp:param name="active" value="create_quiz" />
    </jsp:include>

    <div class="content">
        <h1> Create Quiz </h1>

        <div class="container">
            <form action="Controller" method="POST">     
                <table>
                    <tr>
                        <td></td>
                        <td>Name:</td>
                    </tr>

                    <tr>
                        <td ></td>
                        <td ><input type="text" name="name" required></td>
                    </tr>

                    <input type="hidden" name="action" value="create_quiz" >
                    
                    <tr>
                        <td></td>
                        <td><input name="request_type" class="submit" type="submit" value="Create Quiz" style="width: 100%; padding: 1%;"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>

</body>
</html>