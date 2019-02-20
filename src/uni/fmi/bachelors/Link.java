package uni.fmi.bachelors;

public class Link {
	
	public Link(double lenght, Node to) {
		this.length = lenght;
		endPoint = to;
	}
	
	private double length;
	private LinkType type;
	private Node endPoint;
	
	public void setLenght(double lenght) {
		this.length = lenght;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public LinkType getType() {
		return type;
	}
	public void setType(LinkType type) {
		this.type = type;
	}
	public Node getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Node endPoint) {
		this.endPoint = endPoint;
	}
}
