import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Dijkstra {
	public static void main(String[] args) {
		Node[] nodes = {new Node(0), new Node(1), new Node(2), new Node(3), new Node(4), new Node(5), new Node(6), new Node(7)};

		nodes[0].addNeighbor(nodes[1], 3);
		nodes[0].addNeighbor(nodes[2], 4);
		nodes[1].addNeighbor(nodes[3], 5);
		nodes[1].addNeighbor(nodes[4], 6);
		nodes[2].addNeighbor(nodes[3], 2);
		nodes[2].addNeighbor(nodes[6], 7);
		nodes[3].addNeighbor(nodes[4], 1);
		nodes[3].addNeighbor(nodes[7], 22);
		nodes[4].addNeighbor(nodes[5], 6);
		nodes[5].addNeighbor(nodes[7], 8);
		nodes[6].addNeighbor(nodes[7], 10);

		long startTime = System.nanoTime();

		System.out.println(findShortestDistances(nodes[0]));

		System.out.println((double)(System.nanoTime() - startTime) / 1000000000);
	}

	static Map<Node, Integer> findShortestDistances(Node origin) {
		Map<Node, Integer> shortestPaths = new HashMap<>();
		shortestPaths.put(origin, 0);

		Set<Node> calculated = new HashSet<>();

		Node currentNode = origin;

		while(true) {
			calculated.add(currentNode);

			Set<Node> needToVisit = new HashSet<>();

			for(Node neighbor : currentNode.getNeighbors()) {
				if(!calculated.contains(neighbor)) {
					needToVisit.add(neighbor);
				}
			}

			for(Node neighbor : needToVisit) {
				if(!shortestPaths.containsKey(neighbor)
						|| shortestPaths.get(currentNode) + currentNode.getCostToNeighbor(neighbor) < shortestPaths.get(neighbor)) {
					shortestPaths.put(neighbor, shortestPaths.get(currentNode) + currentNode.getCostToNeighbor(neighbor));
				}
			}
			
			Set<Node> possibleNextNodes = shortestPaths.keySet().stream()
					.filter(x -> !calculated.contains(x))
					.collect(Collectors.toSet());
			
			if(possibleNextNodes.isEmpty()) {
				break;
			} else {
				
				int shortestDistance = Integer.MAX_VALUE;
				Node nextNode = null;
				
				for(Node node : possibleNextNodes) {
					if(shortestPaths.get(node) < shortestDistance) {
						shortestDistance = shortestPaths.get(node);
						nextNode = node;
					}
				}
				
				currentNode = nextNode;
			}
		}

		return shortestPaths;
	}
}
