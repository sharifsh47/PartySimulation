package party;

public class Obstacle {
	private int x;
	private int y;
	private int width;
	private int height;

	public Obstacle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isWithin(int cellX, int cellY) {
		return cellX >= x && cellX < x + width && cellY >= y && cellY < y + height;
	}

	public int getX() { 
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}