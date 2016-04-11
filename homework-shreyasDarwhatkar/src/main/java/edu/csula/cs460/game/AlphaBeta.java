package edu.csula.cs460.game;

import javax.xml.transform.Source;

import edu.csula.cs460.graph.Graph;
import edu.csula.cs460.graph.Node;

public class AlphaBeta {
	public static Node getBestMove(Graph graph, Node source, Integer depth, Integer alpha, Integer beta, Boolean max) {
		// TODO: implement your alpha beta pruning algorithm here
		
			Node returnNode=source;

			if(depth==0)
			{
				return source;
			}
			if(max==true)
			{
				Integer bestNode=Integer.MIN_VALUE;
				for(Node child:graph.neighbors(source))
				{
					Node n=getBestMove(graph, child,depth-1,alpha,beta,false);
					bestNode=(bestNode>(Integer)child.getData())?bestNode:(Integer)child.getData();
					alpha=(alpha>(Integer)n.getData())?alpha:(Integer)n.getData();
					
					graph.getNode(source.getId()).get().setData(bestNode);
					if((Integer)source.getData()<(Integer)child.getData())
					{
						returnNode=child;
					}
					else
					{
						if(graph.neighbors(source)!=null)
						for(Node r:graph.neighbors(source))
						{
							if((Integer)r.getData()==bestNode)
							returnNode=r;
						}
					}
					
					
					//source.setData((Integer)child.getData());
					if(bestNode>(Integer)child.getData())
					source.setData(bestNode);
					else {
						//if(alpha<=beta)
						source.setData((Integer)child.getData());
					}
					if(alpha>=beta)
					{
						break;
					}
					

				}
				return returnNode;
			}
			else  
			{
				Integer bestNode=Integer.MAX_VALUE;

				for(Node child:graph.neighbors(source))
				{
					Node n=getBestMove(graph, child, depth-1,alpha,beta, true);
					bestNode=(bestNode<(Integer)child.getData())?bestNode:(Integer)child.getData();
					beta=(beta<(Integer)n.getData())?beta:(Integer)n.getData();
					
					graph.getNode(source.getId()).get().setData(bestNode);
					if((Integer)source.getData()>(Integer)child.getData())
					{
						returnNode=child;
					}
					
					//source.setData(child.getData());
					
					if(bestNode<=(Integer)child.getData())
					{
					source.setData(bestNode);
					}
					else
					{
						//if(alpha<=beta)
						source.setData((Integer)child.getData());
					}
					if(alpha>=beta)
					{
						break;
					}
					
				}
				return returnNode;
			}
		} 



	
}