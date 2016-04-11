package edu.csula.cs460.graph.strategy;

import edu.csula.cs460.graph.Node;
import edu.csula.cs460.graph.Edge;








import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceArray;




import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sun.org.apache.bcel.internal.generic.LSTORE;

public class AdjacencyList implements Representation {
    private Map<Node, List<Node>> adjacencyList;
    private List<Node> lstnodes;
    protected AdjacencyList(File file) {
        // TODO: read file and parse it into adjacencyList variable
    	String content;
    	try {
			content = Files.toString(file, Charsets.UTF_8);
			 String[] contentArray=content.split("\n");
			 String [] contentArrayNew=Arrays.copyOfRange(contentArray ,1,contentArray.length);
			 String[] parts;
			 String [] tempLine;
			 lstnodes=new ArrayList<Node>();
			 for( int i=0;i<contentArrayNew.length;i++)
		     {
		    	 tempLine=contentArrayNew[i].split(":");
		    	 Node objnode1=new Node(Integer.parseInt(tempLine[0].toString().trim()));
		    	 Node objnode2=new Node(Integer.parseInt(tempLine[1].toString().trim()));
		    	 if(!lstnodes.contains(objnode1))
		    	 lstnodes.add(objnode1);
		    	 if(!lstnodes.contains(objnode2))
		    	 lstnodes.add(objnode2);
		     }
		    
			 
			 for( int i=0;i<contentArrayNew.length;i++)
		     {
				 parts=contentArrayNew[i].split(":");
				 Node objNode=new Node(Integer.parseInt(parts[0].toString().trim()));
				 List<Node> lstnode=new ArrayList<Node>();
				 if (adjacencyList==null )
					{
						
						adjacencyList=new HashMap<Node,List<Node>>();
						int p1=Integer.parseInt(parts[1].toString().trim());
						int p2=Integer.parseInt(parts[2].toString().trim());
						Node objnode1=new Node(p1);
						objnode1.getId();
						lstnode.add(objnode1);
						adjacencyList.put(objNode, lstnode);
					}
					else if (adjacencyList.containsKey(objNode))
					{
						lstnode=adjacencyList.get(objNode);
						lstnode.add(new Node(Integer.parseInt(parts[1].toString().trim())));
						adjacencyList.put(objNode, lstnode);
					}
					else if(!adjacencyList.containsKey(objNode))
					{
						lstnode.add(new Node(Integer.parseInt(parts[1].toString().trim())));
						adjacencyList.put(objNode, lstnode);
						
					}
		
		     }
			
			System.out.println(adjacencyList);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	if(adjacencyList.get(x).contains(x)||adjacencyList.get(x).contains(y))
        return true;
    	else
    		return false;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	List<Node> lstNeighbours=new  ArrayList<Node>();
    	if(lstnodes.contains(x))
    	{
    	if(adjacencyList.containsKey(x))
    	{
    		lstNeighbours=adjacencyList.get(x);
    	}
    	/*for (Map.Entry<Node, List<Node>> entry : adjacencyList.entrySet()) {
    		if(adjacencyList.get(entry.getKey()).contains(x))
    		{
    			lstNeighbours.add(entry.getKey());
    		}
    	}*/
    	}
        return lstNeighbours;
    }

    @Override
    public boolean addNode(Node x) {
    	
    	if(lstnodes.contains(x))
        return false;
    	else{
    		lstnodes.add(x);
    		adjacencyList.put(x,new ArrayList<Node>());
    		return true;
    		}
    	
    }

    @Override
    public boolean removeNode(Node x) {
    	if(!lstnodes.contains(x))
        return false;
    	else{
    		lstnodes.remove(x);
    		if(adjacencyList.containsKey(x))
    		{
    			adjacencyList.remove(x);
    		}
    		for (Map.Entry<Node, List<Node>> entry : adjacencyList.entrySet()) {
        		if(adjacencyList.get(entry.getKey()).contains(x))
        			adjacencyList.get(entry.getKey()).remove(x);
        	}
    		return true;
    	}
    }

    @Override
    public boolean addEdge(Edge x) {
    	if(lstnodes.contains(x.getFrom()) && lstnodes.contains(x.getTo()))
    	{
    		if(adjacencyList.get(x.getFrom()).contains(x.getTo())||adjacencyList.get(x.getTo()).contains(x.getFrom()))
    		{
    			return false;
    		}
    		
    		if(adjacencyList.containsKey(x.getFrom()))
    		{
    			
    			adjacencyList.get(x.getFrom()).add(x.getTo());
    		}
    		else
    		{
    			List<Node> lstToedge=new ArrayList<Node>();
    			lstnodes.add(x.getTo());
    			adjacencyList.put(x.getFrom(), lstToedge);
    		}
    		return true;
    	}
    	else
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	if(adjacencyList.containsKey(x.getFrom()))
    	{
    		if(adjacencyList.get(x.getFrom()).contains(x.getTo()))
    		{
    		adjacencyList.get(x.getFrom()).remove(x.getTo());
    		return true;
    		}
    		else
    			return false;
    		
    	}
    	else
    	{
        return false;
        }
    }
}
