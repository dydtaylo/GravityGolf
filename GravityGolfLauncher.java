//GravityGolfLauncher.java
//Dylan Taylor

import javax.swing.*;
import java.awt.*;

public class GravityGolfLauncher
{
	JFrame frame;
	LevelBuilder levelBuilder;
	Level [] levels;
	int level;
	int strokes;
	Menu menu;
	GravityGolf currentLevel;
	boolean startGame, canCheat;

	public static void main(String [] args)
	{
		new GravityGolfLauncher();
	}
	public GravityGolfLauncher()
	{
		setupScreen();
		levelBuilder = new LevelBuilder();
		levels = levelBuilder.getLevels();
		level = 0;
		strokes = 0;
		startGame = false;
		canCheat = false;

		menu.ready();
		while(!startGame)
		{
			startGame = menu.getStatus();
			menu.requestFocus();
		}
		canCheat = menu.canCheat();
		nextLevel();

		while(level < levels.length)
		{
			while(!currentLevel.hasWon())
				currentLevel.requestFocus();
			level++;
			if(level< levels.length)
				nextLevel();
		}
		strokes += currentLevel.getStrokes();
		frame.remove(currentLevel);
		WinScreen winScreen = new WinScreen();
		winScreen.setStrokes(strokes);
		frame.add(winScreen);
		winScreen.cheated(canCheat);
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
	}
	public void setupScreen()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Gravity Golf");
		menu = new Menu();
		menu.setFocusable(true);
		frame.add(menu);
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
	}

	public void nextLevel()
	{
		if(level == 0)
			frame.remove(menu);
		else
		{
			strokes += currentLevel.getStrokes();
			frame.remove(currentLevel);
		}
		currentLevel = new GravityGolf(levels[level]);
		frame.add(currentLevel);
		currentLevel.canCheat(canCheat);
		frame.pack();
		frame.setVisible(true);
		frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
	}
}