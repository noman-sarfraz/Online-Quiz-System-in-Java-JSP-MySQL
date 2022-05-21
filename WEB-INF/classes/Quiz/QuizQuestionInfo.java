package Quiz;

public class QuizQuestionInfo implements java.io.Serializable {
private int id;
private int quiz_id;
private String question;
private String option1;
private String option2;
private String option3;
private String option4;
private String right_option;

public QuizQuestionInfo() {}

public void setId(int i) {
	id = i;
}

public void setQuiz_id(int qi) {
	quiz_id = qi;
}

public void setQuestion(String q) {
	question = q;
}

public void setOption1(String o1) {
	option1 = o1;
}

public void setOption2(String o2) {
	option2 = o2;
}

public void setOption3(String o3) {
	option3 = o3;
}

public void setOption4(String o4) {
	option4 = o4;
}

public void setRight_option(String ro) {
	right_option = ro;
}


public int getId() {
	return id;
}

public int getQuiz_id() {
	return quiz_id;
}

public String getQuestion() {
	return question;
}

public String getOption1() {
	return option1;
}

public String getOption2() {
	return option2;
}

public String getOption3() {
	return option3;
}

public String getOption4() {
	return option4;
}

public String getRight_option() {
	return right_option;
}

}
