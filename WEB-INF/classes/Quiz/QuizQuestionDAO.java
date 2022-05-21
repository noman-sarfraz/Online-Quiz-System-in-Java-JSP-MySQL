package Quiz;

import java.sql.*;

public class QuizQuestionDAO {

public QuizQuestionDAO() {}

public boolean addQuizQuestion(QuizQuestionInfo Question) {
	boolean isAdded = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "insert into question(quiz_id, question, option1, option2, option3, option4, right_option) values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, Question.getQuiz_id());
		pStmt.setString(2, Question.getQuestion());
		pStmt.setString(3, Question.getOption1());
		pStmt.setString(4, Question.getOption2());
		pStmt.setString(5, Question.getOption3());
		pStmt.setString(6, Question.getOption4());
		pStmt.setString(7, Question.getRight_option());
		
		int rs = pStmt.executeUpdate();	
		

		if (rs > 0) {
			QuizDAO quizDAO = new QuizDAO();
			QuizInfo quizInfo = quizDAO.searchQuiz(Question.getQuiz_id());
			sql = "update quiz SET total_questions = ? where id = ?";
			pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, quizInfo.getTotal_questions() + 1);
			pStmt.setInt(2, quizInfo.getId());
			rs  = pStmt.executeUpdate();
			if(rs > 0) {
				isAdded = true;
			}
		}

		con.close();

	} catch(Exception ex){System.out.println(ex);}

	return isAdded;
}


public boolean updateQuestion(QuizQuestionInfo Question) {
	boolean isUpdated = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "update question SET question=? , option1=?, option2=?, option3=?, option4=?, right_option=? where quiz_id=? and id=?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, Question.getQuestion());
		pStmt.setString(2, Question.getOption1());
		pStmt.setString(3, Question.getOption2());
		pStmt.setString(4, Question.getOption3());
		pStmt.setString(5, Question.getOption4());
		pStmt.setString(6, Question.getRight_option());
		pStmt.setInt(7, Question.getQuiz_id());
		pStmt.setInt(8, Question.getId());
		
		int rs = pStmt.executeUpdate();	
		
		con.close();

		if (rs > 0) {
			isUpdated = true;
		}

	} catch(Exception ex){System.out.println(ex);}

	return isUpdated;
}


public boolean deleteQuestion(int quiz_id, int id) {
	boolean isDeleted = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "DELETE FROM question WHERE id=? AND quiz_id=?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, id);
		pStmt.setInt(2, quiz_id);
		int rs = pStmt.executeUpdate();

		if(rs > 0) {
			isDeleted = true;
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}
	return isDeleted;
}


public QuizQuestionInfo fetchQuestion(int quiz_id, int id) {
	QuizQuestionInfo question = new QuizQuestionInfo();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);
		
		String sql = "select * from question where id = ? AND quiz_id=?";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, id);
		pStmt.setInt(2, quiz_id);
		
		ResultSet rs = pStmt.executeQuery();

		if(rs.next()) {
			question.setId(rs.getInt("id"));
			question.setQuiz_id(rs.getInt("quiz_id"));
			question.setQuestion(rs.getString("question"));
			question.setOption1(rs.getString("option1"));
			question.setOption2(rs.getString("option2"));
			question.setOption3(rs.getString("option3"));
			question.setOption4(rs.getString("option4"));
			question.setRight_option(rs.getString("right_option"));
		}

		con.close();
	} catch(Exception ex){System.out.println(ex);}

	return question ;
}


// end method 
}//end of class

