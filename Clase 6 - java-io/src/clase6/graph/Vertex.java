package clase6.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vertex implements Serializable {

	private static final long serialVersionUID = 8520500010710631610L;
	public int row;
	public int col;
	private List<Vertex> neighbors; // may change this to Set<Vertex>

	public Vertex(int i, int j){
		this.row = i;
		this.col = j;
		this.neighbors = new ArrayList<Vertex>();
	}

	public boolean addNeighbor(Vertex v){
		this.neighbors.add(v);
		return true;
	}
}
