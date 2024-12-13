# High Performance Through Isolated Vertices
This project is used to simulated structured random networks with random intergroup and intragroup connections. It then generates the diameter and average shortest path for a various number of runs.

## Purpose

## Description
The code is divided into 2 folders: Generator and Diameter. The Generator folder is used to create the graphs and contains 3 files. The `Vertex.java` class defines what properies vertices should have including adding neighbors and removing neighbors. The `Shortcut.java` class is used to generate the intragroup and intergroup connections. The code begins by defining `n` number of isolated Vertices with no connections to each other. Then the code creates groups by taking the the first `groupSize` number of Vertices and putting them into a group represented by an ArrayList until every Vertice is in a group. An ArrayList `groups` stores all the groups. 

Intra group connection (or local links) are made by iterating through each group and forming connections with any node in the group that has less than `intraGroup` number of connections and is not the same node that is currently selected.

Inter group connections (or global links) are made by iterating through each group (not including the current group) and adding all available nodes to an ArrayList `otherGroups` 

## Participants

Bria Gilliam - @bgilliam04
Dibyasha Sharma - @dibyashaS
