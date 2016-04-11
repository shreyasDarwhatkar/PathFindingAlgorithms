package edu.csula.cs460.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReferenceArray;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.io.Resources;

public class ListFile {
    // try to make *all* variables final as possible
    private Map<String, List<String>> adjacencyList;

    public ListFile(String filepath) {
        // ListMultimap is a very useful class when it comes to Map of key
        // to a Collection of values
        //ListMultimap<String, String> multimap = ArrayListMultimap.create();
    	List<String> connectingNodes=null;
    	adjacencyList=new LinkedHashMap<String, List<String>>();

        try {
            // use Guava to read file from resources folder
            String content = Resources.toString(
                Resources.getResource(filepath),
                Charsets.UTF_8
            );
            String [] tempMatrix=content.split("\n");
            for(int i=0;i<tempMatrix.length;i++)
            {
            	connectingNodes=new ArrayList<String>();
            	String[] parts=tempMatrix[i].split(":");
            	
            	for(String s:parts[1].split(" "))
            		connectingNodes.add(s.toString().trim());
            	adjacencyList.put(parts[0].toString().trim(), connectingNodes);
            }
            

                    } catch (IOException e) {
            // in case of error, always log error!
            System.err.println("ListFile has trouble reading file");
            e.printStackTrace();
        }

        //adjacencyList = Multimaps.asMap(multimap);
    }

    public List<String> getList(String key) {
        return adjacencyList.get(key);
    }
}
