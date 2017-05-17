
public class GameClient {
	
	public static void main(String[] args) {
		Unit[] units;
		Tile[][][] map = new Tile[2][16][16];
		Double[] averageShotAccuracy;
		int playerShots, playerHits;
		int alienShots, alienHits;
		
		
		

	}
	
	
	static double calcDistance(Point pos1, Point pos2){
		return Math.sqrt( Math.pow((pos2.getX() - pos1.getX()), 2) + Math.pow((pos2.getY() - pos1.getY()), 2));
	}

}
