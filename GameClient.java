import java.util.ArrayList;

import Terrain.Cover;

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
	
	static double calcAccuracy(Point pos1, Point pos2, Tile[][][] map){
		ArrayList<Point> pointsShotPassesThroughX = new ArrayList<Point>();
		ArrayList<Point> pointsShotPassesThroughY = new ArrayList<Point>();
		
		//find line of best fit
		double slope = ( pos2.getY() - pos1.getY() ) / ( pos2.getX() - pos1.getX() );
		double b = pos1.getY() - ( slope * pos1.getX() );
		
		for ( int i = pos1.getX(); i < pos2.getX(); i++){ //add points moving in x direction
			int y = (int) Math.round((slope * i) + b); 
			pointsShotPassesThroughX.add( new Point(i,y));
		}
		
		for ( int i = pos1.getY(); i < pos2.getY(); i++){ //add points moving in y direction
			int x = (int) Math.round((i - b)/slope ); 
			pointsShotPassesThroughY.add( new Point(x,i));
		}
		
		//calc x accuracy
		
		
		//calc y accuracy
		
		//add and subtract based on elevation
		
		//subtract based on distance
		
	}
	
	static double calcAccModifier( Point pos, Tile[][][] map){
		double acc = 0;

		if (map[1][pos.getX()][pos.getY()] instanceof Terrain){
			Terrain point = (Terrain) map[1][pos.getX()][pos.getY()];
			if (point.cover == Cover.FULL_COVER){
				
			}
			
		}
		
	}

}
