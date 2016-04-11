package edu.csula.cs460.graph.search;

import edu.csula.cs460.graph.Edge;
import edu.csula.cs460.graph.Graph;
import edu.csula.cs460.graph.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AStar implements SearchStrategy {
    @Override
    public List<Edge> search(Graph graph, Node source, Node dist) {
        return null;
    }
    HashMap<String, Tile> mapTiles=new HashMap<String,Tile>();
    Integer matrixLen=0;
    Integer size=0;
    Tile startTile=null;
    Tile endTile=null;
    /**
     * A lower level implementation to get path from key point to key point
     * @throws FileNotFoundException 
     */
    public String searchFromGridFile(File file)  {
    List<Tile> lstPath=new ArrayList<Tile>();
    	
        String result = "";
        
        try {
			@SuppressWarnings("resource")
			Scanner in = new Scanner(file);
			String templine="";
			int i=0;
			in.next();//Skip the first line
			Tile objtTile;
			String key=null;
			while (in.hasNext()) {
				templine=in.nextLine();
				
				if (!templine.contains("-")) {
					if (!templine.isEmpty()) {
						templine = templine.substring(1, templine.length() - 1);
						String[] tilesarray = templine.split("(?<=\\G..)");
						matrixLen = tilesarray.length;
						for (int j = 0; j < tilesarray.length; j++) {
							objtTile = new Tile(i, j, tilesarray[j]);
							key ="r"+String.valueOf(objtTile.getRowNumber()) +"c"+ String.valueOf(objtTile.getColumNumber());
							mapTiles.put(key, objtTile);
							if (tilesarray[j].contains("@")) {
								if (startTile == null) {
									startTile = objtTile;
								} else {
									endTile = objtTile;
								}
							}

						}
						i++;
					} 
				}
			}
			size=i;
			
		
			
			
		    PriorityQueue<Tile> tileQueue = new PriorityQueue<Tile>(matrixLen, new Comparator<Tile>() {
		        public int compare(Tile tile1,Tile tile2) {
		            return (tile1.getHuristicValue().getValue() >= tile2.getHuristicValue().getValue()) ? (1): (-1);
		        }
		    });
		    startTile.setHuristicValue(new Huristic(0, 0));
		    tileQueue.add(startTile);
			
			
			
			while(!tileQueue.isEmpty())
			{
				Tile t=tileQueue.poll();
				
				
				List<Tile> lstAdjacent=getAdjacentTiles(t);
				for(Tile obj:lstAdjacent)
				{
					
					if(obj.getHuristicValue()==null)
					{
						obj.setParent(t);
						calulatehuristic(obj);
						tileQueue.add(obj);
						if(obj.getData()==endTile.getData())
						{
							tileQueue.clear();
							break;
						}
						
					}
					
				}
				
				
				
				
				
			}
			
			Tile target=endTile;
			
			while(target.getData()!=startTile.getData())
			{
				lstPath.add(target);
				target=target.getParent();
			}
			
			Collections.reverse(lstPath);
			StringBuilder sb=new StringBuilder();
			for(Tile t: lstPath)
			{
				sb.append(t.getDirection());
				
			}
			
			result=sb.toString();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // TODO: read file and generate path using AStar algorithm

        return result;
    }
    
    public List<Tile> getAdjacentTiles(Tile t)
    {
    	List<Tile> neighbours=new ArrayList<Tile>();
    	int column=t.getColumNumber();
    	int row=t.getRowNumber();
    	String key=null;
    	if(row-1>=0) {
    		key="r"+String.valueOf(row-1)+"c"+String.valueOf(column);
    		if(!mapTiles.get(key).getData().contains("##"))
    		{
    			neighbours.add(mapTiles.get(key));
    			
    			if(mapTiles.get(key).getHuristicValue()==null)
    			mapTiles.get(key).setDirection("N");
    		}
			
		}
    	if(column+1<matrixLen) {
    		key="r"+String.valueOf(row)+"c"+String.valueOf(column+1);
    		if(!mapTiles.get(key).getData().contains("##"))
    		{
    			neighbours.add(mapTiles.get(key));
    			
    			if(mapTiles.get(key).getHuristicValue()==null)
    			mapTiles.get(key).setDirection("E");
    		}
    		
			
		}
    	if(column-1>=0)
    	{
    		key="r"+String.valueOf(row)+"c"+String.valueOf(column-1);
    		if(!mapTiles.get(key).getData().contains("##"))
    		{
    			neighbours.add(mapTiles.get(key));
    			
    			if(mapTiles.get(key).getHuristicValue()==null)
    			mapTiles.get(key).setDirection("W");
    		}
    		
    	}
    	
    	
    	if(row+1<size) {
    		key="r"+String.valueOf(row+1)+"c"+String.valueOf(column);
    		
    		if(!mapTiles.get(key).getData().contains("##"))
    		{
    			neighbours.add(mapTiles.get(key));
    			
    			if(mapTiles.get(key).getHuristicValue()==null)
    			mapTiles.get(key).setDirection("S");
    		}
			
		}
    	
    	return neighbours;
    }
    
    private void calulatehuristic(Tile paramTile) {
    	Huristic objHuristic=new Huristic(endTile.getRowNumber()-paramTile.getRowNumber(), endTile.getColumNumber()-paramTile.getColumNumber());
    	objHuristic.setPathDiff(paramTile.getParent().getHuristicValue().getPathDiff()+1);
    	 objHuristic.setValue(objHuristic.getPathDiff()+ Math.sqrt((objHuristic.getColDiff()*objHuristic.getColDiff())+(objHuristic.getRowDiff()*objHuristic.getRowDiff())));
    	 paramTile.setHuristicValue(objHuristic);
	}
    
    
}