//Hole.java
//Dylan Taylor

public class Hole extends GravityObject
{
	public Hole(double xPos, double yPos, int m, int r, int fR)
	{
		super(xPos, yPos, m, r, fR);
		isHole = true;
	}
}