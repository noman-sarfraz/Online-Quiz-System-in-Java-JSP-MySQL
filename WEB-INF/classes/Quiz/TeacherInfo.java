package Quiz;

public class TeacherInfo implements java.io.Serializable{
private String name;
private String email;
private String password;
private String join_code;

public TeacherInfo() {}

public void setName(String n) {
	name = n;
}

public void setEmail(String n) {
	email = n;
}

public void setPassword(String n) {
	password = n;
}

public void setJoin_code(String n) {
	join_code = n;
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

public String getJoin_code() {
	return join_code;
}




} // end class

