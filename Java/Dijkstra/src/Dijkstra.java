import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Dijkstra {
	public static void main(String[] args) {
		Node[] nodes = {new Node(0), new Node(1), new Node(2), new Node(3)};
		
		nodes[0].addNeighbor(nodes[1], 2);
		nodes[0].addNeighbor(nodes[2], 3);
		nodes[0].addNeighbor(nodes[3], 8);
		nodes[1].addNeighbor(nodes[0], 3);
		nodes[1].addNeighbor(nodes[2], 4);
		nodes[1].addNeighbor(nodes[3], 9);
		nodes[2].addNeighbor(nodes[0], 5);
		nodes[2].addNeighbor(nodes[1], 3);
		nodes[2].addNeighbor(nodes[3], 2);
		nodes[3].addNeighbor(nodes[0], 2);
		nodes[3].addNeighbor(nodes[1], 1);
		nodes[3].addNeighbor(nodes[2], 3);
		
		System.out.println(findShortestPaths(nodes[0]));
	}
	
	static Map<Node, Integer> findShortestPaths(Node origin) {
		Map<Node, Integer> shortestPaths = new HashMap<>();
		shortestPaths.put(origin, 0);
		
		Stack<Node> shouldCalculate = new Stack<>();
		Set<Node> calculated = new HashSet<>();
		
		Node currentNode = origin;
		
		while(true) {
			calculated.add(currentNode);
			
			Set<Node> needToVisit = new HashSet<>();
			
			for(Node neighbor : currentNode.getNeighbors()) {
				if(!calculated.contains(neighbor)) {
					needToVisit.add(neighbor);
					shouldCalculate.add(neighbor);
				}
			}
			
			for(Node neighbor : needToVisit) {
				if(!shortestPaths.containsKey(neighbor) || shortestPaths.get(currentNode) + currentNode.getCostToNeighbor(neighbor) < shortestPaths.get(neighbor)) {
					shortestPaths.put(neighbor, shortestPaths.get(currentNode) + currentNode.getCostToNeighbor(neighbor));
				}
			}
			
			if(shouldCalculate.isEmpty()) {
				break;
			} else {
				currentNode = shouldCalculate.pop();
			}
		}
		
		return shortestPaths;
	}
}
