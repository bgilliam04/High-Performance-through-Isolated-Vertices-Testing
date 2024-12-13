# High Performance Through Isolated Vertices
This project is used to simulated structured random networks with random intergroup and intragroup connections. It then generates the diameter and average shortest path for a various number of runs. The term node and vertex are used interchangeably. 

## Purpose

## Description
The code is divided into 2 folders: Generator and Diameter. The Generator folder is used to create the graphs and contains 3 files. The `Vertex.java` class defines what properies vertices should have including adding neighbors and removing neighbors. 

The `Shortcut.java` class is used to generate the intragroup and intergroup connections. The code begins by defining `n` number of isolated Vertices with no connections to each other. Then the code creates groups by taking the the first `groupSize` number of Vertices and putting them into a group represented by an ArrayList until every Vertice is in a group. An ArrayList `groups` stores all the groups. 

Intra group connection (or local links) are made by iterating through each group and forming connections with any node in the group that has less than `intraGroup` number of connections and is not the same node that is currently selected. The available nodes are stored in an ArrayList `otherVertices` and the Random class is used to make a random connection until the current node is full. 

Inter group connections (or global links) are made by iterating through each group (not including the current group) and adding all available nodes to an ArrayList `otherGroups.` Each vertice generates a `otherGroup` list that includes all the available nodes to connect to. Then the current group is iterated through and random connections are made to each node until it has less than `intraGroup + interGroup` number of connections. The Random class is used for this process as well. The last part of the `Shortcut.java` class creates a file and writes the graphs created to that file.

In the Diameter folder, there is a `Diameter.java` and `Edge.java` class that were not written by us, so no documentation is provided for these. However they will calculate the diameter and average shortest path for the provided files.

## How To Run the Code
1. First go to the `Main.java` class in the `Generator` folder. Then enter the `numRuns` 

## Participants

Bria Gilliam - [@bgilliam04](https://github.com/bgilliam04)

Dibyasha Sharma - [@dibyashaS](https://github.com/dibyashaS)
