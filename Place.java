package graph;

public class Place {

	private int x;
	private int y;
	public Place(int x, int y, int bound) //constructs a place with given inputs
	{
		if (x<0 || x>bound-1 || y<0 || y>bound-1) throw new IllegalArgumentException();
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Place)) return false; 
		return hashCode()==other.hashCode();
	}
	@Override
	public int hashCode() { //generating a hashcode for a point
	    int result = x;
	    result = 31 * result * result + y;
	    return result;
	}
}
