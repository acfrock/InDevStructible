
public class Terrain extends Tile{
	Cover cover;
	Elevation elevation;

	public Terrain(char disp, boolean obst, int hit, Point pos, boolean dmg, Cover c, Elevation e) {
		super(disp, obst, hit, pos, dmg);
		cover = c;
		elevation = e;
	}
	
	enum Cover{
		FULL_COVER, HALF_COVER, NO_COVER;
	}
	
	enum Elevation{
		HIGH,MEDIUM,LOW;
	}
	
	public static Terrain makeRock(Point p){
		return new Terrain('O',true,2,p,true,Cover.FULL_COVER,Elevation.LOW);
	}
	
	public static Terrain makeBush(Point p){
		return new Terrain('M',true,2,p,true,Cover.HALF_COVER,Elevation.LOW);
	}
	
	
}
