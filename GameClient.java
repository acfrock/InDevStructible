import java.util.ArrayList;

import Terrain.Cover;
import Terrain.Elevation;

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
	
	static double calcAccuracy(Point pos1, Point pos2, Tile[][][] map){ //returns accuracy 0-100, baseline 80
		ArrayList<Point> pointsShotPassesThroughX = new ArrayList<Point>();
		ArrayList<Point> pointsShotPassesThroughY = new ArrayList<Point>();
		ArrayList<Point> allPoints = new ArrayList<Point>();
		double accuracy = 80.0;
		
		//find line of best fit
		double slope = ( pos2.getY() - pos1.getY() ) / ( pos2.getX() - pos1.getX() );
		double b = pos1.getY() - ( slope * pos1.getX() );
		
		//add points moving in x direction to x list
		for ( int i = pos1.getX(); i < pos2.getX(); i++){ 
			int y = (int) Math.round((slope * i) + b); 
			pointsShotPassesThroughX.add( new Point(i,y));
		}
		
		//add points moving in y direction to y list
		for ( int i = pos1.getY(); i < pos2.getY(); i++){ 
			int x = (int) Math.round((i - b)/slope ); 
			pointsShotPassesThroughY.add( new Point(x,i));
		}
		
		//add x points to all list
		for (int i = 0; i< pointsShotPassesThroughX.size(); i++){
			allPoints.add(pointsShotPassesThroughX.get(i));
		}
		//add y points to all list, check for repeats
		for (int i = 0; i< pointsShotPassesThroughY.size(); i++){
			boolean isRepeat = false;
			for( int j = 0; j < allPoints.size(); j++){
				if ( allPoints.get(j).getX() == pointsShotPassesThroughY.get(i).getX() && allPoints.get(j).getY() == pointsShotPassesThroughY.get(i).getY()){
					isRepeat = true;
				}
				if ((j + 1 == allPoints.size() ) && (isRepeat == false)){
					allPoints.add(pointsShotPassesThroughY.get(i));
				}
			}
		}
		
		//add and subtract acc's of each point
		for (int i = 0; i<allPoints.size(); i++){
			accuracy += calcAccModifier(allPoints.get(i), map);
		}
		
		//add and subtract based on elevation
		if(map[0][pos1.getX()][pos1.getY()] instanceof Terrain){ //start point
			Terrain point = (Terrain) map[1][pos1.getX()][pos1.getY()];
			if (point.elevation = Elevation.HIGH){
				accuracy += 15;
			}else if(point.elevation = Elevation.LOW){
				accuracy -= 15;
			}
		}
		
		if(map[0][pos2.getX()][pos2.getY()] instanceof Terrain){ //end point
			Terrain point = (Terrain) map[1][pos2.getX()][pos2.getY()];
			if (point.elevation = Elevation.HIGH){
				accuracy -= 15;
			}else if(point.elevation = Elevation.LOW){
				accuracy += 15;
			}
		}
		
		
		//subtract based on distance
		accuracy -= (calcDistance(pos1,pos2) * 4);
		
		
		if (accuracy > 100){
			return 100;
		}if (accuracy < 1){
			return 1;
		}else{
			return accuracy;
		}
	}
	
	static double calcAccModifier( Point pos, Tile[][][] map){
		double acc = 0;

		if (map[1][pos.getX()][pos.getY()] instanceof Terrain){
			Terrain point = (Terrain) map[1][pos.getX()][pos.getY()];
			if (point.cover == Cover.FULL_COVER){
				acc-= 10;
			}else if(point.cover == Cover.HALF_COVER){
				acc -= 5;
			}
			
		}
		
		if (map[1][pos.getX()][pos.getY()] instanceof Unit){
			acc -= 7;
		}
		return acc;
	}

}
