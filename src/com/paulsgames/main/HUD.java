package com.paulsgames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.paulsgames.main.Game.STATE;
import com.paulsgames.utils.Questions;

public class HUD extends MouseAdapter{

	private Game game;
	public static float HEALTH = 100;
	private float greenValue = 255;

	private int score = 0, level = 1;
	private Random r;
	private Questions q;
	private String[] questions = new String[10];
	private String[] answers = new String[10];
	private int[] answer = new int[3];
	private String currentQuestion;
	private int index = 0, answer1,answer2,answer3;

	public HUD(Game game) {
		this.game = game;
		r = new Random();
		q = new Questions();
		questions = q.getQuestions();
		answers = q.getAnswers();
		System.out.print("LOADING QUESTIONS\nLOADING ANSWERS");
		
		currentQuestion = questions[index];
		

		answer1 = r.nextInt(10);
		answer2 = r.nextInt(10);
		answer3 = index;
		
	}	
	
	private int getQuestionNumber() {
		return r.nextInt(10);		
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Game) {
			if (mouseOver(mx,my,15, Game.HEIGHT - 150, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			} else if (mouseOver(mx,my,(Game.WIDTH / 2) - 500, (Game.HEIGHT / 2), 200, 64)){ // this is answer 3
				increaseScore();
				this.index = getQuestionNumber();
				currentQuestion = questions[index];
				answer[randomAnswerLocation()] = index;
				
			}
		}
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void mouseReleased(MouseEvent e) {

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
		
		Font qFont = new Font("arial", 0, 20);
		g.setFont(qFont);
		g.drawString(currentQuestion, 15, 200);
		
		
		
		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 150, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 149, 199, 63);
		g.setColor(Color.green);
		g.drawString(answers[answer[0]], (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) - 110);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 75, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 74, 199, 63);
		g.setColor(Color.green);
		g.drawString(answers[answer[1]], (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) - 30);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2), 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) + 1, 199, 63);
		g.setColor(Color.green);
		g.drawString(answers[answer[2]], (Game.WIDTH / 2) - 435, (Game.HEIGHT / 2) + 45);
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
		
		
		
		// =====================================================================
		// Back button
		Font buttonFont = new Font("arial",1,25);
		g.setColor(Color.lightGray);
		g.setFont(buttonFont);
		g.drawRect(15, Game.HEIGHT - 150, 200, 64);
		g.setColor(Color.gray);
		g.fillRect(16, Game.HEIGHT - 149, 199, 63);
		g.setColor(Color.green);
		g.drawString("Main Menu", 50, Game.HEIGHT - 110);
		
	}

	private int randomAnswerLocation() {
		return r.nextInt(3);
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
