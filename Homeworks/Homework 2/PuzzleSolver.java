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
		
		
		for(int u=0; u<5; u++)
		{
			int[][] startState = {{1,2,3},{4,5,6},{7,8,0}};
			randomize(startState, gen);
			if(check(startState, ss))
				ss.add(startState);		
		}
		
		
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
	
	static boolean check(int[][] arr, ArrayList<int[][]> arrList)
	{
		int [][] tmparr;
		for(int i = 0 ; i < arrList.size() ; i++)
		{
			tmparr = arrList.get(i);
			if((Arrays.equals(arr,tmparr)))
				return false;
		}
		return true;
	}
}
