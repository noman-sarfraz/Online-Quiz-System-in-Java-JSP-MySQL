import java.io.*;  
import javax.servlet.*; 
import javax.servlet.http.*;
import Quiz.TeacherDAO;
import Quiz.StudentDAO;

public class Login extends HttpServlet {

public void doPost (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String userType = request.getParameter("userType");
	if("".equals(email) || "".equals(password) || userType == null) {
		response.sendRedirect("login.jsp?error=validationError");
		return;
	}

// Authentication
	if(userType.equals("1")) {
		TeacherDAO teacherDAO = new TeacherDAO();
		if(teacherDAO.verifyTeacher(email, password)) {	
			HttpSession session = request.getSession(true);
			session.setAttribute("userType", userType);
			session.setAttribute("email", email);
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp?error=authenticationError");
			return;

		}

	} else if(userType.equals("2")) {
		StudentDAO studentDAO = new StudentDAO();
		if(studentDAO.verifyStudent(email, password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userType", userType);
			session.setAttribute("email", email);
			response.sendRedirect("index.jsp");
		} else {
			response.sendRedirect("login.jsp?error=authenticationError");
			return;

		}
	}
}


}


