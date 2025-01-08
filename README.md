
This project is a Java-based simulation of a dynamic party environment, where agents (participants) move around and interact within a defined space consisting of a bar area, a dance floor, and obstacles. The simulation manages the behaviors and states of agents, such as searching for the bar, dancing, avoiding others, or moving randomly, based on their surroundings and predefined rules. The room's layout, including grid dimensions, bar placement, dance floor, and obstacles, is programmatically generated. A graphical user interface (GUI) allows users to adjust parameters like the number of agents, obstacles, and simulation speed. The visualization component renders the simulation in real-time, showing the agents' movements and interactions, providing an engaging and interactive representation of the party environment.



Here's an explanation of each class:
Agent Class
- Represents a participant in the simulation with different states (`SEARCHING_BAR`, `ON_DANCEFLOOR`, etc.).
- Determines the agent's movement based on its current state.
- Can transition between states (e.g., from `SEARCHING_BAR` to `AT_BAR`).
- Includes logic for valid moves and movement direction to avoid obstacles and other agents.
- Handles interactions with the environment, such as being at the bar, on the dancefloor, or near other people.



Bar Class:
- Extends the `Obstacle` class to specifically represent a bar area in the simulation.
- Used to distinguish the bar area visually and functionally from other obstacles.



Direction Class
- Enum that defines possible movement directions for agents (`LEFT`, `RIGHT`, `TOP`, `DOWN`, `STAY`).
- Provides a standardized way to handle movement across the simulation.



`Hurdle Class
- Extends the `Obstacle` class to represent general obstacles within the room.
- Differentiated from the bar but behaves similarly as a physical block in the simulation.



Obstacle Class
- Represents a generic immovable object in the room.
- Stores dimensions (`x`, `y`, `width`, `height`) and checks whether a specific grid cell is within its bounds.



Room Class
- Represents the simulation environment, including the grid, bar, dance floor, agents, and obstacles.
- Manages the layout of the room by placing agents, obstacles, and special areas (bar and dancefloor).
- Checks for interactions, such as whether an agent is near the bar or on the dance floor.
- Handles the navigation logic for agents and updates their positions based on their movements and surroundings.



Simulation CLass
- The main driver class for the simulation.
- Combines all components, including agents, room, GUI, and visualization.
- Controls the lifecycle of the simulation (e.g., starting, stopping, adding agents).
- Manages the speed and number of agents/obstacles in the simulation.



Visualisation Class
- Handles the graphical representation of the simulation.
- Draws the room, including its borders, dance floor, bar area, agents, and obstacles.
- Visualizes different entities with distinct colors (e.g., green for agents, orange for bar, red for obstacles).
- Updates the display dynamically based on the simulation's state.
