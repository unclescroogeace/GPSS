package uni.fmi.bachelors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
	
private Graph graph;
public static double totalPathDistance;
	
	public Dijkstra(Graph graph) {
		this.graph = graph;
	}
	
	public ArrayList<String> getShortestPath(String start, String finish) {
		Map<String, Double> distances = new HashMap<String, Double>();
		HashMap<String, Node> previous = new HashMap<String, Node>();
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		
		
		for(String node : graph.graph.keySet()) {
			if (node == start) {
				distances.put(node, 0.0);
				nodes.add(new Node(node, 0.0));
			} else {
				distances.put(node, 99999.0);
				nodes.add(new Node(node, 99999.0));
			}
			previous.put(node, null);
		}
		
		while (!nodes.isEmpty()) {
			Node smallest = nodes.poll();
			if (smallest.getName() == finish) {
				System.out.println("Smallest distance" + smallest.getDistance());
				totalPathDistance = smallest.getDistance();
				final ArrayList<String> path = new ArrayList<String>();
				while (previous.get(smallest.getName()) != null) {
					path.add(smallest.getName());
					smallest = previous.get(smallest.getName());
				}
				return path;
			}

			if (distances.get(smallest.getName()) == 99999.0) {
				break;
			}
			
			for (Link neighbor : graph.graph.get(smallest.getName()).paths) {
				Double alt = distances.get(smallest.getName()) + neighbor.getLength();
				System.out.println("Wroking with " + smallest.getName() + " and " + neighbor.getEndPoint().getName()
				+ distances.get(smallest.getName()) + " " +
						neighbor.getLength());
				System.out.println("Alt : " + Double.toString(alt));
				if (alt < distances.get(neighbor.getEndPoint().getName())) {
					distances.put(neighbor.getEndPoint().getName(), alt);
					previous.put(neighbor.getEndPoint().getName(), smallest);
					
					System.out.println(distances.get(neighbor.getEndPoint().getName()));
					
					forloop:
					for(Node n : nodes) {
						System.out.println(n.getName() + " compare to " + neighbor.getEndPoint().getName());
						if (n.getName() == neighbor.getEndPoint().getName()) {
							System.out.println("Compare success!");
							nodes.remove(n);
							n.setDistance(alt);
							nodes.add(n);
							break forloop;
						}
						System.out.println("Compare failed!");
					}
				}
			}
			
		}
		
		return new ArrayList<String>(distances.keySet());
	}
}
