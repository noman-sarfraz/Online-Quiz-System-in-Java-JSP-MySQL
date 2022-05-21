package Quiz;

public class StudentInfo implements java.io.Serializable{
private String name;
private String email;
private String password;

public StudentInfo() {}

public void setName(String n) {
	name = n;
}

public void setEmail(String n) {
	email = n;
}

public void setPassword(String n) {
	password = n;
}

public String getName() {
	return name;
}

public String getEmail() {
	return email;
}

public String getPassword() {
	return password;
}




} // end class

