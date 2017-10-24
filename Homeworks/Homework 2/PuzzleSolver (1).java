import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 */

/**
 * @author Irem
 *
 */
public class PuzzleSolver {

	static int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};
	static int x = 2;
	static int y = 2;
	static int tmp;
	
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
		for(int i = 0; i<30;i++)
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