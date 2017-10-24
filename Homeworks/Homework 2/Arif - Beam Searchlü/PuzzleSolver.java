package graph;
import java.util.*;
import org.*;

import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import org.graphstream.graph.Graph;

import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
public class PuzzleSolver {
	static int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
	static int x = 2;
	static int y = 2;
	static int tmp;
	static int[][] child = new int[3][3];
	static Queue<Node> queue = new LinkedList<Node>();
	public static void main(String[] args) {

		ArrayList<int[][]> ss = new ArrayList<int[][]>();
		Random gen = new Random();
		int num_of_initial_states = 10;
		int loop_counter = 0;
		
	
		do
		{
			int[][] startState = {{1,2,3},{4,5,6},{7,8,0}};
			randomize(startState, gen);
		/* To Check each produced
		----------------------------------------------------------
		
				for(int i = 0; i<3;i++)
			{
				for(int l = 0; l<3;l++)
				
					System.out.print((startState)[i][l]);
				
				System.out.println();
			}
			System.out.println();
		//-----------------------------------------------------------
		*/
			if(check(startState, ss))
				ss.add(startState);	
		 	loop_counter++;	
		}while(ss.size()< num_of_initial_states);
		

		
		for(int u=0; u<ss.size() ;u++ )
		{
			
			for(int i = 0; i<3;i++)
			{
				for(int l = 0; l<3;l++)
				
					System.out.print((ss.get(u))[i][l]);
				
				System.out.println();
			}
			System.out.println();
		}
		
		
		

		System.out.print("No of loops:");
		System.out.println(loop_counter);
		System.out.print("Initial states produced:");
		System.out.println(ss.size());
		
		
		Node root = new Node(ss.get(0),-1);
		root.print();
		
	
		beamSearch(root, 2);
		

	}
	static boolean isGoal()
	{
		
		for(int z = 0; z < 3 ; z++)
			for(int e = 0; e < 3;e++)
				if(child[z][e]!=goal[z][e])
					return false;
		return true;
	}
	static void beamSearch(Node root, int w)
	{
		Node current;
		queue.add(root);
		x = root.EmptyX;
		y = root.EmptyY;
		int index = 0;
		boolean check2 = true;
		while(index < w)
		{
			current = queue.poll();
			System.out.println("Current Node");
			current.print();
			x = current.EmptyX;
			y = current.EmptyY;
			deepCopy(current.state );	
			if((current.possibleMoves)[0]==true)
			{
				System.out.println("girdi1");
				moveUp(child);
				Node sub = new Node(child,0);
				sub.print();
				if(isGoal())
				{
					System.out.println("Goal state has been found");
					return;
				}
				current.addChild(sub);
				deepCopy(current.state );
				queue.add(sub);
				
				x = current.EmptyX;
				y = current.EmptyY;
				
				
			}
			if((current.possibleMoves)[1]==true)
			{
				System.out.println("girdi2");
				moveDown(child);
				Node sub = new Node(child,1);
				sub.print();
				if(isGoal())
				{
					System.out.println("Goal state has been found");
					return;
				}
				current.addChild(sub);
				deepCopy(current.state );
				queue.add(sub);
				
				x = current.EmptyX;
				y = current.EmptyY;
				
				
			}
			if((current.possibleMoves)[2]==true)
			{
				System.out.println("girdi3");
				moveRight(child);
				
				Node sub = new Node(child,2);
				sub.print();
				if(isGoal())
				{
					System.out.println("Goal state has been found");
					return;
				}
				current.addChild(sub);
				deepCopy(current.state );
				queue.add(sub);
				x = current.EmptyX;
				y = current.EmptyY;
				
				
				
			}
			if((current.possibleMoves)[3]==true)
			{
				System.out.println("girdi4");
				moveLeft(child);
				
				Node sub = new Node(child,3);
				if(isGoal())
				{
					System.out.println("Goal state has been found");
					return;
				}
				sub.print();
				current.addChild(sub);
				queue.add(sub);
				
				
					
				
			}
			//for first level of states
			if(check2)
			{
				check2=false;
				eliminate(w);
			}
			else 
			{
			index++;
			}
			
			if(index == w)
			{
				eliminate(w);
				index=0;
				
			}
			
			
		}
				
			
				
		
	}
	
	public static void eliminate(int w)
	{
		if(queue.size() <3 && w==3)
			w=2;
		
		Node min1 = queue.peek();
		
		for(Node item: queue)
		{
			if(min1.compareTo(item) != -1)
			{
				min1= item;
			}
			
			
		}
		queue.remove(min1);
		Node min2 = queue.peek();
		for(Node item: queue)
		{
			if(min2.compareTo(item) != -1)
			{
				min2= item;
			}
			
		}	
		queue.remove(min2);
		Node min3 = queue.peek();
		if(w == 3)
		{
		
			
			for(Node item: queue)
			{
				if(min3.compareTo(item) != -1)
				{
					min3= item;
				}
				
			}
			queue.clear();
			queue.add(min1);
			queue.add(min2);
			queue.add(min3);
			
		}
		else
		{
		queue.clear();
		queue.add(min1);
		queue.add(min2);
		}
		
		
	}
	
	static void deepCopy(int arr2[][])
	{
		
			for(int i = 0; i<3;i++)
			{
				for(int l = 0; l<3;l++)
					child[i][l] = arr2[i][l];

				
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
	
	static void randomize(int[][] arr, Random r)
	{
		for(int i = 0; i<10;i++)
		{
			tmp = r.nextInt(4);
			
			if(tmp==0)
				moveUp(arr);
			else if(tmp==1)
				moveDown(arr);
			else if(tmp==2)
				moveRight(arr);
			else
				moveLeft(arr);
		
			
		}
		x = 2;
		y = 2;
	}
	
	//------------------------------------------------------------------------

	static boolean check(int[][] arr, ArrayList<int[][]> arrList)
	{
		int [][] tmparr;
		for(int i = 0 ; i < arrList.size() ; i++)
		{
			int counter = 0;
			tmparr = arrList.get(i);
			int[] tmparrR;
			int[] arrR;
			for (int m = 0; m < 3; m++){
				arrR = getRow(arr,m);
				tmparrR = getRow(tmparr,m);
			
			if((Arrays.equals(arrR,tmparrR))){
				counter++;
			}	
		}
			if(counter == 3)
				return false;
		
		}
		return true;
	}
	
	//get row
	public static int[] getRow(int[][] array, int index){
    int[] row = {0,0,0};
    for(int ii=0; ii<3; ii++){
       row[ii] = array[index][ii];
    }
    return row;
	}
	
 
}