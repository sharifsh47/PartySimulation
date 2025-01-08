package visualisation;

import party.Agent;
import party.Obstacle;
import party.Room;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Visualisation extends JPanel {
	private int width;
	private int height;
	private int gridRows, gridColumns;
	private Room room;
	private Graphics pen;

	public Visualisation(int width, int height, int numAgents, int numObstacles, int rows, int columns) {
		this.width = width;
		this.height = height;
		constructRoom(numAgents, numObstacles, rows, columns);
	}

	public void constructRoom(int numAgents, int numObstacles, int rows, int columns) {
		room = new Room(numAgents, numObstacles, rows, columns);
	}

	protected void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		this.pen = pen;
		drawRoomBorders();
		drawDanceFloor();
		if(room != null) {
			drawEntities(pen);
		}
	}

	public void drawRoomBorders() {
		pen.drawRect(gridRows, gridColumns, width, height);
	}

	public void drawAgentAt(int x, int y) {
		if (pen != null) {
			pen.setColor(Color.GREEN);
			pen.fillRect(x, y, width / room.getGridRows(), height / room.getGridColumns());

			// Added to draw an outline around the agent
			pen.setColor(Color.BLACK);
			pen.drawRect(x, y, width / room.getGridRows(), height / room.getGridColumns());
		}
	}

	public int[] locates(int i, int j) {
		int[] r = new int[2];
		r[0] = (width * i) / room.getGridRows() + gridRows;
		r[1] = (height * j) / room.getGridColumns() + gridColumns;
		return r;
	}

	public void drawBarArea() {
		if (pen != null) {
			int barX = gridRows + width / 5;
			int barY = gridColumns + height - height / 5;
			int barWidth = width / 5;
			int barHeight = height / 10;
			pen.fillRect(barX, barY, barWidth, barHeight);
		}
	}

	public void drawDanceFloor() {
		if (pen != null) {
			int danceX = gridRows + width / 3;
			int danceY = gridColumns + height / 3;
			int danceWidth = width / 3;
			int danceHeight = height / 3;
			pen.setColor(Color.PINK); 
			pen.fillRect(danceX, danceY, danceWidth, danceHeight);
			pen.setColor(Color.BLACK);
			pen.drawRect(danceX, danceY, danceWidth, danceHeight);
		}
	}

	public void drawEntities(Graphics pen) {
		int cellWidth = width / room.getGridRows();
		int cellHeight = height / room.getGridColumns();

		// Loop through all cells in the grid
		for (int i = 0; i < room.getGridRows(); i++) {
			for (int j = 0; j < room.getGridColumns(); j++) {
				if (room.places[i][j] != null) {
					int[] coords = locates(i, j);
					int x = coords[0];
					int y = coords[1];

					// Check what type of object is in the cell
					if (room.places[i][j] instanceof Agent) {
						if (room.isOnDanceFloor(i, j))
							pen.setColor(Color.BLUE); // Blue if on dance floor
						else
							pen.setColor(Color.GREEN); // Default color
						pen.fillRect(x, y, cellWidth, cellHeight);
						pen.setColor(Color.BLACK);
						pen.drawRect(x, y, cellWidth, cellHeight);
					}
					else if (room.isAtBar(i, j)) {
						pen.setColor(Color.ORANGE);
						pen.fillRect(x, y, cellWidth, cellHeight);
					}
					else if (room.places[i][j] instanceof Obstacle) {
						pen.setColor(Color.RED);
						pen.fillRect(x, y, cellWidth, cellHeight);
						pen.setColor(Color.BLACK);
						pen.drawRect(x, y, cellWidth, cellHeight);
					}
				}
			}
		}
	}

	public Room getRoom() {
		return room;
	}
}