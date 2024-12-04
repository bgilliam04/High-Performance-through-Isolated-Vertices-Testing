import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shortcut {
	private List<Vertex> vertices = new ArrayList<>();
	private List<List<Vertex>> groups = new ArrayList<>();
	int n;
	int groupSize;

	public Shortcut(int n, int groupSize, int interGroup, int intraGroup, String filename) {
		this.n = n;

		// generate vertices
		for (int i = 0; i < n; i++) {
			vertices.add(new Vertex(i));
		}

		// adds first groupSize vertices to the first group, then second groupSize to second group and so on until all the vertices are in a group
		for (int i = 0; i < n; i += groupSize) {
			List<Vertex> group = new ArrayList<>();
			for (int j = i; j < i + groupSize; j++) {
				group.add(vertices.get(j));
			}
			groups.add(group);
		}


		int numGroups = n / groupSize;
	
		// add random shortcuts
		Random rand = new Random();

		// for each group
		for (List<Vertex> group : groups) {
			for (Vertex v : group) {
				List<Vertex> otherVertices = new ArrayList<>();
				for (Vertex v1 : vertices) {
					if (group.contains(v1)) {
						if (v1.getNeighbor().size() < interGroup && !(v1.getNeighbor().contains(v))) {
							otherVertices.add(v1);
						}
					}
					
				}

				if (v.getNeighbor().size() < interGroup + 1) {
					int temp = v.getNeighbor().size();
					for (int j = 0; j < interGroup - temp; j++) {
						int r = rand.nextInt(otherVertices.size());
						v.addNeighbor(otherVertices.get(r));
					}
				}
			}
		}

		// add shortcuts between groups
		for (int i = 0; i < numGroups; i++) {
			for (int j = 0; j < groupSize; j++) {
				List<Vertex> otherGroups = new ArrayList<>();
				for (int l = 0; l < numGroups; l++) {
					if (l != i) {
						for (int k = 0; k < groupSize; k++) {
							Vertex v1 = groups.get(l).get(k);
							if (v1.getNeighbor().size() < interGroup + intraGroup && !(v1.getNeighbor().contains(groups.get(i).get(j)))) {
								otherGroups.add(groups.get(l).get(k));
							}
						}
						
					}
				}
				
				Vertex v = groups.get(i).get(j);
				int temp = v.getNeighbor().size();
				if (temp < interGroup + intraGroup + 1) {
					for (int k = 0; k < interGroup + intraGroup - temp; k++) {
						try {
							int r = rand.nextInt(otherGroups.size());
							Vertex v2 = otherGroups.get(r);
							v.addNeighbor(v2);
							// swap this element with the last element in the list, then remove the last to improve time complexity
							Vertex tempV = otherGroups.get(otherGroups.size() - 1);
							otherGroups.set(otherGroups.size() - 1, otherGroups.get(r));
							otherGroups.set(r, tempV);
							otherGroups.remove(otherGroups.size() - 1);
						}
						catch (IllegalArgumentException e) {
							System.out.println("No more vertices to add");
							e.printStackTrace();
						}

					}
				}

			}

			try {
				for (List<Vertex> group : groups) {
					for (Vertex v : group) {
						if (v.getNeighbor().size() == 0) {
							throw new IllegalArgumentException();
						}
					}
				}
			}

			catch (IllegalArgumentException e) {
				System.out.println("No more vertices to add");
				e.printStackTrace();
			}

		}

		// create a file for this graph
		try {
			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(toString());
			myWriter.close();
			System.out.println("Successfully wrote to the file " + filename);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public String toString() {
		String result = "" + n + "\n";
		for (Vertex v : vertices) {
			result += v.toString();
		}
		return result;
	}
}
