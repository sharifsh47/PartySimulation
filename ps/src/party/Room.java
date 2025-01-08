package party;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

public class Room {
	private int gridRows, gridColumns;
	public Object[][] places;
	private ArrayList<Point> agentCoord;
	private ArrayList<Point> danceFloorCoord;
	private int barX, barY, barWidth, barHeight;

	public Room(int numAgents, int numObstacles, int rows, int columns) {
		gridRows = rows;
		gridColumns = columns;
		places = new Object[gridRows][gridColumns];
		agentCoord = new ArrayList<>();
		danceFloorCoord = new ArrayList<>();
		addBarArea();
		defineDanceFloor();
		addObstacles(numObstacles);
	}

	private void addBarArea() {
		barX = gridRows / 5;
		barY = gridColumns - gridColumns / 5;
		barWidth = gridRows / 5;
		barHeight = gridColumns / 10;

		for (int x = barX; x < barX + barWidth; x++) {
			for (int y = barY; y < barY + barHeight; y++) {
				places[x][y] = new Bar(x, y, 1, 1);
			}
		}
	}

	private void addObstacles(int numObstacles) {
		for (int i = 0; i < numObstacles; i++) {
			boolean placed = false;
			while (!placed) {
				int x = (int) (Math.random() * gridRows); // Random x within grid width
				int y = (int) (Math.random() * gridColumns); // Random y within grid height
				if (places[x][y] == null) { // Ensure position is unoccupied
					places[x][y] = new Obstacle(x, y, 1, 1); // Place the obstacle
					placed = true; // Successfully placed the obstacle
				}
			}
		}
	}

	public boolean addAgent() {
		int x = 0; // Entry point at the leftmost column
		int y = gridColumns / 2; // Middle of the grid vertically
		if (places[x][y] == null) { // Check if entry point is free
			Agent agent = new Agent();
			places[x][y] = agent;
			agentCoord.add(new Point(x, y));
			System.out.println("New Agent added ");
			return true; // Successfully added an agent
		} else {
			System.out.println("Entry point is occupied. Waiting for it to clear.");
			return false; // Entry point is occupied
		}
	}

	private void defineDanceFloor() {
		int startX = gridRows / 3;
		int startY = gridColumns / 3;
		int width = gridRows / 3;
		int height = gridColumns / 3;

		for (int x = startX; x < startX + width; x++) {
			for (int y = startY; y < startY + height; y++) {
				danceFloorCoord.add(new Point(x, y));
			}
		}
	}

	public boolean isOnDanceFloor(int x, int y) {
		return danceFloorCoord.contains(new Point(x, y));
	}

	public boolean isNearBar(int x, int y) {
		return Math.abs(x - barX) <= 1 && Math.abs(y - barY) <= 1;
	}

	public boolean isAtBar(int x, int y) {
		return x >= barX && x < barX + barWidth && y >= barY && y < barY + barHeight;
	}

	public void navigateAgents() {
		for (int i = 0; i < agentCoord.size(); i++) {
			Point currentPos = agentCoord.get(i);
			Agent agent = (Agent) places[currentPos.x][currentPos.y];
			if (agent != null) {
				verifySurroundings(agent, currentPos);
				// boolean onDanceFloor = isOnDanceFloor(currentPos.x, currentPos.y);
				// boolean nearBar = isNearBar(currentPos.x, currentPos.y);
				// boolean atBar = isAtBar(currentPos.x, currentPos.y);

				Direction direction = agent.move();
				Point newPos = getNewPosition(currentPos, direction);

				if (isValidMove(newPos.x, newPos.y)) {
					places[currentPos.x][currentPos.y] = null;
					places[newPos.x][newPos.y] = agent;
					agentCoord.set(i, newPos);
					agent.setMoved(true);
				} else {
					agent.setMoved(false);
				}
			}
		}
	}

	private Point getNewPosition(Point currentPos, Direction direction) {
		int x = currentPos.x;
		int y = currentPos.y;

		switch (direction) {
		case LEFT:
			x--;
			break;
		case RIGHT:
			x++;
			break;
		case TOP:
			y--;
			break;
		case DOWN:
			y++;
			break;
		default:
			break;
		}
		return new Point(x, y);
	}

	private boolean isValidMove(int x, int y) {
		return x >= 0 && x < gridRows && y >= 0 && y < gridColumns && places[x][y] == null;
	}

	public int getGridColumns() {
		// TODO Auto-generated method stub
		return gridColumns;
	}

	public int getGridRows() {
		// TODO Auto-generated method stub
		return gridRows;
	}

	public Vector<Integer> atTheBoarder(Point currentPos) {
		Vector<Integer> vec = new Vector<>();
		if (currentPos.x == 0) {
			vec.add(0);
		}
		if (currentPos.x == gridRows - 1) {
			vec.add(1);
		}
		if (currentPos.y == 0) {
			vec.add(2);
		}
		if (currentPos.y == gridColumns - 1) {
			vec.add(3);
		}
		return vec;
	}

	public void verifySurroundings(Agent agent, Point currentPos) {
		Vector<Integer> vec = atTheBoarder(currentPos);

		if (isOnDanceFloor(currentPos.x, currentPos.y)) {
			agent.setOnDanceFloor(true);
		} else {
			agent.setOnDanceFloor(false);
		}

		if (vec.size() == 0) {
			if (places[currentPos.x + 1][currentPos.y] instanceof Bar
					|| places[currentPos.x - 1][currentPos.y] instanceof Bar
					|| places[currentPos.x][currentPos.y + 1] instanceof Bar
					|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
				agent.setAtBar(true);
			} else {
				agent.setAtBar(false);
			}

			if (places[currentPos.x + 1][currentPos.y] instanceof Agent
					|| places[currentPos.x - 1][currentPos.y] instanceof Agent
					|| places[currentPos.x][currentPos.y + 1] instanceof Agent
					|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
				agent.setAroundPeople(true);
			} else {
				agent.setAroundPeople(false);
			}
		} else if (vec.size() == 1) {
			switch (vec.get(0)) {
			case 0:
				if (places[currentPos.x + 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y + 1] instanceof Bar
						|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x + 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y + 1] instanceof Agent
						|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case 1:
				if (places[currentPos.x - 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y + 1] instanceof Bar
						|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x - 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y + 1] instanceof Agent
						|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case 2:
				if (places[currentPos.x + 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y + 1] instanceof Bar
						|| places[currentPos.x - 1][currentPos.y] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x + 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y + 1] instanceof Agent
						|| places[currentPos.x - 1][currentPos.y] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case 3:
				if (places[currentPos.x + 1][currentPos.y] instanceof Bar
						|| places[currentPos.x - 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x + 1][currentPos.y] instanceof Agent
						|| places[currentPos.x - 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			}
		} else if (vec.size() == 2) {
			String var = String.valueOf(vec.get(0)) + String.valueOf(vec.get(1));
			switch (var) {
			case "02":
				if (places[currentPos.x + 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y + 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x + 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y + 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case "03":
				if (places[currentPos.x + 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x + 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case "12":
				if (places[currentPos.x - 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y + 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x - 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y + 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			case "13":
				if (places[currentPos.x - 1][currentPos.y] instanceof Bar
						|| places[currentPos.x][currentPos.y - 1] instanceof Bar) {
					agent.setAtBar(true);
				} else {
					agent.setAtBar(false);
				}

				if (places[currentPos.x - 1][currentPos.y] instanceof Agent
						|| places[currentPos.x][currentPos.y - 1] instanceof Agent) {
					agent.setAroundPeople(true);
				} else {
					agent.setAroundPeople(false);
				}
				break;
			}
		}
	}
}