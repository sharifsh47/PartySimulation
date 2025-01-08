package party;

import java.util.ArrayList;

public class Agent {
	private enum State {
		SEARCHING_BAR, AT_BAR, SEARCHING_DANCEFLOOR, ON_DANCEFLOOR, WAS_ON_DANCEFLOOR, AVOID_PEOPLE
	}

	private State state;
	private Direction PrevMove;
	private boolean moved;
	private int agentType = 0;
	private boolean atBar = false;
	private boolean onDanceFloor = false;
	private boolean aroundPeople = false;

	public Agent() {
		// this.state = State.SEARCHING_DANCEFLOOR; // Initialer Zustand
		agentType = (int) (Math.random() * 3 + 0);
		switch (agentType) {
		case 0:
			this.state = state.SEARCHING_BAR;
			break;
		case 1:
			this.state = state.SEARCHING_DANCEFLOOR;
			break;
		case 2:
			this.state = state.AVOID_PEOPLE;
			break;
		default:
			this.state = state.SEARCHING_DANCEFLOOR;
			break;
		}
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public void updateState() {
		switch (state) {
		case SEARCHING_DANCEFLOOR:
			if (onDanceFloor) {
				state = State.ON_DANCEFLOOR;
			}
			break;
		case ON_DANCEFLOOR:
			if (!onDanceFloor) {
				state = State.WAS_ON_DANCEFLOOR;
			}
			break;
		case SEARCHING_BAR:
			if (atBar) {
				state = State.AT_BAR;
			}
			break;
		case AT_BAR:
			// state = State.SEARCHING_DANCEFLOOR;
			break;
		case WAS_ON_DANCEFLOOR:
			if (onDanceFloor) {
				state = State.ON_DANCEFLOOR;
			}
			if (!moved) {
				state = State.SEARCHING_DANCEFLOOR;
			}
			break;
		case AVOID_PEOPLE:
			break;
		}
	}

	public Direction move() {
		updateState();

		switch (state) {
		case SEARCHING_DANCEFLOOR:
			PrevMove = ValidMove();
			return PrevMove; // Gezieltes Bewegen Richtung Tanzfläche
		case ON_DANCEFLOOR:
			PrevMove = randomMover();
			return PrevMove;
		case SEARCHING_BAR:
			PrevMove = ValidMove();
			return PrevMove; // Gezieltes Suchen nach der Bar
		case AT_BAR:
			return Direction.STAY;
		case WAS_ON_DANCEFLOOR:
			PrevMove = MoveBack();
			return PrevMove;
		case AVOID_PEOPLE:
			if (!aroundPeople) {
				int randomDirection = (int) (Math.random() * 10 + 0);
				switch (randomDirection) {
				case 0, 1, 2, 3, 4, 5:
					PrevMove = Direction.STAY;
					break;
				case 6:
					PrevMove = Direction.LEFT;
					break;
				case 7:
					PrevMove = Direction.RIGHT;
					break;
				case 8:
					PrevMove = Direction.TOP;
					break;
				case 9:
					PrevMove = Direction.DOWN;
					break;
				default:
					break;
				}
				return PrevMove;
			} else {
				PrevMove = randomMover();
				return PrevMove;
			}
		default:
			return Direction.STAY;
		}
	}

	private Direction randomMover() {
		int randomDirection = (int) (Math.random() * 4);

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
			return Direction.STAY;
		}
	}

	private Direction ValidMove() {
		Direction Move = randomMover();
		while (true) {
			switch (Move) {
			case LEFT:
				if (PrevMove != Direction.RIGHT) {
					return Move;
				} else {
					Move = randomMover();
				}
				break;
			case TOP:
				if (PrevMove != Direction.DOWN) {
					return Move;
				} else {
					Move = randomMover();
				}
				break;
			case RIGHT:
				if (PrevMove != Direction.LEFT) {
					return Move;
				} else {
					Move = randomMover();
				}
				break;
			case DOWN:
				if (PrevMove != Direction.TOP) {
					return Move;
				} else {
					Move = randomMover();
				}
				break;
			default:
				return Move;
			}
		}
	}

	private Direction MoveBack() {
		switch (PrevMove) {
		case LEFT:
			return Direction.RIGHT;
		case TOP:
			return Direction.DOWN;
		case RIGHT:
			return Direction.LEFT;
		case DOWN:
			return Direction.TOP;
		default:
			return Direction.STAY;
		}
	}

	public void setAtBar(boolean atBar) {
		this.atBar = atBar;
	}

	public void setOnDanceFloor(boolean onDanceFloor) {
		this.onDanceFloor = onDanceFloor;
	}

	public void setAroundPeople(boolean aroundPeople) {
		this.aroundPeople = aroundPeople;
	}

}