//SimulationBall.java
//Dylan Taylor

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.geom.Line2D;

public class SimulationBall
{
	private double x, y;
	private double speed;
	private double xMove, yMove;
	private double rotation, newRot;
	private int radius;
	private final double SCREEN_WIDTH, SCREEN_HEIGHT;
	private boolean delete, inHole;
	private boolean onGrav;
	private MathObject calc;
	private ArrayList<GravityObject> objects;
	private AsteroidBelt[] belts;
	Rectangle rect;

	public static void main(String [] args)
	{
		new Tester();
	}

	public SimulationBall(double xPos, double yPos, double s, double rot, int r, double sW, double sH)
	{
		x = xPos;
		y = yPos;
		speed = s;
		xMove = 0;
		yMove = 0;
		rotation = rot;
		newRot = 1;
		radius = r;
		SCREEN_WIDTH = sW;
		SCREEN_HEIGHT = sH;
		delete = false;
		inHole = false;
		onGrav = false;
		calc = new MathObject();
		rect = new Rectangle((int) x - radius/2, (int) y - radius/2, radius, radius);
	}

	public void move()
	{
		x += (xMove + speed * Math.cos(Math.toRadians(rotation)));
		y += (yMove + speed * -Math.sin(Math.toRadians(rotation)));
		detectBounds();
		rect.setRect(x - radius/2, y - radius/2, radius, radius);
	}
	public void detectBounds()
	{
		if(x <= -200 || x >= SCREEN_WIDTH + 200)
			explode();
		if(y <= -200 || y >= SCREEN_HEIGHT + 200)
			explode();
	}
	public void simulate(Graphics g, JPanel screen)
	{
		int ticks = 0;
		while(!delete && !inHole && ticks < 5000)
		{
			checkPosition();
			move();
			g.drawLine((int) x, (int) y, (int) x, (int) y);
			ticks++;
		}
	}
	public void checkPosition()
	{
		for(int i = 0; i < objects.size(); i++)
		{
			GravityObject obj = objects.get(i);
			double distance = calc.getDistance(x, y, obj.getX() + obj.getRadius()/2, obj.getY() + obj.getRadius()/2);

			if(distance < obj.getRadius()/2)
				if(i == objects.size() - 1)
					win();
				else
					explode();
			else if(distance < obj.getForceRadius()/2)
				gravitate(obj);
		}

		for(int i = 0; i < belts.length; i++)
		{
			AsteroidBelt belt = belts[i];
			double beltSX = belt.getStartX() * SCREEN_WIDTH;
			double beltSY = belt.getStartY() * SCREEN_HEIGHT;
			double beltEX = belt.getEndX() * SCREEN_WIDTH;
			double beltEY = belt.getEndY() * SCREEN_HEIGHT;
			Line2D line = new Line2D.Double(beltSX, beltSY, beltEX, beltEY);
			if(line.intersects(rect))
				explode();
		}

		rotation %= 360;
	}
	public void win()
	{
		inHole = true;
		explode();
	}
	public void explode()
	{
		delete = true;
	}
	public void gravitate(GravityObject obj)
	{
		double distance = calc.getDistance(x, y, obj.getX() + obj.getRadius()/2, obj.getY() + obj.getRadius()/2);
		double mass = obj.getMass();
		double gAccel =  mass/(distance*distance) * Math.pow(6.667, -5);

		double xDiff = (obj.getX() + obj.getRadius()/2) - x;
		double yDiff = (obj.getY() + obj.getRadius()/2) - y;
		double xPull = (gAccel*xDiff);
		double yPull = (gAccel*yDiff);

		xMove += xPull;
		yMove += yPull;
	}

	public double getX()
	{
		return x;
	}
	public void setX(double xPos)
	{
		x = xPos;
	}

	public double getY()
	{
		return y;
	}
	public void setY(double yPos)
	{
		y = yPos;
	}

	public double getSpeed()
	{
		return speed;
	}
	public void setSpeed(double s)
	{
		speed = s;
	}

	public double getRotation()
	{
		return rotation;
	}
	public void setRotation(double rot)
	{
		rotation = rot;
	}

	public int getRadius()
	{
		return radius;
	}
	public void setRadius(int r)
	{
		radius = r;
	}

	public boolean shouldDelete()
	{
		return delete;
	}
	public boolean isInHole()
	{
		return inHole;
	}
	public void setObjects(ArrayList<GravityObject> o)
	{
		objects = o;
	}
	public void setBelts(AsteroidBelt[] b)
	{
		belts = b;
	}
}