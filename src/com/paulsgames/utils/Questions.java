package com.paulsgames.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Questions {
	private BufferedReader questionInput;
	private BufferedReader answerInput;
	
	public String questions[] = new String[10];
	public String answers[] = new String[10];
	
	public Questions() {
		try {
			this.questionInput = new BufferedReader(new FileReader("questions.csv"));
			this.answerInput = new BufferedReader(new FileReader("answers.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < questions.length; i++) {
			try {
				questions[i] = questionInput.readLine();
				answers[i] = answerInput.readLine();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String[] getQuestions() {
		return this.questions;
	}
	
	public String[] getAnswers() {
		return this.answers;
	}
	

}
