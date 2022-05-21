<%@page isErrorPage="true" %> 
<%@page import = "java.sql.SQLException" %> 

<html>
<head>
<title>Error Page</title>
<link rel="stylesheet" href="css/error_page.css">
</head>
<body>
    <div class="content">
        <h1>Error Page</h1>
        <div class="container">
                <% if(exception != null) { %>
                <h3> 
                <% if (exception instanceof SQLException) { %> 

                An SQL Exception 

                <% } else if (exception instanceof ClassNotFoundException){ %> 

                A Class Not Found Exception 

                <%} else { %> 
                An Exception 

                <% } %> 


                occured while performing operations.</h3> 

                <p>The Error Message was <%= exception.getMessage() %>
                </p>
                <p > Please Try Again Later! </p> 
            <% } else { %>
            <h4>No error</h4>
            <% } %>

            <h3> <a href="index.jsp" class="submit" > Return to Home Page </a> </h3> 

            
        </div>
    </div>

</body>
</html>