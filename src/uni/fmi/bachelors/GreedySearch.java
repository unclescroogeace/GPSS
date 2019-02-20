package uni.fmi.bachelors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GreedySearch {
	private Graph graph;
	private int x;
	private int y;
	
	public GreedySearch(Graph graph) {
		this.graph = graph;
	}
	
	public ArrayList<String> search(String start, String finish){
		ArrayList<String> path = new ArrayList<>();
		Node begining = graph.graph.get(start);
		Node end = graph.graph.get(finish);
		x = end.getX();
		y = end.getY();
		
		ArrayList<Node> queue = new ArrayList<>();
		
		queue.add(begining);
		
		while(!queue.isEmpty()){
			Node temp = queue.get(0);
			queue.remove(0);
			temp.isTested = true;
			
			path.add(temp.getName());
			if(temp.equals(end)) {
				return path;
			}
			
			
			addToQueue(temp, queue);
		}
		return new ArrayList<String>();
		
	}
	private void addToQueue(Node node, ArrayList<Node> queue) {
		double minLength = 0;
		ArrayList<Link> links = new ArrayList<>();
		int count = 0;
		
		for(Link l: node.paths) {
			if(!l.getEndPoint().isTested) {
				links.add(l);
				count += 1;
			}
		}
		
		if(count < 2) {
			if(count > 0) {
				queue.add(0, links.get(0).getEndPoint());
				return;
			}
			else {
				return;
			}
		}
		
		Collections.sort(links, new Comparator<Link>() {
	        @Override
	        public int compare(Link link1, Link link2)
	        {
	        	if(link1.getLength() < link2.getLength()) {
	        		return	-1;
	        	}
	        	if(link1.getLength() > link2.getLength()) {
	        		return	1;
	        	}
	        	return 0;
	        }
	    });
		
		for(Link l: links) {
			l.getEndPoint().setDistance(Double.MAX_VALUE);
		}
		
		minLength = links.get(0).getLength();
		double distance;
		for(Link l: links) {
			if(l.getLength() == minLength) {
				distance = Math.sqrt(
						Math.pow(l.getEndPoint().getX() - x, 2) + 
						Math.pow(l.getEndPoint().getY() - y, 2)
						);
				l.getEndPoint().setDistance(distance);;
			}
		}
		
		Collections.sort(links, new Comparator<Link>() {
	        @Override
	        public int compare(Link link1, Link link2)
	        {
	        	if(link1.getEndPoint().getDistance() < link2.getEndPoint().getDistance()) {
	        		return	-1;
	        	}
	        	if(link1.getEndPoint().getDistance() > link2.getEndPoint().getDistance()) {
	        		return	1;
	        	}
	        	return 0;
	        }
	    });
		
		queue.add(links.get(0).getEndPoint());
	}
}
