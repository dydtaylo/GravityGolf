//AsteroidBelt.java
//Dylan Taylor

public class AsteroidBelt
{
	private double startX, startY, endX, endY;

	public AsteroidBelt(double sX, double sY, double eX, double eY)
	{
		startX = sX;
		startY = sY;
		endX = eX;
		endY = eY;
	}
	public double getStartX()
	{
		return startX;
	}
	public double getStartY()
	{
		return startY;
	}
	public double getEndX()
	{
		return endX;
	}
	public double getEndY()
	{
		return endY;
	}
}