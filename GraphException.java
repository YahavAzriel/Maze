package graph;

public class GraphException extends Exception {
	private String msg;
	public GraphException(String msg)
	{
		this.msg=msg;
	}
	
	public String toString() {
		return "GraphException [msg=" + msg + "]";
	}

}
