package graph;

import java.util.HashSet;
import java.util.Set;

public class ConnectionChecker<V> {
	private GraphInterface<V> g;
	
	public ConnectionChecker(GraphInterface<V> g)
	{
		this.g=g;
	}
	public boolean check(V v1, V v2) //checks if you can reach v2 from v1
	{
		Set<V> checked = new HashSet<V>(); //setting a set for what vertices we visisted
		return neighcheck(v1,v2,checked); //calling a recursive function that checks if we can reach v2
	}
	private boolean neighcheck(V v1, V v2, Set<V> checked) {
		boolean ret=false; 
		checked.add(v1); //adding vertex to the checked set
		if (v1.equals(v2)) return true; //if we have reached v2 return true
		if (g.neighbours(v1).contains(v2)) return true; //if v2 is a neighbour of v1 we can reach it then return true
		for (V v : g.neighbours(v1)) // for all neighbour of v1 if we did not visit it already, check if neighbour can reach v2
		{
			if (!(checked.contains(v)))
				ret= ret | neighcheck(v,v2,checked); //calling the function for all of the neighbours
		}
		return ret;
	}
	
	
}
