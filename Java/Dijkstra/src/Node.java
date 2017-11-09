import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {
	private Map<Node, Integer> neighbors = new HashMap<>();

	private int id;

	Node(int id) {
		this.id = id;
	}

	void addNeighbor(Node destination, int cost) {
		neighbors.put(destination, cost);
		destination.addNeighbor(this, cost);
	}

	Set<Node> getNeighbors() {
		return neighbors.keySet();
	}

	int getCostToNeighbor(Node destination) {
		return neighbors.get(destination);
	}

	@Override
	public String toString() {
		return "Node: " + id;
	}
}
