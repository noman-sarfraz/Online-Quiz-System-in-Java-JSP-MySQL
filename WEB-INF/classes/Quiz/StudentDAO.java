package Quiz;
import java.sql.*;


public class StudentDAO {

public boolean addStudent(StudentInfo student){
	boolean isAdded = false;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
		Connection con = DriverManager.getConnection(url);

		String sql = "insert into student(name, email, password) values(?, ?, ?)";
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, student.getName());
		pStmt.setString(2, student.getEmail());
		pStmt.setString(3, student.getPassword());
		
		int rs = pStmt.executeUpdate();	
		
		con.close();

		if (rs > 0) {
			isAdded = true;
		}

	} catch(Exception ex){System.out.println(ex);}

	return isAdded;
}


public StudentInfo searchStudent(String email){
StudentInfo person = null;
try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
Connection con = DriverManager.getConnection(url);

String sql = "SELECT * FROM student WHERE email = ?";
PreparedStatement pStmt = con.prepareStatement(sql);
pStmt.setString(1, email);
ResultSet rs = pStmt.executeQuery();

if (rs.next()) {
	person = new StudentInfo();
	person.setName(rs.getString("name"));
	person.setEmail(rs.getString("email"));
	person.setPassword(rs.getString("password"));
}
con.close();
} catch(Exception ex){System.out.println(ex);}

return person;

}

public boolean verifyStudent(String email, String password){
boolean isVerified = false;
try {
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost/OQS?user=root&password=root"; // jdbc:mysql://localhost/addressbook (access denied on my pc with this url)
Connection con = DriverManager.getConnection(url);

String sql = "SELECT * FROM student WHERE email = ? AND password = ?";
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


// end method 
}//end of class

