package edu.csula.cs460.graph.search;

import edu.csula.cs460.graph.Edge;
import edu.csula.cs460.graph.Graph;
import edu.csula.cs460.graph.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
    	List<Edge> path=new ArrayList<Edge>();
    	List<Node> lstnode=new ArrayList<>();
    	lstnode=graph.getNodes();
    	lstnode.add(new Node(8));
		HashMap<Node,Integer> distance=new HashMap<Node,Integer>();
    	HashMap<Node,Node> parents=new HashMap<Node,Node>();
    	distance.put(source, 0);
    	parents.put(source, null);
    	int alt=0;
    	PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>(graph.getNodes().size(), new Comparator<Node>() {
            public int compare(Node Node1,Node Node2) {
                return (distance.get(Node1) >= distance.get(Node2) ) ? (1): (-1);
            }
        });
    	for(Node n:lstnode)
    	{
    		if(n.getId()!=source.getId())
    		{
    			distance.put(n,Integer.MAX_VALUE);
    			parents.put(n, null);
    		}
    		NodeQueue.add(n);
    	}
    	while(!NodeQueue.isEmpty())
    	{
    		Node u=NodeQueue.poll();
    		for(Node child:graph.neighbors(u))
    		{
    			alt=distance.get(u)+graph.distance(u, child);
    			if(alt<distance.get(child))
    			{
    				distance.put(child, alt);
    				parents.put(child, u);
    				NodeQueue.remove(child);
    				NodeQueue.add(child);
    			}
    		}
    		if(u.getId()==dist.getId())
			{
				NodeQueue.clear();
				break;
				
			}
    	}
    	Node target=dist;
    	//Node nPath=parents.get(target);
    	while(target!=null)
    	{   if(parents.get(target)!=null)
    		path.add(new Edge(parents.get(target), target, graph.distance(parents.get(target), target)));
    		target=parents.get(target);   		
    	}
    	
    	Collections.reverse(path);
        return path;
    }
    
}