package uni.fmi.bachelors;

import java.util.HashMap;

public class Graph {

	public HashMap<String, Node> graph = new HashMap<>();	
	
	public void inserNode(Node n){
		graph.put(n.getName(), n);
	}
	
	public void createPath(Node from, double lenght, Node to){
		if(graph.containsKey(from.getName()) &&
				graph.containsKey(to.getName())){
			Link path = new Link(lenght, to);
			from.insertPath(path);		
		}else{
			System.out.println("Nodes are not part of the graph!");
		}
	}
	
	public void createTwoWayPath(Node from, double lenght, Node to){
		createPath(from, lenght, to);
		createPath(to, lenght, from);
	}

	public void search(String start, String end, ISearch search) {
		if(search.search(start, end)){
			System.out.println("Path Found!");
		}else{
			System.out.println("No path found!");
		}
	}
	
	public Node getNodeByKey(String key) {
		return graph.get(key);
	}
}
