package game;

public class Terrain extends Tile
{
    public enum Cover
    {
        FULL_COVER, HALF_COVER, NO_COVER;
    }

    public enum Elevation
    {
        HIGH, MEDIUM, LOW;
    }

    public static Terrain makeBush(Point p)
    {
        return new Terrain('M', true, 2, p, true, Cover.HALF_COVER, Elevation.LOW);
    }

    public static Terrain makeRock(Point p)
    {
        return new Terrain('O', true, 2, p, true, Cover.FULL_COVER, Elevation.LOW);
    }

    Cover     cover;

    Elevation elevation;

    public Terrain(char disp, boolean obst, int hit, Point pos, boolean dmg, Cover c, Elevation e)
    {
        super(disp, obst, hit, pos, dmg);
        cover = c;
        elevation = e;
    }

    public Terrain(Point pos){
    	super('_', false, 0, pos, false);
    	cover = Cover.NO_COVER;
    	elevation = Elevation.MEDIUM;
    }
    
    public void IncreaseElevation(){
    	if( elevation == Elevation.MEDIUM){
    		elevation = Elevation.HIGH;
    	}else if (elevation == Elevation.LOW){
    		elevation = Elevation.MEDIUM;
    	}
    }
    
    public void DecreaseElevation(){
    	if(elevation == Elevation.MEDIUM){
    		elevation = Elevation.LOW;
    	}else if (elevation == Elevation.HIGH){
    		elevation = Elevation.MEDIUM;
    	}
    }
    
    
}
