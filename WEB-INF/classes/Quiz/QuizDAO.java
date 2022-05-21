package Quiz;

import java.sql.*;
import java.util.*;

public class QuizDAO {

public QuizDAO() {}

public int addQuiz(String quizName) {
	int id = -1;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "insert into quiz(name,total_questions,uploaded) values(?,0,0)";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, quizName);
		
		int r = pStmt.executeUpdate();
		
		if (r > 0) {
			ResultSet rs=pStmt.getGeneratedKeys();
			if(rs.next()) {
				id=rs.getInt(1);
			}
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return id;
}


public String searchQuizName(int quiz_id) {
	String name = "";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from quiz where id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, quiz_id);
		
		ResultSet rs = pStmt.executeQuery();	
		
		if (rs.next()) {
				name = rs.getString("name");
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return name;
}


public QuizInfo searchQuiz(int quiz_id) {
	QuizInfo quiz = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from quiz where id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, quiz_id);
		
		ResultSet rs = pStmt.executeQuery();	
		quiz = new QuizInfo();

		if (rs.next()) {
				quiz.setId(rs.getInt("id"));
				quiz.setName(rs.getString("name"));
				quiz.setUploaded(rs.getInt("uploaded"));
				quiz.setTotal_questions(rs.getInt("total_questions"));
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return quiz;
}



public boolean uploadQuiz(int quiz_id) {
	boolean uploaded = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "update quiz SET uploaded = 1 where id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);

		pStmt.setInt(1, quiz_id);

		int rs = pStmt.executeUpdate();

		if (rs > 0) {
				uploaded = true;
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return uploaded;
}



public ArrayList<QuizInfo> fetchQuizzes() {
	ArrayList<QuizInfo> quizzes = new ArrayList<QuizInfo>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from quiz order by id desc";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		QuizInfo quiz;
		while(rs.next()) {
			quiz = new QuizInfo();
			quiz.setId(rs.getInt("id"));
			quiz.setName(rs.getString("name"));
			quiz.setUploaded(rs.getInt("uploaded"));
			quiz.setTotal_questions(rs.getInt("total_questions"));
			quizzes.add(quiz);
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return quizzes;
}


public ArrayList<QuizInfo> fetchAvailableQuizzes() {
	ArrayList<QuizInfo> quizzes = new ArrayList<QuizInfo>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from quiz where uploaded = 1 order by id desc";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		QuizInfo quiz;
		while(rs.next()) {
			quiz = new QuizInfo();
			quiz.setId(rs.getInt("id"));
			quiz.setName(rs.getString("name"));
			quizzes.add(quiz);
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return quizzes;
}


public ArrayList<QuizQuestionInfo> fetchQuestions(int quiz_id) {
	ArrayList<QuizQuestionInfo> questions = new ArrayList<QuizQuestionInfo>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "select * from question where quiz_id = ?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, quiz_id);
		
		ResultSet rs = pStmt.executeQuery();	

		QuizQuestionInfo question;
		while(rs.next()) {
			question = new QuizQuestionInfo();
			question.setId(rs.getInt("id"));
			question.setQuiz_id(rs.getInt("quiz_id"));
			question.setQuestion(rs.getString("question"));
			question.setOption1(rs.getString("option1"));
			question.setOption2(rs.getString("option2"));
			question.setOption3(rs.getString("option3"));
			question.setOption4(rs.getString("option4"));
			question.setRight_option(rs.getString("right_option"));
			questions.add(question);
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return questions;
}



public boolean deleteQuiz(int quiz_id) {
	boolean isDeleted = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);
		
		String sql1 = "DELETE FROM quiz WHERE id=?";
		PreparedStatement pStmt1 = con.prepareStatement(sql1);
		pStmt1.setInt(1, quiz_id);
		int rs1 = pStmt1.executeUpdate();

		String sql2 = "DELETE FROM question WHERE quiz_id=?";
		PreparedStatement pStmt2 = con.prepareStatement(sql2);
		pStmt2.setInt(1, quiz_id);
		int rs2 = pStmt2.executeUpdate();

		String sql3 = "DELETE FROM grade WHERE quiz_id=?";
		PreparedStatement pStmt3 = con.prepareStatement(sql3);
		pStmt3.setInt(1, quiz_id);
		int rs3 = pStmt3.executeUpdate();



		if(rs1 > 0) {
			isDeleted = true;
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return isDeleted;
}





// end method 
}//end of class

