
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PuzzleGenerator {
    private int x,y;
    private int[][] arr;
    private BufferedWriter w;
    private int n;
    private final int[][] GOAL = {{1,2,3},{4,5,6},{7,8,0}};
    ArrayList<int[][]> ss;

    public PuzzleGenerator() throws IOException{
        this.x = 2;
        this.y = 2;
        this.n = 10;
        arr = GOAL;
        w = new BufferedWriter(new FileWriter("states.txt"));
        ss = new ArrayList<int[][]>();
        generateN();
        w.close();
    }
    
    private void generateN() throws IOException{        
        do
        {
            int[][] startState = {{1,2,3},{4,5,6},{7,8,0}};
            this.arr = randomize(startState);
            if(check(arr, ss)){
                ss.add(arr);
                w.write(toWriter(arr));
            }
        }while(ss.size()< n);
       
    }
    
    public int[][] getArr(){
        return arr;
    }
    public int[][] randomize(int[][] arr)
    {
        Random r = new Random();
        int tmp;
        for(int i = 0; i<15;i++)
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
        
        return arr;
    }
    
    public void moveUp(int[][] arr)
    {
        if(x == 0 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x-1,y,arr);
        x=x-1;

    }
    public void moveDown(int[][] arr)
    {

        if(x == 2 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x+1,y,arr);
        x=x+1;

    }
    public void moveRight(int[][] arr)
    {

        if(y == 2 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x,y+1,arr);
        y=y+1;

    }
    public void moveLeft(int[][] arr)
    {

        if(y == 0 || arr[x][y] !=0 || x>2 || y>2)
            return;
        else
            swap(x,y,x,y-1,arr);
        y=y-1;

    }
    
    public void swap(int rowa , int columna, int rowb, int columnb,int[][] arr)
    {
        int temp = arr[rowa][columna];
        arr[rowa][columna] = arr[rowb][columnb];
        arr[rowb][columnb] = temp;
    }
    
    public static int[] getRow(int[][] array, int index){
        int[] row = {0,0,0};
        for(int ii=0; ii<3; ii++){
            row[ii] = array[index][ii];
        }
        return row;
    }
    
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
    
    public String toWriter(int[][] arr){
        String result = "";
        for(int i =0; i <arr.length; i++ ){
            for(int j =0; j <arr[i].length; j++ )
                result += arr[i][j] + " ";
            result += "\n";        
        }
        return result;
    }
    
    public static void main(String args[]) throws IOException{
        PuzzleGenerator g = new PuzzleGenerator();
    }
    
}


