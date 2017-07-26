package game;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Terrain.Cover;
import game.Terrain.Elevation;

public class GameClient {

	static Tile[][][] map;
	static Button[][] buttons;

	static Player[] players;
	static Alien[] aliens;
	static Double[] averageShotAccuracy;
	static int playerShots, playerHits;
	static int alienShots, alienHits;
	static JFrame window = new JFrame("InDevStructible");

	static void makeMap(int n, int nbPlayers, int nbAliens) {
		//safety checks
		if (n * n <= nbPlayers + nbAliens + (n*n*.2) + 10){
			n = (int) Math.sqrt(nbPlayers + nbAliens + (n*n*.2) + 10) + 2;
		}
		
		map = new Tile[2][n][n];
		Random random = new Random();

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				map[0][x][y] = new Terrain(new Point(x, y));
			}
		}

		// ----terrain features

		int featureSize = 2 + random.nextInt((n / 3) - 1);
		int nbFeatures = n / 4;

		for (int i = 0; i < nbFeatures; i++) {
			int x = random.nextInt(n - featureSize);
			int y = random.nextInt(n - featureSize);
			boolean isUp = random.nextBoolean();

			// for each feature, increase/decrease elevation for all of
			// feature's spaces
			for (int fx = 0; fx < featureSize; fx++) {
				for (int fy = 0; fy < featureSize; fy++) {
					if (isUp) {
						((Terrain) map[0][x + fx][y + fy]).IncreaseElevation();
					} else {
						((Terrain) map[0][x + fx][y + fy]).DecreaseElevation();
					}

				}
			}

		}

		// ----create objects
		// create players
		players = new Player[nbPlayers];

		if (nbPlayers > 9) {
			nbPlayers = 9;
		}

		for (int i = 0; i < nbPlayers; i++) {
			int x = 0;
			int y = 0;
			if (i == 0) {
				x = n / 2;
				y = n / 2;
			} else if (i == 1) {
				x = (n / 2) + 1;
				y = (n / 2);
			} else if (i == 2) {
				x = (n / 2) + 1;
				y = (n / 2) - 1;
			} else if (i == 3) {
				x = (n / 2);
				y = (n / 2) - 1;
			} else if (i == 4) {
				x = (n / 2) - 1;
				y = (n / 2) - 1;
			} else if (i == 5) {
				x = (n / 2) - 1;
				y = (n / 2);
			} else if (i == 6) {
				x = (n / 2) - 1;
				y = (n / 2) + 1;
			} else if (i == 7) {
				x = (n / 2);
				y = (n / 2) + 1;
			} else if (i == 8) {
				x = (n / 2) + 1;
				y = (n / 2) + 1;
			}

			map[1][x][y] = new Player(new Point(x, y));
			players[i] = (Player) map[1][x][y];
		}

		// create aliens
		aliens = new Alien[nbAliens];
		
		for (int i = 0; i < nbAliens; i++){
			int x = random.nextInt(n);
			int y = random.nextInt(n);
			
			if(map[1][x][y] == null){
				map[1][x][y] = new Alien(new Point(x,y));
			}else{
				nbAliens++;
			}
		}

		// create rocks/bushes
		int nbBushes = n*n / 10;
		int nbRocks = n*n / 10;
		
		for (int i = 0; i < nbRocks; i++){
			int x = random.nextInt(n);
			int y = random.nextInt(n);
			
			if(map[1][x][y] == null){
				map[1][x][y] = Terrain.makeRock(new Point(x,y));
			}else{
				nbRocks++;
			}
		}
		
		
		for (int i = 0; i < nbBushes; i++){
			int x = random.nextInt(n);
			int y = random.nextInt(n);
			
			if(map[1][x][y] == null){
				map[1][x][y] = Terrain.makeBush(new Point(x,y));
			}else{
				nbBushes++;
			}
		}
		
		//finished map creation
	}

	

	public static void prepareWindow() {
		JPanel pnl = new JPanel(); // Panel is a container
		pnl.setLayout(new GridLayout(map[0].length, map[0][0].length));

		buttons = new Button[map[0].length][map[0][0].length];

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j] = new Button(" ");
				buttons[i][j].setBounds(i, j, 50, 50);
				pnl.add(buttons[i][j]);
			}
		}

		window.add(pnl);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public static void updateWindow() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if (map[1][i][j] != null) {
					buttons[i][j].setLabel("" + map[1][i][j].getDisplayChar());
				} else {
					buttons[i][j].setLabel("" + map[0][i][j].getDisplayChar());
				}
			}
		}
	}

	static double calcAccModifier(Point pos, Tile[][][] map) {
		double acc = 0;

		if (map[1][pos.getX()][pos.getY()] instanceof Terrain) {
			Terrain point = (Terrain) map[1][pos.getX()][pos.getY()];
			if (point.cover == Cover.FULL_COVER) {
				acc -= 10;
			} else if (point.cover == Cover.HALF_COVER) {
				acc -= 5;
			}

		}

		if (map[1][pos.getX()][pos.getY()] instanceof Unit) {
			acc -= 7;
		}
		return acc;
	}

	private static class moveFromAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		}
	}

	private static class moveToAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		}
	}

	private static class shootAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		}
	}

	static double calcAccuracy(Point pos1, Point pos2, Tile[][][] map) { // returns
																			// accuracy
																			// 0-100,
																			// baseline
																			// 80
		ArrayList<Point> pointsShotPassesThroughX = new ArrayList<>();
		ArrayList<Point> pointsShotPassesThroughY = new ArrayList<>();
		ArrayList<Point> allPoints = new ArrayList<>();
		double accuracy = 80.0;

		// find line of best fit
		double slope = (pos2.getY() - pos1.getY()) / (pos2.getX() - pos1.getX());
		double b = pos1.getY() - (slope * pos1.getX());

		// add points moving in x direction to x list
		for (int i = pos1.getX(); i < pos2.getX(); i++) {
			int y = (int) Math.round((slope * i) + b);
			pointsShotPassesThroughX.add(new Point(i, y));
		}

		// add points moving in y direction to y list
		for (int i = pos1.getY(); i < pos2.getY(); i++) {
			int x = (int) Math.round((i - b) / slope);
			pointsShotPassesThroughY.add(new Point(x, i));
		}

		// add x points to all list
		for (int i = 0; i < pointsShotPassesThroughX.size(); i++) {
			allPoints.add(pointsShotPassesThroughX.get(i));
		}
		// add y points to all list, check for repeats
		for (int i = 0; i < pointsShotPassesThroughY.size(); i++) {
			boolean isRepeat = false;
			for (int j = 0; j < allPoints.size(); j++) {
				if ((allPoints.get(j).getX() == pointsShotPassesThroughY.get(i).getX())
						&& (allPoints.get(j).getY() == pointsShotPassesThroughY.get(i).getY())) {
					isRepeat = true;
				}
				if (((j + 1) == allPoints.size()) && (isRepeat == false)) {
					allPoints.add(pointsShotPassesThroughY.get(i));
				}
			}
		}

		// add and subtract acc's of each point
		for (int i = 0; i < allPoints.size(); i++) {
			accuracy += calcAccModifier(allPoints.get(i), map);
		}

		// add and subtract based on elevation
		if (map[0][pos1.getX()][pos1.getY()] instanceof Terrain) { // start
																	// point
			Terrain point = (Terrain) map[1][pos1.getX()][pos1.getY()];
			if (point.elevation == Elevation.HIGH) {
				accuracy += 15;
			} else if (point.elevation == Elevation.LOW) {
				accuracy -= 15;
			}
		}

		if (map[0][pos2.getX()][pos2.getY()] instanceof Terrain) { // end point
			Terrain point = (Terrain) map[1][pos2.getX()][pos2.getY()];
			if (point.elevation == Elevation.HIGH) {
				accuracy -= 15;
			} else if (point.elevation == Elevation.LOW) {
				accuracy += 15;
			}
		}

		// subtract based on distance
		accuracy -= (calcDistance(pos1, pos2) * 4);

		if (accuracy > 100) {
			return 100;
		}
		if (accuracy < 1) {
			return 1;
		} else {
			return accuracy;
		}
	}

	static double calcDistance(Point pos1, Point pos2) {
		return Math.sqrt(Math.pow((pos2.getX() - pos1.getX()), 2) + Math.pow((pos2.getY() - pos1.getY()), 2));
	}

	public static void main(String[] args) {
		makeMap(20, 1, 5);
		prepareWindow();
		updateWindow();
	}

}
