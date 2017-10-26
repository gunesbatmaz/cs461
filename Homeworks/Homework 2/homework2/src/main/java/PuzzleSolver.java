import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class PuzzleSolver {
    static int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
    static int x = 2;
    static int index = 0;
    static int y = 2;
    static final int W = 2;
    static int[][] child = new int[3][3];
    static Queue<Node1> queue = new LinkedList<Node1>();
    static int id = 0;
    static int length = 0;
    static int closedNodes = 0;
    static Graph graph = new SingleGraph("graph");
    static Scanner read;
    static int green = 250;
    static int blue = 10;
    static ArrayList<int[][]> ss;
    static ArrayList<Integer> sn;
    static ArrayList<Integer> sl;
    
    public static void main(String[] args) throws IOException {
        ss = new ArrayList<int[][]>();
        sl = new ArrayList<Integer>();
        sn = new ArrayList<Integer>();
        int[][] startState = new int[3][3];
        getArray();
        Node1 root;
        root = new Node1(ss.get(0),-1,id);
        root.print();
        id++;
        graph.addNode(""+root.getManDistance()+root.getId());

        Node n = graph.getNode(""+root.getManDistance()+root.getId());
        n.addAttribute("ui.label","root");
        graph.display();
        beamSearch(root, W);  
        System.out.println("closedNodes:" +closedNodes +", length: " + length);
    }
    static void getArray() throws FileNotFoundException{
        read = new Scanner(new File("states.txt"));
        int[][] arr;
        while(read.hasNext()){
             arr = new int[3][3];
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    arr[i][j] = read.nextInt();
                }
            }
            ss.add(arr);
        }
    }
    
    

    static boolean isGoal(){
        for(int z = 0; z < 3 ; z++)
            for(int e = 0; e < 3;e++)
                if(child[z][e]!=goal[z][e])
                    return false;
        return true;
    }
    static void beamSearch(Node1 root, int w){
    	int tmpw = w;
        Node1 current;
        queue.add(root);
        x = root.EmptyX;
        y = root.EmptyY;
        index = 0;
        boolean check2 = true;
        while(index < w)
        {
            w=tmpw;
            current = queue.poll();
            System.out.println("Current Node");
            current.print();
            x = current.EmptyX;
            y = current.EmptyY;
            deepCopy(current.state);
            if((current.possibleMoves)[0]==true)
            {
                System.out.println("girdi1");
                moveUp(child);
                Node1 sub = new Node1(child,0,id);
                graph.addNode(""+sub.getManDistance()+sub.getId());
                graph.addEdge(""+sub.getManDistance()+current.getManDistance()+sub.getId(),""+current.getManDistance()+current.getId(),""+sub.getManDistance()+sub.getId());
                Node n = graph.getNode(""+sub.getManDistance()+sub.getId());
                n.addAttribute("ui.label",sub.getManDistance());
                n.setAttribute("ui.style","fill-color: rgb(255,0,0);");
                closedNodes++;                
                sub.print();
                if(isGoal())
                {
                    n.setAttribute("ui.style","fill-color: rgb(0,0,255);");
                    System.out.println("Goal state has been found");
                    return;
                }
                current.addChild(sub);
                deepCopy(current.state );
                queue.add(sub);
                id++;

                x = current.EmptyX;
                y = current.EmptyY;


            }
            if((current.possibleMoves)[1]==true)
            {
                System.out.println("girdi2");
                moveDown(child);
                Node1 sub = new Node1(child,1,id);
                graph.addNode(""+sub.getManDistance()+sub.getId());
                graph.addEdge(""+sub.getManDistance()+current.getManDistance()+sub.getId(),""+current.getManDistance()+current.getId(),""+sub.getManDistance()+sub.getId());
                Node n = graph.getNode(""+sub.getManDistance()+sub.getId());
                n.addAttribute("ui.label",sub.getManDistance());
                n.setAttribute("ui.style","fill-color: rgb(255,0,0);");
                closedNodes++;
                sub.print();
                if(isGoal())
                {
                    n.setAttribute("ui.style","fill-color: rgb(0,0,255);");
                    System.out.println("Goal state has been found");
                    return;
                }
                current.addChild(sub);
                deepCopy(current.state );
                queue.add(sub);
                id++;

                x = current.EmptyX;
                y = current.EmptyY;


            }
            if((current.possibleMoves)[2]==true)
            {
                System.out.println("girdi3");
                moveRight(child);

                Node1 sub = new Node1(child,2,id);
                graph.addNode(""+sub.getManDistance()+sub.getId());
                graph.addEdge(""+sub.getManDistance()+current.getManDistance()+sub.getId(),""+current.getManDistance()+current.getId(),""+sub.getManDistance()+sub.getId());
                Node n = graph.getNode(""+sub.getManDistance()+sub.getId());
                n.addAttribute("ui.label",sub.getManDistance());
                n.setAttribute("ui.style","fill-color: rgb(255,0,0);");
                closedNodes++;

                sub.print();
                if(isGoal())
                {
                    n.setAttribute("ui.style","fill-color: rgb(0,0,255);");
                    System.out.println("Goal state has been found");
                    return;
                }
                current.addChild(sub);
                deepCopy(current.state );
                queue.add(sub);
                id++;

                x = current.EmptyX;
                y = current.EmptyY;



            }
            if((current.possibleMoves)[3]==true)
            {
                System.out.println("girdi4");
                moveLeft(child);

                Node1 sub = new Node1(child,3,id);
                graph.addNode(""+sub.getManDistance()+sub.getId());
                graph.addEdge(""+sub.getManDistance()+current.getManDistance()+sub.getId(),""+current.getManDistance()+current.getId(),""+sub.getManDistance()+sub.getId());
                Node n = graph.getNode(""+sub.getManDistance()+sub.getId());
                n.addAttribute("ui.label",sub.getManDistance());
                n.setAttribute("ui.style","fill-color: rgb(255,0,0);");
                closedNodes++;

                if(isGoal())
                {
                    n.setAttribute("ui.style","fill-color: rgb(0,0,255);");
                    System.out.println("Goal state has been found");
                    return;
                }
                sub.print();
                current.addChild(sub);
                queue.add(sub);id++;

            }
            
          //for first level of states
            if(check2){
                check2=false;
                eliminate(w);
                if(queue.size() <3 && w == 3)
                    index=1;
                 else
                 	index=0;
            }
            else{
                index++;
            }
            
            if(index >= w){
                eliminate(w);
                if(queue.size() <3 && w==3)
                    index=1;
                else	
                    index=0;
            }
        }
    }
    static void eliminate(int w){
        length++;
    	if(queue.size() <3 && w==3)
             w=2;
        Node1 min1 = queue.peek();

        for(Node1 item: queue)
        {
            if(min1.compareTo(item) != -1)
            {
                min1= item;
            }
        }
        queue.remove(min1);
        Node1 min2 = queue.peek();
        for(Node1 item: queue)
        {
            if(min2.compareTo(item) != -1)
            {
                min2= item;
            }
        }
        queue.remove(min2);
        Node1 min3 = queue.peek();
        if(w == 3)
        {
            for(Node1 item: queue)
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
            Node n = graph.getNode(""+min1.getManDistance()+min1.getId());
            Node n1 = graph.getNode(""+min2.getManDistance()+min2.getId());
            Node n2 = graph.getNode(""+min3.getManDistance()+min3.getId());
            if(n != null)
                n.setAttribute("ui.style","fill-color: rgb("+blue%255+","+ green +","+blue%255+");");
            if(n1 != null)
                n1.setAttribute("ui.style","fill-color: rgb("+blue%255+","+ green +","+blue%255+");");
            if(n2 != null)
                n2.setAttribute("ui.style","fill-color: rgb("+blue%255+","+ green +","+blue%255+");");
            closedNodes -= 3;
        }
        else
        {
            queue.clear();
            queue.add(min1);
            queue.add(min2);
            Node n = graph.getNode(""+min1.getManDistance()+min1.getId());
            if(n != null)
                n.setAttribute("ui.style","fill-color: rgb("+blue%255+","+ (green) +","+blue%255+");");
            Node n1 = graph.getNode(""+min2.getManDistance()+min2.getId());
            if(n1 != null)
                n1.setAttribute("ui.style","fill-color: rgb("+blue%255+","+ (green) +","+blue%255+");");
            closedNodes -= 2;
            
        }
        if (green > 100)
            green = green - 50 ;
        else
            green = 250;
        try        
        {
            Thread.sleep(1000);
        } 
        catch(InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }

    }
    static void deepCopy(int arr2[][]){

        for(int i = 0; i<3;i++)
        {
            for(int l = 0; l<3;l++)
                child[i][l] = arr2[i][l];


        }


    }
    static void moveUp(int[][] arr){
        if(x == 0 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x-1,y,arr);
        x=x-1;
    }
    static void moveDown(int[][] arr){
        if(x == 2 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x+1,y,arr);
        x=x+1;
    }
    static void moveRight(int[][] arr){
        if(y == 2 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x,y+1,arr);
        y=y+1;
    }
    static void moveLeft(int[][] arr){
        if(y == 0 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x,y-1,arr);
        y=y-1;
    }
    static void swap(int rowa , int columna, int rowb, int columnb,int[][] arr){
        int temp = arr[rowa][columna];
        arr[rowa][columna] = arr[rowb][columnb];
        arr[rowb][columnb] = temp;
    }
}