import java.io.*;  
import javax.servlet.*; 
import javax.servlet.http.*;
import Quiz.StudentDAO;
import Quiz.StudentInfo;
import Quiz.TeacherDAO;

public class Signup extends HttpServlet {

public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String join_code = request.getParameter("join_code");
	if("".equals(name) || "".equals(email) || "".equals(password) || "".equals(join_code)) {
		response.sendRedirect("signup.jsp?error=validationError");
		return;
	}

	TeacherDAO teacherDAO = new TeacherDAO();
	StudentDAO studentDAO = new StudentDAO();

	if(studentDAO.searchStudent(email) != null) {
		response.sendRedirect("signup.jsp?error=duplicateEmailError");
		return;
	}

	if(teacherDAO.verifyJoinCode(join_code)) {
		StudentInfo student = new StudentInfo();
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		boolean isAdded = studentDAO.addStudent(student);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("userType", "2");
		session.setAttribute("email", email);
		response.sendRedirect("index.jsp");
	} else {
		response.sendRedirect("signup.jsp?error=joinCodeError");
		return;
	}

}



} // end of class

