package challenge_eight;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Graph {
    private Map<String, List<String>> adjVertices;
    
    public Graph() {
    	this.adjVertices = new HashMap<>();
    }

    // standard constructor, getters, setters
    public void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    public void removeVertex(String label) {
        if(this.adjVertices.get(label) != null) {
            adjVertices.values().stream().forEach(e -> e.remove(label));
            adjVertices.remove(label);
        }
    }
    
    public void addEdge(String label1, String label2) {
        if(!adjVertices.get(label1).contains(label2)) {
        	adjVertices.get(label1).add(label2);
        }
        if(!adjVertices.get(label2).contains(label1)) {
            adjVertices.get(label2).add(label1);
        }
    }
    
    public void removeEdge(String label1, String label2) {
        List<String> eV1 = adjVertices.get(label1);
        List<String> eV2 = adjVertices.get(label2);
        if (eV1 != null)
            eV1.remove(label2);
        if (eV2 != null)
            eV2.remove(label1);
    }
    
    public boolean hasVertexCut() {
    	
    	Iterator<String> adjVerticesKeys = this.adjVertices.keySet().iterator();
 
    	Deque<String> vertexSearch = new ArrayDeque<>();
    	
    	vertexSearch.push(adjVerticesKeys.next());
    	
    	while(!vertexSearch.isEmpty()) {
    		String v = vertexSearch.pop();
    		for(int i=0; i<this.adjVertices.get(v).size(); i++) {
    			String connection = this.adjVertices.get(v).get(i);
    			if(vertexSearch.contains(connection)) {
    				continue;
    			}
    			vertexSearch.push(connection);
    		}
    		this.removeVertex(v);
    	}
    	
    	return !this.adjVertices.isEmpty();	
    }
    
    public Map<String, List<String>> getAdjVertices() {
    	return this.adjVertices;
    }
}