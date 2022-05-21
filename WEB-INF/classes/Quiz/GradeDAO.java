package Quiz;

import java.sql.*;
import java.util.*;

public class GradeDAO {

public GradeDAO() {}

public boolean addGrade(String student_id, int quiz_id, int obtained_marks, int isAttempted) {
	boolean isAdded = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "insert into grade(student_id, quiz_id, total_marks, obtained_marks, isAttempted) values(?, ?, ?, ?, ?)";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, student_id);
		pStmt.setInt(2, quiz_id);
		QuizDAO quizDAO = new QuizDAO();
		pStmt.setInt(3, quizDAO.searchQuiz(quiz_id).getTotal_questions());
		pStmt.setInt(4, obtained_marks);
		pStmt.setInt(5, isAttempted);

		int rs = pStmt.executeUpdate();	
		
		con.close();

		if (rs > 0) {
			isAdded = true;
		}

	} catch(Exception ex){System.out.println(ex);}

	return isAdded;
}


public boolean updateGrade(GradeInfo Grade) {
	boolean isUpdated = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "update grade SET total_marks=? , obtained_marks=?, isAttempted=? where id=?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, Grade.getTotal_marks());
		pStmt.setInt(2, Grade.getObtained_marks());
		pStmt.setInt(3, Grade.getIsAttempted());
		pStmt.setInt(4, Grade.getId());
		
		int rs = pStmt.executeUpdate();	
		
		con.close();

		if (rs > 0) {
			isUpdated = true;
		}

	} catch(Exception ex){System.out.println(ex);}

	return isUpdated;
}


public GradeInfo searchGrade(String student_id, int quiz_id) {
	GradeInfo grade = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from grade where student_id = ? AND quiz_id=?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, student_id);
		pStmt.setInt(2, quiz_id);
		
		ResultSet rs = pStmt.executeQuery();

		if(rs.next()) {
			grade = new GradeInfo();
			grade.setId(rs.getInt("id"));
			grade.setStudent_id(rs.getString("student_id"));
			grade.setQuiz_id(rs.getInt("quiz_id"));
			grade.setTotal_marks(rs.getInt("total_marks"));
			grade.setObtained_marks(rs.getInt("obtained_marks"));
			grade.setIsAttempted(rs.getInt("isAttempted"));
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return grade ;
}


public ArrayList<GradeInfo> searchGrades(String student_id) {
	ArrayList<GradeInfo> grades = new ArrayList<GradeInfo>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from grade where student_id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, student_id);

		ResultSet rs = pStmt.executeQuery();

		GradeInfo grade;
		while(rs.next()) {
			grade = new GradeInfo();
			grade.setId(rs.getInt("id"));
			grade.setStudent_id(rs.getString("student_id"));
			grade.setQuiz_id(rs.getInt("quiz_id"));
			grade.setTotal_marks(rs.getInt("total_marks"));
			grade.setObtained_marks(rs.getInt("obtained_marks"));
			grade.setIsAttempted(rs.getInt("isAttempted"));
			grades.add(grade);

		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return grades;
}



public ArrayList<GradeInfo> searchGrades(int quiz_id) {
	ArrayList<GradeInfo> grades = new ArrayList<GradeInfo>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from grade where quiz_id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, quiz_id);

		ResultSet rs = pStmt.executeQuery();

		GradeInfo grade;
		while(rs.next()) {
			grade = new GradeInfo();
			grade.setId(rs.getInt("id"));
			grade.setStudent_id(rs.getString("student_id"));
			grade.setQuiz_id(rs.getInt("quiz_id"));
			grade.setTotal_marks(rs.getInt("total_marks"));
			grade.setObtained_marks(rs.getInt("obtained_marks"));
			grade.setIsAttempted(rs.getInt("isAttempted"));
			grades.add(grade);

		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return grades;
}




// end method 
}//end of class

