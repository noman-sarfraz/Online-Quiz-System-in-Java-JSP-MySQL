package Quiz;

import java.sql.*;

public class TeacherDAO {

// public boolean addStudent(StudentInfo student) {
// 	boolean isAdded = false;
// 	try {
// 		Class.forName("com.mysql.jdbc.Driver");
// 		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
// 		Connection con = DriverManager.getConnection(url);

// 		String sql = "insert into student(name, email, password) values(?, ?, ?)";
// 		PreparedStatement pStmt = con.prepareStatement(sql);
// 		pStmt.setString(1, student.name);
// 		pStmt.setString(2, student.email);
// 		pStmt.setString(3, student.password);
		
// 		int rs = pStmt.executeUpdate();	
		
// 		con.close();

// 		if (rs > 0) {
// 			isAdded = true;
// 		}

// 	} catch(Exception ex){System.out.println(ex);}

// 	return isAdded;
// }


// public StudentInfo searchStudent(String email){
// StudentInfo person = null;
// try {
// Class.forName("com.mysql.jdbc.Driver");
// String url = "jdbc:mysql://localhost/student?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
// Connection con = DriverManager.getConnection(url);

// String sql = "SELECT * FROM studentinfo WHERE email = ?";
// PreparedStatement pStmt = con.prepareStatement(sql);
// pStmt.setString(1, email);
// ResultSet rs = pStmt.executeQuery();

// if (rs.next( ) ) {
// String name = rs.getString("name");
// String phone = rs.getString("phone");
// String mail = rs.getString("email");
// String password = rs.getString("pasword");
// person = new StudentInfo(name, phone, mail, password);}
// con.close();
// } catch(Exception ex){System.out.println(ex);}

// return person;

// }

public boolean verifyTeacher(String email, String password){
boolean isVerified = false;
try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
Connection con = DriverManager.getConnection(url);

String sql = "SELECT * FROM teacher WHERE email = ? AND password = ?";
PreparedStatement pStmt = con.prepareStatement(sql);
pStmt.setString(1, email);
pStmt.setString(2, password);
ResultSet rs = pStmt.executeQuery();

if (rs.next()) {
	isVerified = true;
}

con.close();
} catch(Exception ex){System.out.println(ex);}

return isVerified;

}


public boolean verifyJoinCode(String jc){
	boolean isVerified = false;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
	Connection con = DriverManager.getConnection(url);

	String sql = "SELECT * FROM teacher";
	Statement Stmt = con.createStatement();
	ResultSet rs = Stmt.executeQuery(sql);

	if (rs.next()) {
		String join_code = rs.getString("join_code");
		if(join_code.equals(jc))
			isVerified = true;
	}

	con.close();
	} catch(Exception ex){System.out.println(ex);}

	return isVerified;

}


// end method 
}//end of class

