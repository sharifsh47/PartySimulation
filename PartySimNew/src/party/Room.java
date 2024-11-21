package party;

import java.awt.Point;
import java.util.ArrayList;

public class Room {
    private int k;
    private int l;
    private Agent[][] places;
    private ArrayList<Point> agentCoord;

    public Room(int Rows, int Columns) {
        super();
        k = Columns;
        l = Rows;
        places = new Agent[k][l];
        agentCoord = new ArrayList<>();
    }

    public int getK() {
        return k;
    }

    public int getL() {
        return l;
    }

    public ArrayList<Point> getCoordinates() {
        return agentCoord;
    }

    public boolean addAgent() {
        int x = 0; // Entry point at the leftmost column
        int y = l / 2; // Middle of the grid vertically

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

  
  public void navigate() {
        
        for (int i = 0; i < agentCoord.size(); i++) {
            Point coord = agentCoord.get(i);
            System.out.println("Agent #" + i + " at (" + coord.x + ", " + coord.y + ")");
        }

        // Loop over each agent for movement
        for (int agentIndex = 0; agentIndex < agentCoord.size(); agentIndex++) {
            Point coordinate = agentCoord.get(agentIndex);
            int i = coordinate.x;
            int j = coordinate.y;

            if (places[i][j] != null) {
                Agent agent = places[i][j];
                Direction direction = agent.move();
                int newI = i, newJ = j;

                switch (direction) {
                    case LEFT:
                        newI--;
                        break;
                    case RIGHT:
                        newI++;
                        break;
                    case TOP:
                        newJ--;
                        break;
                    case DOWN:
                        newJ++;
                        break;
                }

                if(newI >= 0 && newI < k && newJ >= 0 && newJ < l) {
    				if (places[newI][newJ] == null) {
                    places[i][j] = null;
                    places[newI][newJ] = agent;
                    agentCoord.set(agentIndex, new Point(newI, newJ));
                    System.out.println("Agent #" + agentIndex + " moved " + direction);
                } else {
                    System.out.println("Agent #" + agentIndex + " couldn't move " + direction + " due to collision or boundary");
                }
            }
        }

     
        }}}

    

