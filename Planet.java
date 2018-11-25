//Planet.java
//Dylan Taylor

public class Planet extends GravityObject
{
	public Planet(double xPos, double yPos, int m, int r, int fR)
	{
		super(xPos, yPos, m, r, fR);
		isHole = false;
	}
}