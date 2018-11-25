//GravityGolf.java
//Dylan Taylor

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GravityGolf extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
	Dimension screenSize;
	final int SCREEN_WIDTH, SCREEN_HEIGHT;
	ArrayList <Ball> balls;
	int defaultBallRadius;
	Random gen;
	int startX, startY, endX, endY;
	double dragRotation;
	MouseEvent mouse;
	boolean mouseDown;
	Level level;
	MathObject calc;
	ArrayList<GravityObject> objects;
	Tee tee;
	Hole hole;
	Image backgroundImage, ballImage;
	String ballImageUrl;
	String [] images;
	AsteroidBelt[] belts;
	boolean wonGame;
	int strokes;
	Thread thread;
	boolean shiftDown, controlDown, capsLockDown, altDown, gDown;
	boolean canCheat;

	public static void main(String [] args)
	{
		new Tester();
	}

	public GravityGolf(Level gameLevel)
	{
		level = gameLevel;
		setupScreen();
		SCREEN_WIDTH = (int) screenSize.getWidth();
		SCREEN_HEIGHT = (int) screenSize.getHeight();
		initVars();
		createObjects();
		loadBackground();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	public void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		g.drawImage(backgroundImage, 0, 0, this);

		g.setColor(new Color(255, 0, 0 , 255));
		if(mouse != null)
			if(mouseDown)
			{

				if(!gDown)
				{
					g.drawLine(startX, startY, endX, endY);
					g.setColor(new Color(255, 0, 0, 128));
					Graphics2D g2d = (Graphics2D) g;
					Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
					g2d.setStroke(dashed);

					if(endX != startX && endY != startY)
						g2d.drawLine(startX, startY, -250 * (endX-startX), -250 * (endY-startY));
					else if(endY != startY)
						if(startY > endY)
							g2d.drawLine(startX, startY, startX, 2000);
						else
							g2d.drawLine(startX, startY, startX, -2000);
					else if(startX != endX)
						if(startX > endX)
							g2d.drawLine(startX, startY, 2000, startY);
						else
							g2d.drawLine(startX, startY, -2000, startY);
				}
				else
				{
					if(altDown)
					{
						for(double n = 0; n < 10; n += .1)
						{
							int ballRadius = defaultBallRadius;
							double ballX = endX;
							double ballY = endY;
							double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT) * n;
							double ballRotation = calc.findRotation(startX, startY, endX, endY);
							SimulationBall simBall = new SimulationBall(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
							simBall.setObjects(objects);
							simBall.setBelts(belts);
							simBall.simulate(g, this);
						}
					}
					else if(capsLockDown)
					{
						if(shiftDown)
						{
							for(int i = 0; i < 10; i++)
							{
								for(int n = 0; n < 20 * (i + 1); n++)
								{
									int ballRadius = defaultBallRadius;
									double ballX = endX;
									double ballY = endY;
									double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT) + i;
									double ballRotation = calc.findRotation(startX, startY, endX, endY) + n * 360/(20 * (i +1));
									SimulationBall simBall = new SimulationBall(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
									simBall.setObjects(objects);
									simBall.setBelts(belts);
									simBall.simulate(g, this);
								}
							}
						}
						else
						{
							for(int i = 0; i < 60; i++)
							{
								Ball newBall;
								int ballRadius = defaultBallRadius;
								double ballX = endX;
								double ballY = endY;
								double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT);
								double ballRotation = calc.findRotation(startX, startY, endX, endY) + i * 6;
								SimulationBall simBall = new SimulationBall(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
								simBall.setObjects(objects);
								simBall.setBelts(belts);
								simBall.simulate(g, this);
							}
						}
					}
					else if(shiftDown)
					{
						for(int i = 0; i < 10; i++)
						{
							for(int n = 0; n < 10; n++)
							{
								int ballRadius = defaultBallRadius;
								double ballX = endX + (i-5) * 5;
								double ballY = endY + (n-5) * 5;
								double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100 * SCREEN_WIDTH/SCREEN_HEIGHT;
								double ballRotation = calc.findRotation(startX, startY, endX, endY);
								SimulationBall simBall = new SimulationBall(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
								simBall.setObjects(objects);
								simBall.setBelts(belts);
								simBall.simulate(g, this);
							}
						}
					}
					else
					{
						int ballRadius = defaultBallRadius;
						double ballX = endX;
						double ballY = endY;
						double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100 * SCREEN_WIDTH/SCREEN_HEIGHT;
						double ballRotation = calc.findRotation(startX, startY, endX, endY);
						SimulationBall simBall = new SimulationBall(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
						simBall.setObjects(objects);
						simBall.setBelts(belts);
						simBall.simulate(g, this);
					}
				}
			}

		g.setColor(Color.BLUE);
		for(Ball b: balls)
			b.draw(g, this);

		for(int i = 0; i < balls.size(); i++)
		{
			if(balls.get(i).isInHole())
				wonGame = true;
			if(balls.get(i).shouldDelete())
			{
				balls.remove(i);
				i--;
			}
		}
		this.requestFocus();
		repaint();
	}

	public void shootBall()
	{
		if(altDown)
			shootBurst();
		else if(capsLockDown)
			if(shiftDown)
				shootBurstRing();
			else
				shootRing();
		else if(shiftDown)
			shootBrick();
		else
			shootNormal();
	}
	public void shootBurst()
	{
		for(double n = 0; n < 10; n += .1)
		{
			Ball newBall;
			int ballRadius = defaultBallRadius;
			double ballX = endX;
			double ballY = endY;
			double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT) * n;
			double ballRotation = calc.findRotation(startX, startY, endX, endY);
			newBall = new Ball(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
			balls.add(newBall);
			newBall.setObjects(objects);
			newBall.setImage(ballImage);
			newBall.setBelts(belts);
			strokes++;
		}
	}
	public void shootBurstRing()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int n = 0; n < 20 * (i + 1); n++)
			{
				Ball newBall;
				int ballRadius = defaultBallRadius;
				double ballX = endX;
				double ballY = endY;
				double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT) + i;
				double ballRotation = calc.findRotation(startX, startY, endX, endY) + n * 360/(20 * (i +1));
				newBall = new Ball(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
				balls.add(newBall);
				newBall.setObjects(objects);
				newBall.setImage(ballImage);
				newBall.setBelts(belts);
				strokes++;
			}
		}
	}
	public void shootRing()
	{
		for(int i = 0; i < 60; i++)
		{
			Ball newBall;
			int ballRadius = defaultBallRadius;
			double ballX = endX;
			double ballY = endY;
			double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT);
			double ballRotation = calc.findRotation(startX, startY, endX, endY) + i * 6;
			newBall = new Ball(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
			balls.add(newBall);
			newBall.setObjects(objects);
			newBall.setImage(ballImage);
			newBall.setBelts(belts);
			strokes++;
		}
	}
	public void shootBrick()
	{
		for(int i = 0; i < 10; i++)
		{
			for(int n = 0; n < 10; n++)
			{
				Ball newBall;
				int ballRadius = defaultBallRadius;
				double ballX = endX + (i-5)*10;
				double ballY = endY + (n-5)*10;
				double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100*(SCREEN_WIDTH/SCREEN_HEIGHT);
				double ballRotation = calc.findRotation(startX, startY, endX, endY);
				newBall = new Ball(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
				balls.add(newBall);
				newBall.setObjects(objects);
				newBall.setImage(ballImage);
				newBall.setBelts(belts);
				strokes++;
			}
		}
	}
	public void shootNormal()
	{
		Ball newBall;
		int ballRadius = defaultBallRadius;
		double ballX = endX;
		double ballY = endY;
		double ballSpeed = calc.getDistance(startX, startY, endX, endY)/100 * SCREEN_WIDTH/SCREEN_HEIGHT;
		double ballRotation = calc.findRotation(startX, startY, endX, endY);
		newBall = new Ball(ballX, ballY, ballSpeed, ballRotation, ballRadius, SCREEN_WIDTH, SCREEN_HEIGHT);
		balls.add(newBall);
		newBall.setObjects(objects);
		newBall.setImage(ballImage);
		newBall.setBelts(belts);
		strokes++;
	}

	public void setupScreen()
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize);
	}
	public void initVars()
	{
		balls = new ArrayList<Ball>();
		defaultBallRadius = 30;
		gen = new Random();
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
		dragRotation = 0;
		mouse = null;
		mouseDown = false;
		images = level.getImages();
		calc = new MathObject();
		wonGame = false;
		strokes = 0;
		shiftDown = false;
		controlDown = false;
		capsLockDown = false;
		altDown = false;
		ballImageUrl = images[0];
		ballImage = createImage(30, 30, ballImageUrl);
		canCheat = false;
	}
	public void createObjects()
	{
		objects = new ArrayList<GravityObject>();
		tee = new Tee((int) (level.getX(0) * SCREEN_WIDTH), (int) (level.getY(0) * SCREEN_HEIGHT), (int)(.1 * SCREEN_WIDTH), (int)(SCREEN_HEIGHT  * .1));
		for(int i = 1; i < level.getNumItems() - 1; i++)
		{
			objects.add(new Planet(level.getX(i) * SCREEN_WIDTH, level.getY(i) * SCREEN_HEIGHT, level.getMass(i), level.getRadius(i), level.getForceRadius(i)));
		}
		hole = new Hole(level.getX(level.getNumItems() - 1) * SCREEN_WIDTH, level.getY(level.getNumItems() - 1) * SCREEN_HEIGHT, level.getMass(level.getNumItems() - 1), level.getRadius(level.getNumItems() - 1), level.getForceRadius(level.getNumItems() - 1));
		objects.add(hole);
	}
	public void loadBackground()
	{
		backgroundImage =  new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		Graphics g = backgroundImage.getGraphics();

		Image backG = createImage(SCREEN_WIDTH, SCREEN_HEIGHT, images[images.length - 2]);

		g.drawImage(backG, 0, 0, this);

		tee.draw(g);

		for(int i = 0; i < objects.size(); i++)
			if(objects.get(i).isHole())
				objects.get(i).draw(g, createImage(objects.get(i).getRadius(), objects.get(i).getRadius(), images[images.length - 1]),this);
			else
				objects.get(i).draw(g, createImage(objects.get(i).getRadius(), objects.get(i).getRadius(), images[gen.nextInt(images.length - 5) + 3]),this);

		belts = level.getBelts();
		for(int i = 0; i < belts.length; i++)
		{
			AsteroidBelt belt = belts[i];
			double beltSX = belt.getStartX() * SCREEN_WIDTH;
			double beltSY = belt.getStartY() * SCREEN_HEIGHT;
			double beltEX = belt.getEndX() * SCREEN_WIDTH;
			double beltEY = belt.getEndY() * SCREEN_HEIGHT;
			double beltLength = calc.getDistance(beltSX, beltSY, beltEX, beltEY);
			//g.drawLine((int) beltSX, (int) beltSY, (int) beltEX, (int) beltEY);
			for(int n = 0; n < beltLength/20; n++)
			{
				g.drawImage(createImage(30, 30, images[gen.nextInt(3)]), (int)(beltSX + (beltEX - beltSX) * (n/(beltLength/20))  + gen.nextInt(40) - 20), (int)(beltSY + (beltEY - beltSY) * (n/(beltLength/20))) + gen.nextInt(40) - 20, this);
			}
		}

		g.dispose();
	}

	public Image createImage(int width, int height, String path)
	{
		Image image = null;
		try
		{
			image = ImageIO.read(new File(path));
			image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		}
		catch(IOException e){}
		return image;
	}
	public boolean hasWon()
	{
		return wonGame;
	}

	public int getStrokes()
	{
		return strokes;
	}

	public void canCheat(boolean b)
	{
		canCheat = b;
	}
	public boolean canCheat()
	{
		return canCheat;
	}

	public void mousePressed(MouseEvent m)
	{
		startX = m.getX();
		startY = m.getY();
		if(endX == 0 && endY == 0)
		{
			endX = startX;
			endY = startY;
		}
		mouse = m;
		mouseDown = true;
	}
	public void mouseReleased(MouseEvent m)
	{
		if(canCheat && controlDown)
			shootBall();
		else
			if(endX > tee.getX() && endX < tee.getX() + tee.getWidth())
				if(endY > tee.getY() && endY < tee.getY() + tee.getHeight())
					shootBall();
		mouseDown = false;
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
	}
	public void mouseDragged(MouseEvent m)
	{
		if(mouseDown)
		{
			endX = m.getX();
			endY = m.getY();
			mouse = m;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if(canCheat)
		{
			int k = e.getKeyCode();
			if(k == KeyEvent.VK_SHIFT)
				shiftDown = true;
			if(k == KeyEvent.VK_CONTROL)
				controlDown = true;
			if(k == KeyEvent.VK_CAPS_LOCK)
				capsLockDown = true;
			if(k == KeyEvent.VK_K)
				ballImageUrl = "images/cat.png";
			if(k == KeyEvent.VK_B)
				defaultBallRadius = 100;
			if(k == KeyEvent.VK_ALT)
				altDown = true;
			if(k == KeyEvent.VK_G)
				gDown = true;

			ballImage = createImage(defaultBallRadius, defaultBallRadius, ballImageUrl);
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(canCheat)
		{
			int k = e.getKeyCode();
			if(k == KeyEvent.VK_SHIFT)
				shiftDown = false;
			if(k == KeyEvent.VK_CONTROL)
				controlDown = false;
			if(k == KeyEvent.VK_CAPS_LOCK)
				capsLockDown = false;
			if(k == KeyEvent.VK_K)
				ballImageUrl = images[0];
			if(k == KeyEvent.VK_B)
				defaultBallRadius = 30;
			if(k == KeyEvent.VK_ALT)
				altDown = false;
			if(k == KeyEvent.VK_G)
				gDown = false;

			ballImage = createImage(defaultBallRadius, defaultBallRadius, ballImageUrl);
		}
	}
	public void keyTyped(KeyEvent e){}

	public void mouseMoved(MouseEvent m){}
	public void mouseClicked(MouseEvent m){}
	public void mouseEntered(MouseEvent m){}
	public void mouseExited(MouseEvent m){}
}