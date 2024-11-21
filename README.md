ButtonClickListener: An interface that defines methods to handle button click events (Start, Stop, Resume) in the GUI.

GUI: Manages the graphical user interface, allowing users to input parameters (e.g., number of agents, field size) and interact with the simulation through buttons.

module-info: Specifies the module definition for the project, declaring dependencies such as java.desktop.

Agent: Represents an individual entity in the simulation, capable of moving in random directions within the room.

Direction: An enumeration defining possible movement directions for agents (LEFT, RIGHT, TOP, DOWN).

Room: Models the simulation environment, managing agent positions and ensuring valid movements within the defined grid.

Simulation: Orchestrates the overall simulation, handling GUI integration, agent addition, and movement logic.

Visualisation: Handles graphical rendering of the simulation, including agents, room borders, and specialized areas like the bar and dance floor.
