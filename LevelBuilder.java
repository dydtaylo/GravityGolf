//LevelBuilder.java
//Dylan Taylor

public class LevelBuilder
{
	private Level[] levels;

	public static void main(String [] args)
	{
		new Tester();
	}

	public LevelBuilder()
	{
		levels = new Level[7];

		levels[0] =
		new Level(new double[]{.1, .2, .2, .4, .4, .6, .6, .8, .8, .8}, //x-coordinates
		new double[]{.435, .2, .7, .2, .7, .2, .7, .2, .7, .435}, //y-coordinates
		new int[]{0, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 100000}, //masses
		new int[]{0, 50, 50, 50, 50, 50, 50, 50, 50, 50}, //radii
		new int[]{0, 300, 300, 300, 300, 300, 300, 300, 300, 150}, //force radii
		10, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{}); //asteroid belts

		levels[1] =
		new Level(new double[]{.05, .4, .4, .8}, //x-coordinates
		new double[]{.385, .275, .475, .4}, //y-coordinates
		new int[]{0, 50000, 50000, 100000}, //masses
		new int[]{0, 100, 100, 50}, //radii
		new int[]{0, 350, 350, 150}, //force radii
		4, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{}); //asteroid belts

		levels[2] =
		new Level(new double[]{.05, .4, .8}, //x-coordinates
		new double[]{.4, .4, .4}, //y-coordinates
		new int[]{0, 100000, 100000}, //masses
		new int[]{0, 100, 50}, //radii
		new int[]{0, 600, 150}, //force radii
		3, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{}); //asteroid belts

		levels[3] =
		new Level(new double[]{.4, .25, .8}, //x-coordinates
		new double[]{.8, .25, .2}, //y-coordinates
		new int[]{0, 150000, 100000}, //masses
		new int[]{0, 300, 50}, //radii
		new int[]{0, 800, 150}, //force radii
		3, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{new AsteroidBelt(.5, .5, 1, 1)}); //asteroid belts

		levels[4] =
		new Level(new double[]{.1, .1, .8}, //x-coordinates
		new double[]{.8, .2, .2}, //y-coordinates
		new int[]{0, 60000, 100000}, //masses
		new int[]{0, 100, 50}, //radii
		new int[]{0, 600, 150}, //force radii
		3, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{new AsteroidBelt(.2, .3, 1, 1)}); //asteroid belts

		levels[5] =
		new Level(new double[]{.15, .25, .6, .85}, //x-coordinates
		new double[]{.5, .2, .8, .4}, //y-coordinates
		new int[]{0, 20000, 17500, 100000}, //masses
		new int[]{0, 75, 75, 50}, //radii
		new int[]{0, 475, 475, 350}, //force radii
		4, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{new AsteroidBelt(.275, .3, .275, 1), new AsteroidBelt(.625, 0, .625, .75)}); //asteroid belts

		levels[6] =
		new Level(new double[]{.1, .2, .4, .6, .85}, //x-coordinates
		new double[]{.5, .2, .8, .2, .5}, //y-coordinates
		new int[]{0, 20000, 17500, 15000, 100000}, //masses
		new int[]{0, 75, 75, 75, 50}, //radii
		new int[]{0, 475, 475, 475, 350}, //force radii
		5, //number of objects
		new String[]{"images/asteroid.png", "images/asteroid2.png", "images/asteroid3.png", "images/planet1.png", "images/planet2.png", "images/planet3.png", "images/planet4.png", "images/planet4.png", "images/space1.jpg", "images/flag.png"}, //image paths
		new AsteroidBelt[]{new AsteroidBelt(.225, .3, .225, .475), new AsteroidBelt(.225, .525, .225, 1), new AsteroidBelt(.425, 0, .425, .475), new AsteroidBelt(.425, .525, .425, .775), new AsteroidBelt(.625, .3, .625, .475), new AsteroidBelt(.625, .525, .625, 1)}); //asteroid belts
	}

	public Level[] getLevels()
	{
		return levels;
	}
}