package edu.csula.cs460.file;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MatrixFile {
    private  int[][] matrix;

    public MatrixFile(String filepath) {
        // since we don't know how many rows there is going to be, we will
        // create a list to hold dynamic arrays instead
        List<int[]> dynamicMatrix = Lists.newArrayList();

        try {
            // use Guava to read file from resources folder
            String content = Resources.toString(
                Resources.getResource(filepath),
                Charsets.UTF_8
            );
            
            String [] tempMatrix=content.split("\n");
            int i=0;
            matrix=new int[tempMatrix.length][tempMatrix.length];
            for(String s:tempMatrix)
            {
            	String [] lineMatrix=s.split(" ");
            	int j=0;
            	while(j<lineMatrix.length)
            	{
            		matrix[i][j]=Integer.parseInt(lineMatrix[j].toString().trim());
            		j++;
            	}
            	i++;
            	
            }
            
            

            /*Arrays.stream(content.split("\n"))
                .forEach(line -> {
                    dynamicMatrix.add(
                        Arrays.stream(line.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray()
                    );
                });*/
        } catch (IOException e) {
            // in case of error, always log error!
            System.err.println("MatrixFile has trouble reading file");
            e.printStackTrace();
        }

        
    }

    public int getValue(int row, int col) {
        return matrix[row][col];
    }

    public int getSum() {
        return Arrays.stream(matrix)
            .mapToInt(row -> Arrays.stream(row).sum())
            .sum();
    }
}
