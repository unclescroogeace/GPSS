package uni.fmi.bachelors;

import java.util.ArrayList;

public class BreathSearch implements ISearch{

	Graph g = new Graph();
	
	public BreathSearch(Graph g) {
		this.g = g;
	}
	
	@Override
	public boolean search(String start, String finish) {
		if(!g.graph.containsKey(start)
				|| !g.graph.containsKey(finish))
			return false;
		
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(g.graph.get(start));
		
		while(queue.size() > 0){
			Node current = queue.get(0);
			System.out.println(current.getName() + "-");
			
			if(current.getName().equalsIgnoreCase(finish))
				return true;
			
			queue.remove(0);
			current.isTested = true;
			
			for(int i = 0; i < current.paths.size(); i++){
				Link link = current.paths.get(i);
				
				if(!link.getEndPoint().isTested 
						&& !queue.contains(link.getEndPoint())){
					queue.add(link.getEndPoint());
				}
			}
			
		}
		
		return false;
	}

}
