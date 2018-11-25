//GravityObject.java
//Dylan Taylor

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Point2D;
import java.awt.Graphics2D;

public class GravityObject
{
	private int x, y;
	private int mass;
	private int radius;
	private int forceRadius;
	private RadialGradientPaint gradient;
	boolean isHole;

	public GravityObject(double xPos, double yPos, int m, int r, int fR)
	{
		x = (int) xPos;
		y = (int) yPos;
		mass = m;
		radius = r;
		forceRadius = fR;
		Point2D center = new Point2D.Float((float) x + radius/2, (float) y + radius/2);
		float [] dist = {.0f, 1.0f};
		Color [] colors = {Color.BLUE, new Color(0, 0,128, 64)};
		gradient = new RadialGradientPaint(center, forceRadius/2, dist, colors);
	}
	public void draw(Graphics g, Image image, JPanel screen)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(gradient);
		g2.fillOval((int) x + radius/2 - forceRadius/2, (int) y + radius/2 - forceRadius/2, forceRadius, forceRadius);
		g.setColor(Color.RED);
		g.drawImage(image, (int) x, (int) y, screen);
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getMass()
	{
		return mass;
	}
	public int getRadius()
	{
		return radius;
	}
	public int getForceRadius()
	{
		return forceRadius;
	}

	public boolean isHole()
	{
		return isHole;
	}
}