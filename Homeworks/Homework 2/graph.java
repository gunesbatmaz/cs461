package graph;
import java.util.*;
import org.*;

import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import org.graphstream.graph.Graph;

import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
public class graph {
	static int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial1 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial2 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial3 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial4 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial5 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial6 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial7 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial8 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial9 = {{1,2,3},{4,5,6},{7,8,0}};
	static int[][] initial10 = {{1,2,3},{4,5,6},{7,8,0}};
	static int x = 2;
	static int y = 2;
	public static void main(String[] args) 
	{
		ArrayList<int[][]> i1 = new ArrayList<int[][]>();
		Random gen = new Random();
		
		i1.add(initial1);
		i1.add(initial2);
		i1.add(initial3);
		i1.add(initial4);
		i1.add(initial5);
		i1.add(initial6);
		i1.add(initial7);
		i1.add(initial8);
		i1.add(initial9);
		i1.add(initial10);
		
		for(int u=0; u<i1.size();u++ )
		{
			for(int i = 0; i<30;i++)
			{
				int tmp = gen.nextInt(4);
				
				if(tmp==0)
					moveUp(i1.get(u));
				else if(tmp==1)
					moveDown(i1.get(u));
				else if(tmp==2)
					moveRight(i1.get(u));
				else
					moveLeft(i1.get(u));
				
				
			}
			x = 2;
			y = 2;
			
		}
		
		for(int u=0; u<i1.size();u++ )
		{
			
			for(int i = 0; i<3;i++)
			{
				for(int l = 0; l<3;l++)
				{
					System.out.print((i1.get(u))[i][l]);
				}
				System.out.println();
			}
			System.out.println();
			
		}
		
		
	}
	
	
	static void moveUp(int[][] arr)
	{

		if(x == 0 || arr[x][y] !=0 || x>2 || y>2)
			return;
		
		else
			swap(x,y,x-1,y,arr);
		
		x=x-1;
		
	}
	static void moveDown(int[][] arr)
	{

		if(x == 2 || arr[x][y] !=0 || x>2 || y>2)
			return;
		
		else
			swap(x,y,x+1,y,arr);
		
		x=x+1;
		
	}
	static void moveRight(int[][] arr)
	{

		if(y == 2 || arr[x][y] !=0 || x>2 || y>2)
			return;
		
		else
			swap(x,y,x,y+1,arr);
		
		y=y+1;
		
	}
	static void moveLeft(int[][] arr)
	{

		if(y == 0 || arr[x][y] !=0 || x>2 || y>2)
			return;
		
		else
			swap(x,y,x,y-1,arr);
		
		y=y-1;
		
	}
	
	static void swap(int rowa , int columna, int rowb, int columnb,int[][] arr)
	{
		int temp = arr[rowa][columna];
		arr[rowa][columna] = arr[rowb][columnb];
		arr[rowb][columnb] = temp;
				
	}
	
	

	
}
