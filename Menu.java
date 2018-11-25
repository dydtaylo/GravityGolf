//Menu.java
//Dylan Taylor

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JPanel implements KeyListener
{
	private boolean startGame, ready;
	Font small, big;
	Dimension screenSize;
	final int SCREEN_WIDTH, SCREEN_HEIGHT;
	FontMetrics metrics;
	boolean canCheat;
	String str;
	int[] cheatStr;
	int cheatStrCount;

	public Menu()
	{
		startGame = false;
		ready = false;
		addKeyListener(this);
		big = new Font("TimesRoman", Font.PLAIN, 200);
		small = new Font("TimesRoman", Font.PLAIN, 125);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = (int) screenSize.getWidth();
		SCREEN_HEIGHT = (int) screenSize.getHeight();
		canCheat = false;
		cheatStr = new int[]{KeyEvent.VK_C, KeyEvent.VK_H, KeyEvent.VK_E, KeyEvent.VK_A, KeyEvent.VK_T, KeyEvent.VK_S};
		cheatStrCount = 0;
	}

	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, 2000, 2000);
		g.setFont(big);
		metrics = g.getFontMetrics(big);
		str = "Gravity Golf";
		g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, (SCREEN_HEIGHT - metrics.getHeight())/2 + metrics.getAscent() - SCREEN_HEIGHT/10);
		g.setFont(small);
		metrics = g.getFontMetrics(small);
		if(!ready)
			str = "Loading...";
		else
			str = "Press any key to begin";
		g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, (SCREEN_HEIGHT - metrics.getHeight())/2 + metrics.getAscent() + SCREEN_HEIGHT/10);
		repaint();
	}
	public void ready()
	{
		ready = true;
	}

	public boolean getStatus()
	{
		return startGame;
	}
	public boolean canCheat()
	{
		return canCheat;
	}

	public void keyPressed(KeyEvent key)
	{
		int k = key.getKeyCode();
		if(k == cheatStr[cheatStrCount])
			cheatStrCount++;
		else
			if(ready)
				startGame = true;
		if(cheatStrCount  >= cheatStr.length)
		{
			canCheat = true;
			startGame = true;
		}
	}
	public void keyReleased(KeyEvent key){}
	public void keyTyped(KeyEvent key){}
}