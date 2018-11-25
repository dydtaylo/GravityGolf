//WinScreen.java
//Dylan Taylor

import java.awt.*;
import javax.swing.*;

public class WinScreen extends JPanel
{
	int strokes;
	FontMetrics metrics;
	Dimension screenSize;
	final int SCREEN_WIDTH, SCREEN_HEIGHT;
	String str;
	boolean cheated;

	public WinScreen()
	{
		strokes = 0;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = (int) screenSize.getWidth();
		SCREEN_HEIGHT = (int) screenSize.getHeight();
		cheated = false;
	}

	public void paintComponent(Graphics g)
	{
		g.setFont(new Font("TimesRoman", Font.PLAIN, 300));
		metrics = g.getFontMetrics(g.getFont());
		str = "You win!";
		g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, (SCREEN_HEIGHT - metrics.getHeight())/2 + metrics.getAscent() - SCREEN_HEIGHT/10);

		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		metrics = g.getFontMetrics(g.getFont());
		str = "It only took you " + strokes + " strokes!";
		g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, (SCREEN_HEIGHT - metrics.getHeight())/2 + metrics.getAscent() + SCREEN_HEIGHT/10);

		if(cheated)
		{
			g.setColor(Color.RED);

			str = "You filthy cheater";
			g.drawString(str, (SCREEN_WIDTH - metrics.stringWidth(str))/2, (SCREEN_HEIGHT - metrics.getHeight())/2 + metrics.getAscent() + SCREEN_HEIGHT/5);
		}
	}

	public void setStrokes(int num)
	{
		strokes = num + 1;
	}
	public void cheated(boolean b)
	{
		cheated = b;
	}
}