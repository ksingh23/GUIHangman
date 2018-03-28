package Hangman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private String wrongLetters;
	private String phrase;
	private String letter;
	private String dashed;
	
	private int mistakeCounter;
	
	private DrawStuff draw;
	private DrawBodyParts body;
	
	private JPanel gameDisplayPanel;
	private JPanel wordLinesPanel;
	private JPanel titleDisplayPanel;
	private JPanel inputPanel;
	private JPanel wrongLettersPanel;
	private JPanel bottomDisplayPanel;
	
	private JLabel hangman;
	private JLabel word;
	private JTextArea emptyGuessLines;
	private JLabel letterGuess;
	private JTextArea wrongGuessString;
	
	private JTextField wordInput;
	private JTextField letterInput;
	
	private JButton enterGuess;
	private JButton enterInput;
	
	private char[] answer;
	private String[] dashes;
	private String [] split;
	
	private Font font;
	private Font labelFont;
	private Font dashFont;
	
	@SuppressWarnings("rawtypes")
	private ArrayList lettersToReplace;
	
	@SuppressWarnings("rawtypes")
	public GamePanel (){
		setLayout (new BorderLayout ());
		bottomDisplayPanel = new JPanel ();
		enterGuess = new JButton ("Enter");
		enterInput = new JButton ("Enter");
		font  = new Font ("Courier New", 1, 50);
		labelFont  = new Font ("Courier New", 1, 15);
		dashFont = new Font ("Courier New", 1, 25);
		enterGuess.addActionListener(new GuessListener ());
		enterInput.addActionListener(new LetterListener());
		titleDisplayPanel = new JPanel ();
		wrongLettersPanel = new JPanel ();
		draw = new DrawStuff();
		wordLinesPanel = new JPanel ();
		gameDisplayPanel = new JPanel (new GridLayout (2,1));
		word = new JLabel ("Enter a word or phrase  ");
		word.setFont(labelFont);
		mistakeCounter = 0;
		wrongLetters = "";
		dashed = "                                            ";
		lettersToReplace = new ArrayList();
		emptyGuessLines = new JTextArea (dashed);
		emptyGuessLines.setOpaque(false);
		emptyGuessLines.setEditable(false);
		emptyGuessLines.setFont(dashFont);
		wrongGuessString = new JTextArea (wrongLetters);
		wrongGuessString.setOpaque(false);
		wrongGuessString.setEditable(false);
		wrongGuessString.setFont(font);
		wordLinesPanel.add(emptyGuessLines);
		wrongLettersPanel.add(wrongGuessString);
		gameDisplayPanel.add(wrongLettersPanel);
		gameDisplayPanel.add(wordLinesPanel);
		letterGuess = new JLabel ("  Enter a letter");
		letterGuess.setFont(labelFont);
		wordInput = new JTextField (45);
		letterInput = new JTextField (5);
		inputPanel = new JPanel ();
		wordLinesPanel.add(enterGuess);
		add (titleDisplayPanel, BorderLayout.NORTH);
		gameDisplayPanel.add(wordLinesPanel, BorderLayout.SOUTH);
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gameDisplayPanel, draw);
		hangman = new JLabel ("HANGMAN");
		hangman.setFont(font);
		titleDisplayPanel.add(hangman);
		inputPanel.add(word);
		inputPanel.add (wordInput);
		inputPanel.add(enterGuess);
		inputPanel.add(letterGuess);
		inputPanel.add(letterInput);
		inputPanel.add(enterInput);
		bottomDisplayPanel.add(inputPanel);
		add(split, BorderLayout.CENTER);
		add (bottomDisplayPanel, BorderLayout.SOUTH);
	}
	
	private class DrawStuff extends JPanel{
		private static final long serialVersionUID = 1L;
		public void paintComponent(Graphics page) {
			super.paintComponent(page);
			setBackground(Color.WHITE);
			body = new DrawBodyParts (270,75);
			body.drawHangInitial(page);
			if (mistakeCounter == 1) {
				body.drawHead(page);
			}
			
			else if (mistakeCounter == 2) {
				body.drawBody(page);
			}
			
			else if (mistakeCounter == 3) {
				body.drawLeftLeg(page);
			}
			
			else if (mistakeCounter == 4) {
				body.drawRightLeg(page);
			}
			
			else if (mistakeCounter == 5) {
				body.drawLeftArm(page);
			}
			
			else if (mistakeCounter == 6) {
				body.drawRightArm(page);
			}
		}
	}
	private class GuessListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			dashed = "";
			if (lettersToReplace.size() != 0) {
				for (int a = 0; a < lettersToReplace.size(); a++) {
					lettersToReplace.remove(a);
				}
			}
			wrongLetters = "";
			wrongGuessString.setText(wrongLetters);
			phrase = wordInput.getText().toLowerCase();
			split = phrase.split("\\s+");
			wordInput.setText("");
			phrase = "";
			int lineCount = 1;
			for (int i = 0; i < split.length; i++) {
				phrase+=split[i] + " ";
				if (phrase.length() > (lineCount * 20)) {
					phrase = phrase.replace(split[i], "");
					while (phrase.length() < lineCount * 20) {
						phrase+=" ";
					}
					phrase+=split[i]+" ";
					lineCount++;
				}
			}
			
			answer = new char[phrase.length()];
			dashes = new String [phrase.length()];
			for (int i = 0; i < phrase.length(); i++) {
				answer[i] = phrase.charAt(i);
			}
			
			for (int k = 0; k < dashes.length; k++) {
				if (answer[k] != ' ') {
					if (Character.isLetter(answer[k])) {
						dashes[k] = "_ ";
					}
					
					else {
						dashes[k] = Character.toString(answer[k]) + " ";
					}
					
				}
				
				else {
					dashes[k] = "  ";
				}
			}
			
			for (int j = 0; j < dashes.length; j++) {
				
				if (j % 19 == 0 && j != 0) {
					dashed+=dashes[j]+"\n";
				}
				
				else {
					dashed+=dashes[j];
				}
			}
		
			emptyGuessLines.setText(dashed);
			repaint();
		}
		
	}
	
	private class LetterListener implements ActionListener{
		@SuppressWarnings({"unchecked", "rawtypes" })
		public void actionPerformed (ActionEvent e) {
			if (mistakeCounter < 6) {
				dashed = "";
				letter = letterInput.getText().toLowerCase();
				char l = letter.charAt(0);
				letterInput.setText("");
				lettersToReplace = new ArrayList ();
				if (letter.length() == 1 && Character.isAlphabetic(l)) {
					for (int i = 0; i < phrase.length(); i++) {
						if (l == phrase.charAt(i)) {
							lettersToReplace.add(i);
						}
					}
					
					if (lettersToReplace.size() == 0) {
						mistakeCounter++;
						if (wrongLetters.length() % 18 == 0 && wrongLetters.length() != 0) {
							wrongLetters+=letter+" "+"\n";
						}
						
						else {
							wrongLetters+=letter+" ";
						}
						wrongGuessString.setText(wrongLetters);
					}
					
					else {
						for (int j = 0; j < lettersToReplace.size(); j++) {
							dashes[(int) lettersToReplace.get(j)] = letter + " ";
						}
					}
				}
				
				
				for (int j = 0; j < dashes.length; j++) {
					if (j % 19 == 0 && j != 0) {
						dashed+=dashes[j]+"\n";
					}
					
					else {
						dashed+=dashes[j];
					}
				}
				
				emptyGuessLines.setText(dashed);
				repaint();
			}
		}
	}
	
}
