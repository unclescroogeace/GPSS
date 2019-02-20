package uni.fmi.bachelors;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private String name;
	private double weight;
	private int x;
	private int y;
	public boolean isTested;
	private double distance;
	
	public ArrayList<Link> paths = new ArrayList<>();
	
	public void insertPath(Link path){
		paths.add(path);
	}
	
	public boolean isPathExist(Node node) {
		for(Link l : paths) {
			if(l.getEndPoint().getName() == node.getName()) {
				return true;
			}
		}
		return false;
	}
	
	public Node(String name) {
		this.name = name;
	}
	
	public Node(String name, double distance) {
		this.name = name;
		this.distance = distance;
	}
	
	public Node(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public Node(String name, int x, int y, double weight) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	@Override
	public int compareTo(Node n) {
		if (this.distance < n.distance)
			return -1;
		else if (this.distance > n.distance)
			return 1;
		else
			return this.getName().compareTo(n.getName());
	}
}
