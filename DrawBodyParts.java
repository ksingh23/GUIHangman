package Hangman;

import java.awt.*;

public class DrawBodyParts {
	private int x;
	private int y;
	public DrawBodyParts (int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	public void drawHangInitial (Graphics page) {
		page.drawLine(100, 350 , 250, 350);
		page.drawLine(175, 350, 175, 50);
		page.drawLine(175, 50, 300, 50);
		page.drawLine(300, 50, 300, 75);
	}
	
	public void drawHead (Graphics page) {
		page.drawOval(x, y, 60, 60);
	}
	
	public void drawBody (Graphics page) {
		page.drawOval(x, y, 60, 60);
		page.drawLine(300, 135, 300, 235);
	}
	
	public void drawLeftLeg (Graphics page) {
		page.drawOval(x, y, 60, 60);
		page.drawLine(300, 135, 300, 235);
		page.drawLine(300, 235, 250, 295);
	}
	
	public void drawRightLeg (Graphics page) {
		page.drawOval(x, y, 60, 60);
		page.drawLine(300, 135, 300, 235);
		page.drawLine(300, 235, 250, 295);
		page.drawLine(300, 235, 350, 295);
	}
	
	public void drawLeftArm (Graphics page) {
		page.drawOval(x, y, 60, 60);
		page.drawLine(300, 135, 300, 235);
		page.drawLine(300, 235, 250, 295);
		page.drawLine(300, 235, 350, 295);
		page.drawLine(300, 155, 260, 185);
	}
	
	public void drawRightArm (Graphics page) {
		page.drawOval(x, y, 60, 60);
		page.drawLine(300, 135, 300, 235);
		page.drawLine(300, 235, 250, 295);
		page.drawLine(300, 235, 350, 295);
		page.drawLine(300, 155, 260, 185);
		page.drawLine(300, 155, 340, 185);
	}

}
