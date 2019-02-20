/*
package uni.fmi.bachelors;

import java.util.ArrayList;

public class SearchByCoordinates implements ISearch{

	Graph g;
	int x;
	int y;
	
	public SearchByCoordinates(Graph g) {
		this.g = g;
	}
	
	@Override
	public boolean search(String start, String finish) {
		
		if(!(g.graph.containsKey(start) && g.graph.containsKey(finish)))
			return false;
		
		Node begining = g.graph.get(start);
		Node end = g.graph.get(finish);
		x = end.getX();
		y = end.getY();
		
		ArrayList<Node> queue = new ArrayList<>();
		
		queue.add(begining);
		
		while(!queue.isEmpty()){
			Node temp = queue.get(0);
			queue.remove(0);
			temp.isTested = true;
			
			System.out.println(temp.getName());
			
			if(temp.equals(end))
				return true;
			
			for(Link l: temp.paths){
				
				if(!l.getEndPoint().isTested 
						&& !g.graph.containsKey(l.getEndPoint())){
					addToQueue(l.getEndPoint(), queue);
				}
			}
		}
		
		
		return false;
	}

	private void addToQueue(Node endPoint, ArrayList<Node> queue) {
		
		endPoint.distance = distance;
		
		for(int i = 0; i < queue.size(); i++){
			if(queue.get(i).distance > distance){
				queue.add(i, endPoint);
				return;
			}
		}
		
		queue.add(endPoint);
	}

}
*/
