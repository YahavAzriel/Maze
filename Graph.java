package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V> {


	private Set<V> vertices = new HashSet<V>();
	private Map<V, Set<V>> edges = new HashMap<V,Set<V>>();
	
	public void addVertex(V v) throws GraphException //adds vertex to the graph if does not exist
	{
		if (vertices.contains(v)) throw new GraphException("adding an existing vertex");
		vertices.add(v);
	}
	
	public void addEdge(V v1, V v2) throws GraphException //adds edge between two vertices if does not exist
	{
		if (!(vertices.contains(v1))||!(vertices.contains(v2))) throw new GraphException("Vertex does not exist");
		if (edges.get(v1)==null) { // if v1 has no neighbours, create new set of neighbours
			Set<V> s = new HashSet<V>();
			edges.put(v1, s);
		}
		if (edges.get(v2)==null) { //same for v2
			Set<V> s = new HashSet<V>();
			edges.put(v2, s);
		}
		if (edges.get(v1).contains(v2)) throw new GraphException("Edge exists!");
		edges.get(v1).add(v2); //add v2 to neighbors set of v1
		edges.get(v2).add(v1); //same for v2
	}

	public boolean hasEdge(V v1, V v2)  //returns true of v2 is neighbor of v1 and opposite
	{
		return edges.get(v1).contains(v2) || edges.get(v2).contains(v1);
	}
	
	public boolean connected(V v1, V v2) throws GraphException //check if v2 can be reached from v1
	{
		if (!(vertices.contains(v1))||!(vertices.contains(v2))) throw new GraphException("Vertes does not exist");
		Set<V> marked = new HashSet<V>(); //setting hashset for all vertices thats been visited
		return checkconnect(v1,v2,marked); //recursive function that goes by all neighbors until it reaches v2 or cannot reach
		
	}
	private boolean checkconnect(V v1,V v2, Set<V> marked)
	{
		boolean ret = false;
		marked.add(v1); //add v1 to marked set
		if (v1.equals(v2)) return true; //if v1 is equal to v2 that means we have reached v2
		for (V v : edges.get(v1)) //for all neighbors of v1 check if they can reach v2 recursive
		{
			if (v.equals(v2)) return true;
			if(!(marked.contains(v)))
			ret=ret | checkconnect(v,v2,marked);
		}
		return ret;
	}
	
}


