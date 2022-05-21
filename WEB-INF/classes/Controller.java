import java.io.*;  
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import Quiz.QuizDAO;
import Quiz.QuizQuestionDAO;
import Quiz.QuizQuestionInfo;
import Quiz.GradeDAO;
import Quiz.GradeInfo;

public class Controller extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
ServletException, IOException { 
processRequest(request, response); 
} 
 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
ServletException, IOException { 
processRequest(request, response); 
} 

protected void processRequest(HttpServletRequest request, 
HttpServletResponse response) throws ServletException, IOException {

	String action = request.getParameter("action");

	if(action.equals("delete_quiz")) {
		QuizDAO quizDAO = new QuizDAO();
		quizDAO.deleteQuiz(Integer.parseInt(request.getParameter("quiz_id")));
		response.sendRedirect("quiz_list.jsp");
	} else if(action.equals("create_quiz")) {
		QuizDAO quizDAO = new QuizDAO();
		int quiz_id = quizDAO.addQuiz(request.getParameter("name"));
		response.sendRedirect("view_quiz.jsp?id=" + quiz_id);
	} else if(action.equals("delete_question")) {
		QuizQuestionDAO quizQuestionDAO = new QuizQuestionDAO();
		boolean deleted = 
		quizQuestionDAO.deleteQuestion(
			Integer.parseInt(request.getParameter("quiz_id")),
			Integer.parseInt(request.getParameter("id"))
		);
		
		if(deleted) {
			response.sendRedirect("view_quiz.jsp?id=" + request.getParameter("quiz_id"));
		}
	} else if(action.equals("grade_quiz")) {
		gradeQuiz(request,response);
	} else if(action.equals("upload_quiz")) {
		QuizDAO quizDAO = new QuizDAO();
		boolean uploaded = quizDAO.uploadQuiz(Integer.parseInt(request.getParameter("quiz_id")));
		if(uploaded) {
			response.sendRedirect("view_quiz.jsp?id=" + request.getParameter("quiz_id"));
		}
	}

	else {
		System.out.println("No service available for the request " + action);
	}

}


private void gradeQuiz(HttpServletRequest request, 
HttpServletResponse response) throws ServletException, IOException {

	String student_id = request.getParameter("student_id");
	int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));

	QuizDAO quizDAO = new QuizDAO();
	ArrayList<QuizQuestionInfo> questions = quizDAO.fetchQuestions(quiz_id);
	QuizQuestionInfo question;
	
	GradeDAO gradeDAO = new GradeDAO();
	GradeInfo grade = gradeDAO.searchGrade(student_id, quiz_id);
	
	int marks_obtained = 0;
	String question_no;
	if(grade!=null && grade.getIsAttempted() == 0) {
		for(int i=0;i<questions.size();i++) {
			question = questions.get(i);
			question_no = "question_" + question.getId();
			if(question.getRight_option().equals(request.getParameter(question_no))) {
				marks_obtained++;
			}
		}

		grade.setIsAttempted(1);
		grade.setObtained_marks(marks_obtained);
		grade.setTotal_marks(Integer.parseInt(request.getParameter("total_marks")));
		gradeDAO.updateGrade(grade);
		response.sendRedirect("quiz_marks.jsp?quiz_id=" + request.getParameter("quiz_id"));
	} else {
		System.out.println("grade: " + grade);
	}

}


} // end of class


