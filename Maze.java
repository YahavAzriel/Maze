package graph;

import java.util.Collection;
import java.util.HashSet;

public class Maze implements GraphInterface<Place> {
	private int size,startx,starty,endx,endy;
	private char[][] maze;
	public Maze(int size, int startx, int starty, int endx, int endy) //constructs a maze with given inputs
	{
		if (startx>=size||starty>=size||endx>=size||endy>=size||startx<0||starty<0||endx<0||endy<0) throw new IllegalArgumentException();
		this.size=size;
		this.startx=startx;
		this.starty=starty;
		this.endx=endx;
		this.endy=endy;
		maze = new char[size][size]; //creating char matrix sizexsize
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				maze[i][j]='.';
		maze[startx][starty]='S';
		maze[endx][endy]='E';
	}
	public boolean addWall(int x, int y) //adds wall if point does not contain a wall or start/ending point
	{
		if (x>=size||y>=size||x<0||y<0) throw new IllegalArgumentException();
		if (maze[x][y]=='S'||maze[x][y]=='E'||maze[x][y]=='@') return false;
		maze[x][y]='@';
		return true;
	}
	public String toString() //builds a string for maze matrix
	{
		StringBuilder s = new StringBuilder();
		for (int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
				s.append(maze[i][j]);
			s.append("\n");
		}
		return s.toString();
	}

	public boolean isSolvable() throws GraphException //checking if ending point can be reached from starting point
	{
		Graph<Place> g = new Graph<Place>(); //initializes a graph for the maze
		Place p[][] = new Place[size][size]; //initializing place matrix
		try 
		{
			for (int i = 0 ;i<size;i++) //nested loop initalizes vertices
				for (int j=0;j<size;j++)
				{
					p[i][j]=new Place(i,j,size); //creating place for vertex
					if (maze[i][j]!='@') g.addVertex(p[i][j]); //if point is not a wall add vertex to the graph
				}
			for (int i = 0 ; i < size ; i++) //initializing edges
				for (int j =  0; j<size;j++)
				{
					if (maze[i][j]!='@') //if its not a wall
					{
						if (i+1<size) 
							if (maze[i+1][j]!='@') g.addEdge(p[i][j], p[i+1][j]); //add edge between vertices downwards
						if (j+1<size)
							if (maze[i][j+1]!='@') g.addEdge(p[i][j], p[i][j+1]); //add edge beween vertices rightwards
					}
				}
		}
		catch(GraphException e) { //if exception was caught print the message and end
			e.printStackTrace();
			return false;
		}
		return g.connected(p[startx][starty], p[endx][endy]); //using graph connected function to check if ending vertex is reachable from starting vertex
		
	}
	public Collection<Place> neighbours(Place p) //returns a set of all vertices in a graph
	{
		HashSet<Place> h = new HashSet<Place>();
		if (p.getX()+1<size) 
			if (maze[p.getX()+1][p.getY()]!='@') h.add(new Place(p.getX()+1,p.getY(),size));
		if (p.getY()+1<size)
			if (maze[p.getX()][p.getY()+1]!='@') h.add(new Place(p.getX(),p.getY()+1,size));
		if (p.getX()-1>=0)
			if (maze[p.getX()-1][p.getY()]!='@') h.add(new Place(p.getX()-1,p.getY(),size));
		if (p.getY()-1>=0)
			if (maze[p.getX()][p.getY()-1]!='@') h.add(new Place(p.getX(),p.getY()-1,size));
		return h;
	}

}
