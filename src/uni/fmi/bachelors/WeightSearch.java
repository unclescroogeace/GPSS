package uni.fmi.bachelors;

import java.util.ArrayList;

public class WeightSearch implements ISearch{

	Graph g;
	
	public WeightSearch(Graph g) {
		this.g = g;
	}
	
	@Override
	public boolean search(String start, String finish) {
		
		if(!g.graph.containsKey(start)
				|| !g.graph.containsKey(finish))
			return false;
		
		Node begining = g.graph.get(start);
		
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(begining);
		
		while(queue.size() > 0){
			Node node = queue.get(0);
			node.isTested = true;
			queue.remove(0);
			
			System.out.println(node.getName());
			
			if(node.getName().equals(finish))
				return true;
			
			for(int i = 0; i < node.paths.size(); i++){
				Link l = node.paths.get(i);	
				
				if(queue.contains(l.getEndPoint()) 
						|| l.getEndPoint().isTested){
					continue;
				}
				
				addNodeToQueue(l.getEndPoint(), queue);
			}
			
			
		}		
		
		return false;
	}

	private void addNodeToQueue(Node endPoint, ArrayList<Node> queue) {
		for(int i = 0; i < queue.size(); i++){
			if(endPoint.getWeight() < queue.get(i).getWeight()){
				queue.add(i, endPoint);
				return;
			}
		}		
		queue.add(endPoint);
	}

}
