package edu.csula.cs460.graph.strategy;

import edu.csula.cs460.graph.Edge;
import edu.csula.cs460.graph.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class AdjacencyMatrix implements Representation {
	private List<Node> lstnodes;
    private Node[] nodes;
    private int[][] adjacencyMatrix;
    private int matrixLength;
    String content;

    protected AdjacencyMatrix(File file) {
        // TODO: read file and parse the file content into adjacencyMatrix

		
		try {
			content = Files.toString(file, Charsets.UTF_8);
			lstnodes=new ArrayList<Node>();
			adjacencyMatrix=convertStringToMatrix(content,Integer.parseInt(content.split("\n")[0].toString().trim()),true);
			System.out.println(Arrays.deepToString(adjacencyMatrix));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    protected AdjacencyMatrix() {
		// TODO Auto-generated constructor stub
    	nodes = new Node[0];
        adjacencyMatrix = new int[0][0];
	}

	private int[][] convertStringToMatrix(String content,int size,boolean lnode )
    {
     String[] contentArray=content.split("\n");
     String[] tempLine;
     matrixLength=size;
     String [] contentArrayNew=Arrays.copyOfRange(contentArray ,1,contentArray.length);
     int [][] adjMatrix=new int[matrixLength][matrixLength];
    // Arrays.stream(adjMatrix).forEach(line);
     if(lnode)
     {
     for( int i=0;i<contentArrayNew.length;i++)
     {
    	 tempLine=contentArrayNew[i].split(":");
    	 Node objnode1=new Node(Integer.parseInt(tempLine[0].toString().trim()));
    	 Node objnode2=new Node(Integer.parseInt(tempLine[1].toString().trim()));
    	 if(!lstnodes.contains(objnode1))
    	 lstnodes.add(objnode1);
    	 if(!lstnodes.contains(objnode2))
    	 lstnodes.add(objnode2);
    	 //adjMatrix[Integer.parseInt(tempLine[0].toString().trim())][Integer.parseInt(tempLine[1].toString().trim())]=Integer.parseInt(tempLine[2].toString().trim());
     }
     }
     for( int i=0;i<contentArrayNew.length;i++)
     {int row=-1,column=-1;
    	 tempLine=contentArrayNew[i].split(":");
    	 Node objnode1=new Node(Integer.parseInt(tempLine[0].toString().trim()));
    	 Node objnode2=new Node(Integer.parseInt(tempLine[1].toString().trim()));
    	 row=lstnodes.indexOf(objnode1);
    	 column=lstnodes.indexOf(objnode2);
    	 if(row>=0 && column>=0)
    	 adjMatrix[row][column]=Integer.parseInt(tempLine[2].toString().trim());
     }
     
     nodes=lstnodes.toArray(new Node[lstnodes.size()]);
     return adjMatrix;
    }

    @Override
    public boolean adjacent(Node x, Node y) {
    	int row=lstnodes.indexOf(x);
    	int column=lstnodes.indexOf(y);
    	if(adjacencyMatrix[row][column]>0)
    		return true;
    	else
        return false;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Node> neighbors(Node x) {
    	int row=lstnodes.indexOf(x);
    	List<Node> lstAdjNodes=new ArrayList<Node>();
    	for(int i=0;i<matrixLength;i++)
    	{
    		if(adjacencyMatrix[row][i]>0)
    		{
    			
    			lstAdjNodes.add(nodes[i]);
    		}
    	}
    		
        Collections.sort(lstAdjNodes);
        return lstAdjNodes;
    }

    @Override
    public boolean addNode(Node x) {
    	if(!lstnodes.contains(x))
    	{
    	lstnodes.add(x);
    	adjacencyMatrix =convertStringToMatrix(content, Integer.parseInt(content.split("\n")[0].toString().trim())+1,false);
    	//System.out.println(Arrays.deepToString(adjacencyMatrix));
        return true;
    	}
    	else
    	return false;
    }

    @Override
    public boolean removeNode(Node x) {
    	if(lstnodes.contains(x))
    	{
    	lstnodes.remove(x);
    	adjacencyMatrix =convertStringToMatrix(content, Integer.parseInt(content.split("\n")[0].toString().trim())-1,false);
        return true;
    	}
    	else
    		return false;
    }

    @Override
    public boolean addEdge(Edge x) {
    	if(lstnodes.contains(x.getFrom())&&lstnodes.contains(x.getTo()))
    	{
    		int row= lstnodes.indexOf(x.getFrom());
    		int col=lstnodes.indexOf(x.getTo());
    		if(adjacencyMatrix[row][col]==1)
    		{
    			return false;
    		}
    		else{
    		adjacencyMatrix[row][col]=x.getValue();
    		return true;
    		}
    	}
    	else
    		return false;
    }

    @Override
    public boolean removeEdge(Edge x) {
    	if(lstnodes.contains(x.getFrom())&&lstnodes.contains(x.getTo()))
    	{
    		if(adjacencyMatrix[lstnodes.indexOf(x.getFrom())][lstnodes.indexOf(x.getTo())]==1)
    		{
    
    		adjacencyMatrix[lstnodes.indexOf(x.getFrom())][lstnodes.indexOf(x.getTo())]=0;
    		return true;
    		}
    		else
    			return false;
    	}
    	else
    		return false;
    }
    @Override
    public int distance(Node from, Node to) {
        // TODO: implement a method to get edge value between fromNode to toNode
    	if(lstnodes.contains(from) && lstnodes.contains(to))
    	{
    		
    		return adjacencyMatrix[lstnodes.indexOf(from)][lstnodes.indexOf(to)]; 
    	}
    	else
        return 0;
    }

	@Override
	public List<Node> getNodes() {
		// TODO Auto-generated method stub
		return lstnodes;
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
	 @Override
	    public String toString() {
	        String result = "nodes: ";
	        for (Node node: nodes) {
	            result += node.toString() + ", ";
	        }

	        result += "\nMatrix:\n";

	        for (int[] row: adjacencyMatrix) {
	            for (int value: row) {
	                result += value + " ";
	            }
	            result += "\n";
	        }

	        return result;
	    }
}
