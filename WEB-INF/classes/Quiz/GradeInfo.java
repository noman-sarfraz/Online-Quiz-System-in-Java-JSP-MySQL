package Quiz;

public class GradeInfo implements java.io.Serializable {
private int id;
private String student_id;
private int quiz_id;
private int total_marks;
private int obtained_marks;
private int isAttempted;

public GradeInfo() {}

public void setId(int i) {
	id = i;
}

public void setStudent_id(String i) {
	student_id = i;
}

public void setQuiz_id(int i) {
	quiz_id = i;
}

public void setTotal_marks(int i) {
	total_marks = i;
}

public void setObtained_marks(int i) {
	obtained_marks = i;
}

public void setIsAttempted(int i) {
	isAttempted = i;
}



public int getId() {
	return id;
}

public String getStudent_id() {
	return student_id;
}

public int getQuiz_id() {
	return quiz_id;
}

public int getTotal_marks() {
	return total_marks;
}

public int getObtained_marks() {
	return obtained_marks;
}

public int getIsAttempted() {
	return isAttempted;
}

}
