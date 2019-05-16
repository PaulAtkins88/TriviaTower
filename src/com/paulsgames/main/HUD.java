package com.paulsgames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.paulsgames.utils.Questions;

public class HUD {

	public static float HEALTH = 100;
	private float greenValue = 255;

	private int score = 0, level = 1;
	private Random r;
	private Questions q;
	private String[] questions = new String[10];
	private String[] answers = new String[10];
	private String currentQuestion, currentAnswer;

	public HUD() {
		r = new Random();
		q = new Questions();
		questions = q.getQuestions();
		answers = q.getAnswers();
		System.out.print("LOADING QUESTIONS\nLOADING ANSWERS");
	}
	
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);

		greenValue = HEALTH * 2;
	}

	private void increaseScore() {
		score += 100;
	}

	private void decreaseScore() {
		score -= 100;
	}

	public void render(Graphics g) {
		// draw the top of the game here
		// ====================================================
		Font fnt = new Font("Helvetica", 1, 15);
		g.setFont(fnt);

		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);

		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(15, 15, (int) HEALTH * 2, 32);

		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);

		g.drawString("Health", 15, 12);

		g.drawString("Score: " + score, Game.WIDTH - 100, 15);
		g.drawString("Level : " + level, Game.WIDTH - 100, 30);
		// =====================================================
		// draw the trivia question here
		currentQuestion = questions[1];
		currentAnswer = answers[1];
		Font qFont = new Font("arial", 0, 20);
		g.setFont(qFont);
		g.drawString(currentQuestion, 15, 200);
		
		
		
		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 150, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 149, 199, 63);
		g.setColor(Color.green);
		g.drawString("1.14", (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) - 110);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 75, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 74, 199, 63);
		g.setColor(Color.green);
		g.drawString("2.14", (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) - 30);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2), 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) + 1, 199, 63);
		g.setColor(Color.green);
		g.drawString("3.14", (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) + 45);
		//g.drawString(currentAnswer, 15, 300);
		
		//======================================================================
		// draw tower - will be an image eventually
		
		g.drawRect((Game.WIDTH / 2) + 150,(Game.HEIGHT / 2)-300, 300, 600);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((Game.WIDTH / 2) + 151,(Game.HEIGHT / 2)-299, 299, 599);
		g.setColor(Color.black);
		g.drawString("THE TOWER", (Game.WIDTH / 2) + 250,(Game.HEIGHT / 2)-199);
		g.drawString("TO CLIMB", (Game.WIDTH / 2) + 250,(Game.HEIGHT / 2)-179);
		g.drawString("WITH CORRECT ANSWERS", (Game.WIDTH / 2) + 175,(Game.HEIGHT / 2)-159);
		
	}



	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

}
