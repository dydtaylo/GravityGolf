//MathObject.java
//Dylan Taylor

public class MathObject
{
	public MathObject()
	{
		//System.out.println("ayy");
	}
	public double getDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	public double getDistance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	public double findRotation(int x1, int y1, int x2, int y2)
	{
		double r = 0;
		if(x1 != x2)
		{
			double lineHeight = y2 - y1;
			double lineWidth = x2 - x1;
			double invTan = Math.atan2(lineHeight, lineWidth);
			r = 180 - Math.toDegrees(invTan);
		}
		else
		{
			if(y2 > y1)
				r = 90;
			else
				r = 270;
		}
		r %= 360;
		return r;
	}
	public double findRotation(double x1, double y1, double x2, double y2)
	{
		double r = 0;
		if(x1 != x2)
		{
			double lineHeight = y2 - y1;
			double lineWidth = x2 - x1;
			double invTan = Math.atan2(lineHeight, lineWidth);
			r = 180 - Math.toDegrees(invTan);
		}
		else
		{
			if(y2 > y1)
				r = 90;
			else
				r = 270;
		}
		r %= 360;
		return r;
	}
	public double findRotation(double lineWidth, double lineHeight)
	{
		double r = 0;
		double invTan = Math.atan2(lineHeight, lineWidth);
		r = 180 - Math.toDegrees(invTan);

		r %= 360;
		return r;
	}
}