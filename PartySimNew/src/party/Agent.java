package party;

public class Agent {

	public Direction move() {

		int randomDirection = (int) (Math.random() * 4 + 0);
		System.out.println(randomDirection);
		switch (randomDirection) {
		case 0:
			return Direction.LEFT;

		case 1:
			return Direction.TOP;

		case 2:
			return Direction.RIGHT;

		case 3:
			return Direction.DOWN;

		default:
			break;

		}
		return null;
	}

}
