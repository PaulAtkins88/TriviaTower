package com.paulsgames.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questions {
	private BufferedReader questionInput;
	private BufferedReader answerInput;
	private int size = 0;
	
	private HashMap <Integer, String> questions = new HashMap<Integer, String>();
	private HashMap <Integer, String> answers = new HashMap<Integer, String>();
	private HashMap <Integer, String[]> test = new HashMap<Integer,String[]>();
	public Questions() {
		// open the question & answer files
		try {
			this.questionInput = new BufferedReader(new FileReader("questions.csv"));
			this.answerInput = new BufferedReader(new FileReader("answers.csv"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (questionInput.readLine() != null) size++;			
			questionInput.close();
			this.questionInput = new BufferedReader(new FileReader("questions.csv"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//read the questions and answers
		for (int i = 0; i < size; i++) {
			try {
				questions.put(i, questionInput.readLine());
				answers.put(i, answerInput.readLine());	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// close both files
		try {
			questionInput.close();
			answerInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map getQuestions() {
		return this.questions;
	}
	
	public Map getAnswers() {
		return this.answers;
	}
	

}
