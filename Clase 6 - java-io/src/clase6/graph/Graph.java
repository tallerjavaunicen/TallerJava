package clase6.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph implements Serializable {

	private static final long serialVersionUID = -2632163054149021990L;
	private List<Vertex> vertices;

	private Map<Integer, Set<Vertex>> map;

	public Graph(int rowMax, int colMax){
		map = new HashMap<Integer, Set<Vertex>>();

		this.vertices = new ArrayList<Vertex>();
	}

	public void connectVertices(Vertex u, Vertex v){
		u.addNeighbor(v);
		v.addNeighbor(u);
	}
}