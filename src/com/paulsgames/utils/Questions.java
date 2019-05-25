package com.paulsgames.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Questions {
	private BufferedReader input;
	public static int size = 0;

	private String[] questions;
	private String[][] answers;
	private String[] tmp;

	public Questions() {
		// open the question & answer files
		size = getArraySize();
		System.out.println("arraySize is " + size);
		questions = new String[size];
		answers = new String[size][3];
		try {
			this.input = new BufferedReader(new FileReader("res/data.csv"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String tmpLine;
		tmp = new String[4];
		// read the questions and answers
		for (int i = 0; i < size; i++) {
			try {
				tmpLine = input.readLine();
				tmp = tmpLine.split(",", 4);
				questions[i] = tmp[0];
				answers[i][0] = tmp[1];
				answers[i][1] = tmp[2];
				answers[i][2] = tmp[3];
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// close files
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getArraySize() {
		int count = 0;
		try {
			this.input = new BufferedReader(new FileReader("res/data.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			while (input.readLine() != null)
				count++;
			input.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return count;
	}

	public String[] getQuestions() {
		return this.questions;
	}

	public String[][] getAnswers() {
		return this.answers;
	}

}
