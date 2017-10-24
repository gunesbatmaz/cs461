import java.util.*;
/*String styleSheet =
		        "node {" +
		        "	fill-color: black;" +
		        "}" +
		        "node.marked {" +
		        "	fill-color: red;" +
		        "}";
	
	Graph graph = new SingleGraph("Tutorial 1");
	graph.addAttribute("ui.stylesheet", styleSheet);
	graph.addNode("A(0/0)");
	graph.setStrict(false);
	graph.setAutoCreate( true );
	graph.getNode("A(0/0)").setAttribute("ui.class", "marked");
	
	graph.addNode("B(6/0)");
	graph.addNode("C(0/10)");
	graph.addNode("D(6/10)");
	graph.addNode("E(0/6)");
	graph.addNode("F(6/4)");
	graph.addNode("G(6/6)");
	graph.addNode("H(0/4)");
	graph.addNode("J(2/10)");
	graph.addNode("K(4/0)");
	graph.addNode("L(2/0)");
	graph.addNode("M(4/10)");
	graph.addNode("N(0/2)");
	graph.addNode("P(6/8)");
	graph.addNode("R(6/2)");
	graph.addNode("S(0/8)");
	
	graph.getNode("B(6/0)").setAttribute("ui.class", "marked");
	
	graph.getNode("D(6/10)").setAttribute("ui.class", "marked");
	graph.getNode("C(0/10)").setAttribute("ui.class", "marked");
	graph.getNode("F(6/4)").setAttribute("ui.class", "marked");
	graph.getNode("H(0/4)").setAttribute("ui.class", "marked");
	graph.getNode("K(4/0)").setAttribute("ui.class", "marked");
	graph.getNode("M(4/10)").setAttribute("ui.class", "marked");
	graph.getNode("P(6/8)").setAttribute("ui.class", "marked");
	System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	
	for(int x = 0; x <16; x++)
	{	
		for(int y = 0 ; y < 16 ; y++)
		{
			if(edges[x][y]==1 && x>=y)
			{
				String tmp1 = harf[x];
				String tmp2 = harf[y];
				graph.addEdge(""+tmp1+tmp2, graph.getNode(tmp1),graph.getNode(tmp2),true);
				graph.getEdge(""+tmp1+tmp2).setAttribute("ui.class", "marked");
			}
		
				
	
		}
		
	}
	  
	for (Node node : graph) 
	{
		node.addAttribute("ui.label", node.getId());
	}
	graph.display();*/
public class Jugs3 {


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
		static String[] stateName = new String[16];
		static int[] bf = new int[16];
		
		public static void main(String[] args) 
		{
			String[] harf = {"A(0/0)","B(6/0)","C(0/10)","E(6/10)","D(0/6)","M(6/4)","F(6/6)","S(0/4)","G(2/10)","R(4/0)","H(2/0)","P(4/10)","J(0/2)","N(6/8)","K(6/2)","L(0/8)"};
			
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
						
						System.out.println("index: "+index + " States: "+ states[index][0]+ " " + states[index][1]);
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
			}//finds vertices
			
			int b=0;
			for(int i = 0 ; i < 16 ; i++){
				for(int j = 0 ; j < 16 ; j++)
				{
					if(i == j)
						edges[i][j]=0;
					System.out.print(edges[i][j] +"\t");
					b = b + j; 
				}
				System.out.println();
			}//Displays the adj. matrix
			
			int t =0;
			double avg = 0;
			for (int i = 0; i < 16; i++)
			{
				t =0;
				for(int j = 0; j <16 ; j++)
				{
					t += edges[i][j];
				}
				avg += t;
			}
			avg = avg/16;
		
			System.out.println("avg: " + avg);
			
			
			System.out.println("Edges List");
			for(int i = 0 ; i < 16 ; i++){
				for(int j = 0 ; j < 16 ; j++){
					if(edges[i][j]==1)
						System.out.println("['"+harf[i]+"', '"+harf[j]+"'], ");
				}
			}//displays the edges
			System.out.println("");
			
			for(int i = 0 ; i < 16 ; i++){
				stateName[i] = harf[i];
				for(int j = 0 ; j < 2 ; j++)
					System.out.print(states[i][j] +"\t");
				System.out.print(stateName[i]);
				System.out.println("");
			}
			
			DFS(0);
		for(int i = 0 ; i < 16 ; i++){
			//	System.out.print(path.get(i) + " ");
				System.out.print(harf[path.get(i)] + "\t");
				//if(states[path.get(i)][1]==8)
				//	break;
			}
		
	/*		System.out.println("");
			for(int i = 0 ; i < 1 ; i++){
			//	System.out.print(path.get(i) + " ");
				System.out.print(harf[path.get(15-i)] + "\t");
				if(states[path.get(15-i)][1]==8)
					break;
			}
			
		*/
	}
		
		
		//----------------------------------------------------------------------
		
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
			System.out.println(i);	
			path.add(i);
			visited[i]= true;
			for (int j = 0; j<16; j++){
				if(!(visited[j]) && edges[i][j]==1)
					DFS(j);
			}    	
		}	
	
}