//Level.java
//Dylan Taylor

public class Level
{
	private int numItems;
	private double [] xArr;
	private double [] yArr;
	private int [] massArr;
	private int [] radiusArr;
	private int [] forceRadiusArr;
	private String [] images;
	private AsteroidBelt[] belts;

	public Level(double[] xA, double[] yA, int[] massA, int[] radiusA, int[] forceRadiusA, int num, String[] imagesA, AsteroidBelt[] b)
	{
		xArr = xA;
		yArr = yA;
		massArr = massA;
		radiusArr = radiusA;
		forceRadiusArr = forceRadiusA;
		numItems = num;
		images = imagesA;
		belts = b;
	}

	public double [] getXArr()
	{
		return xArr;
	}
	public double getX(int index)
	{
		if(index >= 0 && index < xArr.length)
			return xArr[index];
		else
			return -1;
	}
	public double [] getYArr()
	{
		return yArr;
	}
	public double getY(int index)
	{
		if(index >= 0 && index < yArr.length)
			return yArr[index];
		else
			return -1;
	}

	public int [] getMassArr()
	{
		return massArr;
	}
	public int getMass(int index)
	{
		if(index >= 0 && index < massArr.length)
			return massArr[index];
		else
			return -1;
	}

	public int [] getRadiusArr()
	{
		return radiusArr;
	}
	public int getRadius(int index)
	{
		if(index >= 0 && index < radiusArr.length)
			return radiusArr[index];
		else
			return -1;
	}

	public int [] getForceRadiusArr()
	{
		return forceRadiusArr;
	}
	public int getForceRadius(int index)
	{
		if(index >= 0 && index < forceRadiusArr.length)
			return forceRadiusArr[index];
		else
			return -1;
	}

	public int getNumItems()
	{
		return numItems;
	}

	public String [] getImages()
	{
		return images;
	}
	public String getImage(int index)
	{
		return images[index];
	}

	public AsteroidBelt[] getBelts()
	{
		return belts;
	}
}