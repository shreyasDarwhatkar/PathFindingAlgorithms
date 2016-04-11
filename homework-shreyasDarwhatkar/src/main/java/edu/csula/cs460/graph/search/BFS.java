package edu.csula.cs460.graph.search;

import edu.csula.cs460.graph.Edge;
import edu.csula.cs460.graph.Graph;
import edu.csula.cs460.graph.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS implements SearchStrategy {
	List<Node> lstvisited=new ArrayList<Node>();
    public List<Edge> search(Graph graph, Node source, Node dist) {
    	Map<Node, Node> mapParent=new HashMap<Node, Node>();
    	List<Edge> lstEdges=new ArrayList<Edge>();
    	List<Node> lstpathNodes=new ArrayList<Node>();
    	Queue<Node> q=new LinkedList<Node>();
    	q.add(source);
    	lstvisited.add(source);
    	lstpathNodes.add(source); 
    	while(!q.isEmpty())
    	{
    		Node n=(Node)q.remove();
    		Node child=null;
    		
    		while((child=getUnvisitedChildNode(graph,n))!=null){
    			lstvisited.add(child);
    			lstpathNodes.add(child);
    			q.add(child);
    			mapParent.put(child,n);
    			if(dist.equals(child)){
    				q.clear();
    				break;
    			}
    		}
    		
    	}
    	Collections.reverse(lstpathNodes);
    	Node connectingNode=null;
    	for (Node p : lstpathNodes) {
			Edge e=new Edge(mapParent.get(p), p,graph.distance(mapParent.get(p), p));
			if((connectingNode==null || connectingNode==e.getTo()) && e.getFrom()!=null)
			{
			lstEdges.add(e);
			connectingNode=e.getFrom();
			}
		}
    	
    	Collections.reverse(lstEdges);
    	
        return lstEdges;
    }
    
	private Node getUnvisitedChildNode(Graph g,Node n)
	{
		
		List<Node> LstChildnodes=g.neighbors(n);
		
		if (LstChildnodes!=null) {
			for (Node Objnode : LstChildnodes) {
				if (!lstvisited.contains(Objnode)) {
					return Objnode;
				}

			}
		}
			return null;
	}
	
    
}