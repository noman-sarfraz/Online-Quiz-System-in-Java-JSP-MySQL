<% 
    String error = request.getParameter("error");
    String message = "";
    if(error != null)  {
      if(error.equals("validationError")) {
        message = "Error! No field can be empty. Fill all the fields.";
      } else if(error.equals("authenticationError")) {
        message = "Error! Please provide valid credentials.";
      } 
    }
    
%>

<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="css/login.css">

</head>
<body>
    <div class="content">
        <h1>Login</h1>

        <div class="error"><%= message %></div>


        <div class="container">
            <form action="Login" method="POST">
                <table>
                    <tr>
                        <td  ></td>
                        <td >Email:</td>
                    </tr>
                    
                    <tr>
                        <td ></td>
                        <td ><input type="email" name="email"  required></td>
                    </tr>
                    
                    <tr>
                        <td  ></td>
                        <td >Password:</td>
                    </tr>
                    
                    <tr>
                        <td ></td>
                        <td ><input type="password" name="password"  required ></td>
                    </tr> 
                    
                    <tr>
                        <td  ></td>
                        <td >User-Type:</td>
                    </tr>
                    
                    <tr> 
                        <td ></td> 
                        <td>
                            <input type="radio" name="userType" value="1"   required> Teacher 
                            <input type="radio" name="userType" value="2"   required> Student 
                        </td> 
                    </tr>

                    <tr>
                        <td></td>
                        <td></td>
                    </tr>

                    <tr>
                        <td></td>
                        <td><input type="submit" class="submit" value="Submit" style="width: 100%; padding: 3%;"></td>
                    </tr>
                </table>
                
            </form>
        </div>
    </div>

</body>
</html>