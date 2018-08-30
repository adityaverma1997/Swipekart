package operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionList {
	public static List<String> getQuestions() {
		List<String> questions = new ArrayList<>();
		questions.add("What is your name?");
		questions.add("What is your best friend name?");
		questions.add("What is your pet name?");
		questions.add("What is your favourite colour?");
		questions.add("What is your favourite sports?");
		questions.add("What is your nick name?");
		Collections.shuffle(questions);
		return questions;
	}
}
