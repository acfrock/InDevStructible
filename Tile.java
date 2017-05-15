
public class Tile 
{
	char displayChar;
	boolean isObstruction;
	int hitPoints;
	Point position;
	boolean isDamagable;
	
	public Tile(int x, int y)
	{
		displayChar = '.';
		isObstruction = false;
		hitPoints = 0;
		position = new Point(x,y);
		isDamagable = false;
	}
	
	public Tile(char disp, boolean obst, int hit, Point pos, boolean dmg)
	{
		displayChar = disp;
		isObstruction = obst;
		hitPoints = hit;
		position = pos;
		isDamagable = dmg;
	}
	 
	public void takeDmg()
	{
		hitPoints--;
	}
	
}
