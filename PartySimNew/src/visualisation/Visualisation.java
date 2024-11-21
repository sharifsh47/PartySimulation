package visualisation;
 
import party.Room;
import java.awt.*;
import java.util.ArrayList;
 
import javax.swing.*;
 
 
public class Visualisation extends JPanel {
    private int width;
    private int height;
    private int k, l;
    private Room room;
    private Graphics pen;
 
    public Visualisation(int width, int height, int numAgents, int Rows, int Columns) {
        this.width = width;
        this.height = height;
        // Construct the room with given offsets.
        constructRoom( Rows, Columns);
    }
   
    public void constructRoom( int Rows, int Columns) {
        room = new Room(Rows, Columns);
    }
 
    protected void paintComponent(Graphics pen) {
        super.paintComponent(pen);
        this.pen = pen;
        drawRoomBorders();
        drawBarArea();      // Added - Draw the bar area
        drawDanceFloor();   // Added - Draw the dance floor
        if(room != null) {
            drawAgents();
        }
    }
   
    public void drawRoomBorders() {
        pen.drawRect(k, l, width, height);
    }
   
    public void drawAgentAt(int x, int y) {
        if(pen != null) {
        	pen.setColor(Color.GREEN);
            pen.fillRect(x, y, width/room.getK(), height/room.getL());
        }
    }
   
    public int[] locates(int i, int j) {
        int[] r = new int[2];
        r[0] = (width * i) / room.getK() + k;
        r[1] = (height * j) / room.getL() + l;
        return r;
    }
    // Method to highlight specific areas (e.g., bar area)
    public void drawBarArea() { // Added - Begin method to draw bar area
        if (pen != null) {
            pen.setColor(Color.ORANGE); // Color for the bar area
            int barX = k + width / 5;
            int barY = l + height - height / 5;
            int barWidth = width / 5;
            int barHeight = height / 10;
            pen.fillRect(barX, barY, barWidth, barHeight);
        }
    } // Added - End method to draw bar area
 
    // Method to draw the dance floor
    public void drawDanceFloor() { // Added - Begin method to draw dance floor
        if (pen != null) {
            pen.setColor(Color.PINK); // Color for the dance floor
            int danceX = k + width / 3;
            int danceY = l + height / 3;
            int danceWidth = width / 3;
            int danceHeight = height / 3;
            pen.fillRect(danceX, danceY, danceWidth, danceHeight);
        }
    }   // Added - End method to draw dance floor
 
    // Method to highlight an avatar's current position
    /*public void highlightAvatarPosition(int x, int y) { // Added - Begin method to highlight avatar position
        if (pen != null) {
            pen.setColor(Color.RED); // Different color to highlight
            pen.drawOval(x - 3, y - 3, 10, 10); // Small circle around avatar
        }
    }*/ // Override drawAgents to highlight positions
    public void drawAgents() { // Modified - Override drawAgents to include highlighting
        ArrayList<Point> positions = room.getCoordinates();
        for (Point position : positions) {
            int x = locates(position.x, position.y)[0];
            int y = locates(position.x, position.y)[1];
            drawAgentAt(x, y);
            //highlightAvatarPosition(x, y); // Added - Highlight each avatar position
        }
    }
 
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
 
    public Room getRoom() {
        return room;
    }
}