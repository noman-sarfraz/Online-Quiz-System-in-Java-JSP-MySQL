<% 
    String error = request.getParameter("error");
    String message = "";
    if(error != null)  {
      if(error.equals("validationError")) {
        message = "Error! No field can be empty. Fill all the fields.";
      } else if(error.equals("duplicateEmailError")) {
        message = "Error! Duplicate email address. Provide another email address.";
      } else if(error.equals("joinCodeError")) {
        message = "Error! Your Join Code was not valid. Try again.";
      } 
    }
    
%>

<html>
<head>
<title>Signup</title>
<link rel="stylesheet" href="css/signup.css">

</head>
<body>
  <div class="content">
    <h1>Signup</h1>

    <div class="error"><%= message %></div>
    
    <div class="container">
      <form action="Signup" method="POST">
        <table>
          <tr>
            <td  ></td>
            <td >Name:</td>
        </tr>
        
        <tr>
            <td ></td>
            <td ><input type="text" name="name" required></td>
        </tr>
        
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
              <td ><input type="password" name="password"  required></td>
          </tr>
          
          <tr>
              <td  ></td>
              <td >Join Code:</td>
          </tr>
          
          <tr>
              <td ></td>
              <td ><input type="text" name="join_code"  required ></td>
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