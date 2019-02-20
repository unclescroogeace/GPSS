package uni.fmi.bachelors;

public class MainClass {

	public static void main(String[] args) {
		
		Graph graph =  new Graph();
		
		Node a = new Node("A", 7, 1, 1);
		Node b = new Node("B", 6, 1, 12);
		Node c = new Node("C", 6, 2, 4);
		Node d = new Node("D", 5, 1, 22);
		Node e = new Node("E", 5, 2, 18);
		Node f = new Node("F", 5, 3, 3);
		Node g = new Node("G", 4, 1, 28);
		Node h = new Node("H", 4, 4, 5);
		Node i = new Node("I", 4, 5, 1);
		Node j = new Node("J", 5, 4, 30);
		Node q = new Node("Q", 5, 4, 30);
		
		graph.graph.put(a.getName(), a);
		graph.graph.put(b.getName(), b);
		graph.graph.put(c.getName(), c);
		graph.graph.put(d.getName(), d);
		graph.graph.put(e.getName(), e);
		graph.graph.put(f.getName(), f);
		graph.graph.put(g.getName(), g);
		graph.graph.put(h.getName(), h);
		graph.graph.put(j.getName(), j);
		graph.graph.put(i.getName(), i);
		graph.graph.put(q.getName(), q);

		graph.createTwoWayPath(a, 3, b);
		graph.createTwoWayPath(a, 12, c);
		graph.createTwoWayPath(b, 2, d);
		graph.createTwoWayPath(b, 5, e);
		graph.createTwoWayPath(c, 25, f);
		graph.createTwoWayPath(d, 3, g);
		graph.createTwoWayPath(e, 2, j);
		graph.createTwoWayPath(f, 29, h);
		graph.createTwoWayPath(f, 38, i);
		graph.createTwoWayPath(g, 7, j);
		graph.createTwoWayPath(h, 19, j);
		graph.createTwoWayPath(i, 9, j);
		
		graph.search("F", "E", new BreathSearch(graph));
		
		
		//Dijkstra search = new Dijkstra(graph);
		//String start = "F";
		//String finish = "E";
		/*ArrayList<String> path = search.getShortestPath(start, finish);
		if(!path.isEmpty()) {
			Collections.reverse(path);
			path.add(0, start);
			
			for(String s: path) {
				System.out.println(s);
			}
		}
		else {
			System.out.println("The path between " + start + " and " + finish + " do not exist!");
		}
		*/
	}

}
