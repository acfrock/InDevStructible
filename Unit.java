
public abstract class Unit extends Tile{

	public Unit(char disp, boolean obst, int hit, Point pos, boolean dmg) {
		super(disp, obst, hit, pos, dmg);
	}

	public void moveTo(Point p){
		this.position = p;
	}
	
}
