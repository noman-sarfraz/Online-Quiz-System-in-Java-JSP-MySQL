package Session;

import javax.servlet.*;
import javax.servlet.http.*;

public class Session {

public static boolean isSessionSet(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if(session != null) {
		return true;
	}
	return false;
}

public static void invalidate(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	if(session != null) {
		session.invalidate();
	}
}

public static boolean authenticateTeacher(HttpServletRequest request) {
	boolean isAuthenticated = false;
	HttpSession session = request.getSession(false);
	if(session != null) {
		String userType = (String) session.getAttribute("userType");
		if("1".equals(userType)) {
			isAuthenticated = true;
		}
	}
	return isAuthenticated;
}

public static boolean authenticateStudent(HttpServletRequest request) {
	boolean isAuthenticated = false;
	HttpSession session = request.getSession(false);
	if(session != null) {
		String userType = (String) session.getAttribute("userType");
		if("2".equals(userType)) {
			isAuthenticated = true;
		}
	}
	return isAuthenticated;
}

// use after authentication
public static String getStudentId(HttpServletRequest request) {
	HttpSession session = request.getSession(false);
	return (String) session.getAttribute("email");
}



// end method 
}//end of class

