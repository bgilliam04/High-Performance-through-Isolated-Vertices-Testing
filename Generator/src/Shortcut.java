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

	public Shortcut(int n, int groupSize, int y, int z, String filename) {
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

		// TESTING: print each group and which vertices are in each group
		/*for (int i = 0; i < numGroups; i++) {
			System.out.println("Group " + i + ":");
			for (int j = 0; j < groupSize; j++) {
				System.out.print(groups.get(i).get(j).getIndex() + " ");
			}
			System.out.println();
		}*/
	
		// add random shortcuts
		Random rand = new Random();

		// for each group
		for (List<Vertex> group : groups) {
			for (Vertex v : group) {
				if (v.getNeighbor().size() < y) {
					int temp = v.getNeighbor().size();
					for (int j = 0; j < y - temp; j++) {
						int r = rand.nextInt(groupSize);
						// re-random it if there already an edge or the vertices meet its maximum
						// neighbors
						if (r == v.getIndex() || v.getNeighbor().contains(group.get(r))
								|| group.get(r).getNeighbor().size() == y) {
							j--;
						} else {
							v.addNeighbor(group.get(r));
						}
					}
				}
			}
		}

		// add shortcuts between groups
		for (int i = 0; i < numGroups; i++) {
			List<Vertex> otherGroups = new ArrayList<>();
			for (int j = 0; j < numGroups; j++) {
				if (j != i) {
					otherGroups.addAll(groups.get(j));
				}
			}

			// TESTING: print otherGroups
			/*System.out.println("Other groups for group " + i + ":");
			for (Vertex v : otherGroups) {
				System.out.print(v.getIndex() + " ");
			}*/

			for (int j = 0; j < groupSize; j++) {
				Vertex v = groups.get(i).get(j);
				int temp = v.getNeighbor().size();
				for (int k = 0; k < y + z - temp; k++) {
					int r = rand.nextInt(otherGroups.size());
					Vertex v2 = otherGroups.get(r);
					if (v.getNeighbor().contains(v2)
							|| v2.getNeighbor().size() == y + z) {
						k--;
					} else {
						v.addNeighbor(v2);
						otherGroups.remove(r);
					}
				}
			}

		}


		/*for (int i = 0; i < n; i++) {
			if (vertices.get(i).getNeighbor().size() < y) {
				int temp = vertices.get(i).getNeighbor().size();
				for (int j = 0; j < y - temp; j++) {
					int r = rand.nextInt(n);
					// re-random it if there already an edge or the vertices meet its maximum
					// neighbors
					if (r == i || vertices.get(i).getNeighbor().contains(vertices.get(r))
							|| vertices.get(r).getNeighbor().size() == y) {
						j--;
					} else {
						vertices.get(i).addNeighbor(vertices.get(r));
					}
				}
			}
		}*/

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
