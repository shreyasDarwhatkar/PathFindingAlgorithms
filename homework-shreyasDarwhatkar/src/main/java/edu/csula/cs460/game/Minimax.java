package edu.csula.cs460.game;
import edu.csula.cs460.graph.Graph;
import edu.csula.cs460.graph.Node;

public class Minimax {

	public static Node getBestMove(Graph graph, Node root, Integer depth, Boolean max) {
		
		Node returnNode=null;
		if(depth==0)
		{
			return root;
		}
		if(max==true)
		{
			Integer bestNode=Integer.MIN_VALUE;
			for(Node child:graph.neighbors(root))
			{
				Node n=getBestMove(graph, child, depth-1, false);
				bestNode=(bestNode>(Integer)child.getData())?bestNode:(Integer)child.getData();
				graph.getNode(root.getId()).get().setData(bestNode);
				
				if((Integer)root.getData()<(Integer)child.getData())
				{
					returnNode=child;
				}
				root.setData(bestNode);

			}
			return returnNode;
		}
		else  
		{
			Integer bestNode=Integer.MAX_VALUE;
			for(Node child:graph.neighbors(root))
			{
				Node n=getBestMove(graph, child, depth-1, true);
				bestNode=(bestNode<(Integer)child.getData())?bestNode:(Integer)child.getData();
				graph.getNode(root.getId()).get().setData(bestNode);
				
				if((Integer)root.getData()>(Integer)child.getData())
				{
					returnNode=child;
				}
				root.setData(bestNode);
			}
			return returnNode;
		}
	}

}