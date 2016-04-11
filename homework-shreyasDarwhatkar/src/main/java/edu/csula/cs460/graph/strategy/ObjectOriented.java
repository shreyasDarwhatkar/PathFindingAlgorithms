package edu.csula.cs460.graph.strategy;

import edu.csula.cs460.graph.Edge;
import edu.csula.cs460.graph.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReferenceArray;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;


public class ObjectOriented implements Representation {
    private List<Node> nodes;
    private List<Edge> edges;

    protected ObjectOriented(File file) {
        // TODO: parse file content and add it to nodes
    	String content;
    	@SuppressWarnings("unused")
		String def;
			try {
				content = Files.toString(file, Charsets.UTF_8);
				 String[] contentArray=content.split("\n");
				 String [] contentArrayNew=Arrays.copyOfRange(contentArray ,1,contentArray.length);
				 
				 @SuppressWarnings("unused")
				char[] abc=content.toCharArray(); 
				 
				 Arrays.stream(contentArrayNew).
				 forEach(line->
				 { AtomicReferenceArray<String> parts =
                        new AtomicReferenceArray<>(line.split(":"));
				 Node objNode=new Node(Integer.parseInt(parts.get(0).toString().trim()));
				 Node objNode1=new Node(Integer.parseInt(parts.get(1).toString().trim()));
				 Edge objEdge=new Edge(objNode,objNode1,Integer.parseInt(parts.get(2).toString().trim()));
				 
				 if(nodes==null && edges==null)
				 {
					 nodes=new ArrayList<Node>();
					 edges=new ArrayList<Edge>();
					 edges.add(objEdge);
					 nodes.add(objNode);
					 nodes.add(objNode1);
					 
				 }
				 else
				 {
					 if(!nodes.contains(objNode))
					 {
						 nodes.add(objNode);
					 }
					 if(!nodes.contains(objNode1))
					 {
						 nodes.add(objNode1);
					 }
					 if(!edges.contains(objEdge))
					 {
						 edges.add(objEdge);
					 }
				 }
				 });
				 
				 System.out.println(nodes);
				 System.out.println(edges);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
    }

    protected ObjectOriented() {
		// TODO Auto-generated constructor stub
    	 nodes = Lists.newArrayList();
         edges = Lists.newArrayList();
	}

	@Override
    public boolean adjacent(Node x, Node y) {
    	boolean aFlag=false;
    	if(nodes.contains(x) && nodes.contains(y))
    	{
    		for(Edge e:edges)
    		{
    			if((e.getFrom().getId()==x.getId() && e.getTo().getId()==y.getId())||(e.getTo().getId()==x.getId() && e.getFrom().getId()==y.getId()))
    			{
    				aFlag=true;
    			}
    		}
    	}
        return aFlag;
    }

    @Override
    public List<Node> neighbors(Node x) {
    	List<Node> lstNeighbors=new ArrayList<Node>();
    	if (nodes.contains(x))
    	{
    	for(Edge e:edges)
    	{
    		if(e.getFrom().getId()==x.getId())
    			lstNeighbors.add(e.getTo());
    		//else if(e.getTo().getId()==x.getId())
    			//lstNeighbors.add(e.getFrom());
    		
    	}
    	return lstNeighbors;
    	}
    	else
        return null;
    }

    @Override
    public boolean addNode(Node x) {
    	if(!nodes.contains(x))
    	{
    		nodes.add(x);
    		return true;
    	}
    	else
        return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	if(nodes.contains(x))
    	{
    		nodes.remove(x);
    		for(Edge e:edges)
    		{
    			if(e.getFrom()==x||e.getTo()==x)
    			{
    				edges.remove(e);
    			}
    		}
    		return true;
    	}
    	else {
    		return false;	
		}
        
    }

    @Override
    public boolean addEdge(Edge x) {
    	if(!edges.contains(x))
    	{
    		edges.add(x);
    		return true;
    	}
    	else
        return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	for(Edge e: edges)
    	{
    		if(e.getFrom().getId()==x.getFrom().getId() && e.getTo().getId()==x.getTo().getId())
    		{
    			edges.remove(e);
    			return true;
    		}
    		
    	}
    	
        return false;
        
    }
    @Override
    public int distance(Node from, Node to) {
        // TODO: implement a method to get edge value between fromNode to toNode
    	int weight=0;
    	if(nodes.contains(from)&&nodes.contains(to))
    	{
    		for(Edge e:edges)
    		{
    			if(e.getFrom().getId()==from.getId() && e.getTo().getId()==to.getId()){
    				weight=e.getValue();
    			}
    		}
    		return weight;
    	}
        return 0;
    }

	@Override
	public List<Node> getNodes() {
		// TODO Auto-generated method stub
		return nodes;
	}
	 @Override
	    public Optional<Node> getNode(int index) {
	        for (Node node: nodes) {
	            if (node.getId() == index) {
	                return Optional.of(node);
	            }
	        }
	        return Optional.empty();
	    }
}
