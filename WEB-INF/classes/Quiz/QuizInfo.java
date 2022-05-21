package Quiz;

public class QuizInfo implements java.io.Serializable {
private int id;
private String name;
private int uploaded;
private int total_questions;

public QuizInfo() {}

// setters
public void setId(int i) {
	id = i;
}

public void setName(String n) {
	name = n;
}

public void setUploaded(int n) {
	uploaded = n;
}

public void setTotal_questions(int n) {
	total_questions = n;
}


// getters
public int getId() {
	return id;
}

public String getName() {
	return name;
}

public int getUploaded() {
	return uploaded;
}

public int getTotal_questions() {
	return total_questions;
}



}
