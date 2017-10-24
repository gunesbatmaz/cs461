import java.util.*;
public class Jugs {


		static final int maxX = 6;
		static final int maxY = 10;
		static int[][] states = new int[16][2];
		static int[][] edges = new int[16][16];
		static int index = 1;
		static int oldstate;
		static int x =0 ;
		static int y=0;
		static int branch;
		static boolean[] visited = new boolean[16];
		static List<Integer> path = new ArrayList<Integer>();

		public static void main(String[] args) 
		{
			for(int i=0; i<16; i++){
				visited[i]= false;
				
			}
			for(int i = 0 ; i < 16 ; i++)
			{
				x = states[i][0];
				
				y = states[i][1];
				System.out.println("x =" + x + "; y = " + y);
				if(fill("X")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
				
				x = states[i][0];
				y = states[i][1];
				if(fill("Y")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
					
				x = states[i][0];
				y = states[i][1];
				if(empty("X")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
				
				x = states[i][0];
				y = states[i][1];
				if(empty("Y")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
				
				x = states[i][0];
				y = states[i][1];
				if(pour("X")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
				
				x = states[i][0];
				y = states[i][1];
				if(pour("Y")){
					if(check())
					{
						System.out.println("x =" + x + "; y = " + y);
						states[index][0]=x;
						states[index][1]=y;
						edges[i][index]=1;
						System.out.println("index: "+index + "States: "+ states[index][0]+ " " + states[index][1]);
						index++;
					}
					else
						edges[i][oldstate]=1;}
				System.out.println(i);
			}
			
			int b=0;
			for(int i = 0 ; i < 16 ; i++){
				for(int j = 0 ; j < 16 ; j++)
				{
					if(i == j)
						edges[i][j]=0;
					System.out.print(edges[i][j] +"\t");
					b = b + j; 
				}
				System.out.println("");
				branch=(branch>b)?branch:b;
			}
			
			
			for(int i = 0 ; i < 16 ; i++){
				for(int j = 0 ; j < 2 ; j++)
					System.out.print(states[i][j] +"\t");
				System.out.println("");
			}
			
			DFS(0);
		for(int i = 0 ; i < 16 ; i++){
				System.out.print(path.get(i) + " ");
				
				if(states[path.get(i)][1]==8)
					break;
			}
		}
		
		public static boolean fill(String type)
		{
			if(type.equals("X"))
				if(x == maxX)
					return false;
				else
					x = maxX;
			else
				if(y == maxY)
					return false;
				else
					y = maxY;
			return true;
		}
		
		public static boolean empty(String type)
		{
			if(type.equals("X"))
				if (x==0)
					return false;
				else
					x=0;
			else
				if (y==0)
					return false;
				else
					y=0;
				
			return true;
		}
		
		public static boolean pour(String type)
		{
			if (type=="X")
			{
				if(x + y >= maxY)
				{
					x = x-(maxY - y);
					y = maxY;
					
				}
				else
				{
					y = x + y;
					x = 0;
				}
			}
			else
				if(x + y >= maxX)
				{
					y = y - maxX + x;
					x = maxX;
					
				}
				else
				{
					x = x + y;
					y = 0;
				}
				
			return true;
		}
		
		public static boolean check()
		{
			for(int u=0; u < 16; u++)
				if(states[u][0]==x && states[u][1]==y){
					oldstate=u;
					return false;}
			return true;
		}
		
		public static void DFS(int i)
		{	
			path.add(i);
			visited[i]= true;
			for (int j = 0; j<16; j++){
				if(!(visited[j]) && edges[i][j]==1)
					DFS(j);
			}    	
		}	
	
}                        