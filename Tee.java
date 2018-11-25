//Tee.java
//Dylan Taylor

import java.awt.*;

public class Tee
{
	private int x, y;
	private int width, height;

	public Tee(int xPos, int yPos, int w, int h)
	{
		x = xPos;
		y = yPos;
		width = w;
		height = h;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int w)
	{
		width = w;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int h)
	{
		height = h;
	}
}