package com.paulsgames.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Random;

import com.paulsgames.gfx.SpriteSheet;
import com.paulsgames.main.Game.STATE;
import com.paulsgames.utils.GameObject;
import com.paulsgames.utils.Handler;
import com.paulsgames.utils.Questions;

public class HUD extends MouseAdapter {

	private Game game;
	private Handler handler;
	public static float HEALTH = 100;
	private float greenValue = 255;
	public static int score = 0, level = 1;
	private Random r;
	private Questions q;
	private HashMap<Integer, String> questions = new HashMap<Integer, String>();
	private HashMap<Integer, String> answers = new HashMap<Integer, String>();
	private int[] answer = new int[3];
	private String currentQuestion;
	private int index = 0;
	
	// timer variables
	public static long startTime;
	private long elapsedTime,elapsedSeconds; 
	
	@SuppressWarnings("unchecked")
	public HUD(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		r = new Random();
		q = new Questions();
		
		questions = (HashMap<Integer, String>) q.getQuestions();
		answers = (HashMap<Integer, String>) q.getAnswers();

		currentQuestion = questions.get(index);

		answer = randomAnswerLocation(index);
	}

	public int[] randomAnswerLocation(int index) {
		int[] randomAnswers = new int[3];
		randomAnswers[r.nextInt(3)] = index;
		for (int i = 0; i < randomAnswers.length; i++) {
			if (randomAnswers[i] == index) {
				randomAnswers[i] = index;
			} else {
				randomAnswers[i] = r.nextInt(15);				
			}
		}
		return randomAnswers;
	}

	private int getQuestionNumber() {
		return r.nextInt(15);
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		int selection;
		if (game.gameState == STATE.Game) {
			if (mouseOver(mx, my, 15, Game.HEIGHT - 150, 200, 64)) {
				// this is the back button
				game.gameState = STATE.Menu;
				handler.object.clear();
				return;
			} else if (mouseOver(mx, my, (Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 150, 200, 64)) {
				// this is answer 1
				selection = answer[0];
				if(selection == index) {
					correct();
				} else {
					incorrect();
				}				
				return;
			} else if (mouseOver(mx, my, (Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 75, 200, 64)) {
				// this is answer 2
				selection = answer[1];
				if(selection == index) {
					correct();
				} else {
					incorrect();
				}
				return;
			} else if (mouseOver(mx, my, (Game.WIDTH / 2) - 500, (Game.HEIGHT / 2), 200, 64)) {
				// this is answer 3
				selection = answer[2];
				if(selection == index) {
					correct();
				} else {
					incorrect();
				}
				return;
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
		score = (int) Game.clamp(score, 0, 100000);

		greenValue = HEALTH * 2;
	}

	private void correct() {
		for (GameObject object : handler.object) {
			if (object.getId() == ID.Player) {
				object.setY((int) (object.getY()-32));
			}			
		}
		index = getQuestionNumber();
		currentQuestion = questions.get(index);
		answer = randomAnswerLocation(index);
		score += 100;
		level++;
	}

	private void incorrect() {
		for (GameObject object : handler.object) {
			if (object.getId() == ID.Player) {
				object.setY((int) (object.getY()+32));
			}			
		}
		HEALTH -= 10;
		score -= 100;
		level--;
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

		g.setColor(Color.black);
		g.drawRect(15, 15, 200, 32);

		g.drawString("Health", 15, 12);

		g.drawString("Score: " + score, Game.WIDTH - 100, 15);
		g.drawString("Level : " + level, Game.WIDTH - 100, 30);
		
		// start timer
		elapsedTime = System.currentTimeMillis() - startTime;
		elapsedSeconds = (elapsedTime / 1000) + 1;
		// draw timer
		g.drawString("Timer : " + elapsedSeconds, Game.WIDTH - 100, 50);
		if(elapsedSeconds > 10) { // reset the timer
			startTime = System.currentTimeMillis();
			elapsedTime = System.currentTimeMillis() - startTime;
			elapsedSeconds = (elapsedTime / 1000) + 1;
		}
		
		// =====================================================
		// draw the trivia question here

		Font qFont = new Font("arial", 0, 20);
		g.setFont(qFont);
		g.drawString(currentQuestion, 15, 200);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 150, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 149, 199, 63);
		g.setColor(Color.black);
		g.drawString(answers.get(answer[0]), (Game.WIDTH / 2) - 470, (Game.HEIGHT / 2) - 110);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2) - 75, 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) - 74, 199, 63);
		g.setColor(Color.black);
		g.drawString(answers.get(answer[1]), (Game.WIDTH / 2) - 470, (Game.HEIGHT / 2) - 30);

		g.setColor(Color.lightGray);
		g.drawRect((Game.WIDTH / 2) - 500, (Game.HEIGHT / 2), 200, 64);
		g.setColor(Color.gray);
		g.fillRect((Game.WIDTH / 2) - 499, (Game.HEIGHT / 2) + 1, 199, 63);
		g.setColor(Color.black);
		g.drawString(answers.get(answer[2]), (Game.WIDTH / 2) - 470, (Game.HEIGHT / 2) + 45);		

		// =====================================================================
		// Back button
		Font buttonFont = new Font("arial", 1, 25);
		g.setColor(Color.lightGray);
		g.setFont(buttonFont);
		g.drawRect(15, Game.HEIGHT - 150, 200, 64);
		g.setColor(Color.gray);
		g.fillRect(16, Game.HEIGHT - 149, 199, 63);
		g.setColor(Color.black);
		g.drawString("Main Menu", 50, Game.HEIGHT - 110);

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
