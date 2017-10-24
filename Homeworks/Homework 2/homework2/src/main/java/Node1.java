
public class Node1 implements Comparable<Node1>
{

    int[][] state = new int[3][3];
    int manDistance;
    Node1 child1;
    Node1 child2;
    Node1 child3;
    Node1 child4;
    int EmptyX;
    int EmptyY;
    int rootMove;
    int id;
    boolean [] possibleMoves = new boolean[4];

    public Node1(int arr[][], int move, int id)
    {
        deepCopy(arr);
        this.manDistance = calculateSum();
        this.child1=null;
        this.child2=null;
        this.child3=null;
        this.child4= null;
        this.rootMove= move;
        findPossibleMovesOfState();
        this.id = id;

    }



    public int getId(){
        return id;
    }
    private void deepCopy(int arr[][])
    {



        for(int i = 0; i<3;i++)
        {
            for(int l = 0; l<3;l++)
                state[i][l] = arr[i][l];


        }




    }
    public void addChild(Node1 cur)
    {

        if(child1==null)
            child1= cur;
        else if(child2==null)
            child2=cur;
        else if (child3==null)
            child3=cur;
        else if (child4 ==null)
            child4=cur;
        else
            System.out.println("all children are full");
    }

    private void findPossibleMovesOfState()

    {

        if(EmptyX == 0 && EmptyY==0)
        {
            possibleMoves[0] = false;
            possibleMoves[1] = true;
            possibleMoves[2] = true;
            possibleMoves[3] = false;

        }
        else if(EmptyX == 0 && EmptyY==1)
        {
            possibleMoves[0] = false;
            possibleMoves[1] = true;
            possibleMoves[2] = true;
            possibleMoves[3] = true;

        }
        else if(EmptyX == 0 && EmptyY==2)
        {
            possibleMoves[0] = false;
            possibleMoves[1] = true;
            possibleMoves[2] = false;
            possibleMoves[3] = true;

        }
        else if(EmptyX == 1 && EmptyY==0)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = true;
            possibleMoves[2] = true;
            possibleMoves[3] = false;

        }
        else if(EmptyX == 1 && EmptyY==1)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = true;
            possibleMoves[2] = true;
            possibleMoves[3] = true;

        }
        else if(EmptyX == 1 && EmptyY==2)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = true;
            possibleMoves[2] = false;
            possibleMoves[3] = true;

        }
        else if(EmptyX == 2 && EmptyY==0)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = false;
            possibleMoves[2] = true;
            possibleMoves[3] = false;

        }
        else if(EmptyX == 2 && EmptyY==1)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = false;
            possibleMoves[2] = true;
            possibleMoves[3] = true;

        }
        else if(EmptyX == 2 && EmptyY==2)
        {
            possibleMoves[0] = true;
            possibleMoves[1] = false;
            possibleMoves[2] = false;
            possibleMoves[3] = true;

        }

        if(rootMove != -1)
        {
            if(rootMove == 0)
                possibleMoves[1] = false;
            else if(rootMove==1)
                possibleMoves[0] = false;
            else if(rootMove==2)
                possibleMoves[3] = false;
            else if(rootMove==3)
                possibleMoves[2] = false;
        }
    }
    private int calculateSum()
    {
        int sum = 0;

        boolean cond = true;

        for(int i = 0; i<3;i++)
        {
            for(int l = 0; l<3;l++)
            {
                sum = sum + Math.abs(i-findRow(state[i][l]))+Math.abs(l-findColumn(state[i][l]));
                if(cond && state[i][l]==0)
                {
                    this.EmptyX=i;
                    this.EmptyY=l;
                    cond =false;

                }

            }

        }



        return sum;
    }

    private int findRow(int tile)
    {
        if(tile == 0  )
            return 2;
        else if(tile == 1)
            return 0;
        else if(tile == 2)
            return 0;
        else if(tile == 3)
            return 0;
        else if(tile == 4)
            return 1;
        else if(tile == 5)
            return 1;
        else if(tile == 6)
            return 1;
        else if(tile == 7)
            return 2;
        else if(tile == 8)
            return 2;
        else
            return -1;


    }
    private int findColumn(int tile)
    {
        if(tile == 0  )
            return 2;
        else if(tile == 1)
            return 0;
        else if(tile == 2)
            return 1;
        else if(tile == 3)
            return 2;
        else if(tile == 4)
            return 0;
        else if(tile == 5)
            return 1;
        else if(tile == 6)
            return 2;
        else if(tile == 7)
            return 0;
        else if(tile == 8)
            return 1;
        else
            return -1;


    }

    public void print()

    {


        for(int i = 0; i<3;i++)
        {
            for(int l = 0; l<3;l++)

                System.out.print(state[i][l]);

            System.out.println();
        }
        System.out.println();


    }
    public int[][] getState() {
        return state;
    }


    public void setState(int[][] state) {
        this.state = state;
    }


    public int getManDistance() {
        return manDistance;
    }


    public void setManDistance(int manDistance) {
        this.manDistance = manDistance;
    }


    public Node1 getChild1() {
        return child1;
    }


    public void setChild1(Node1 child1) {
        this.child1 = child1;
    }


    public Node1 getChild2() {
        return child2;
    }


    public void setChild2(Node1 child2) {
        this.child2 = child2;
    }


    public Node1 getChild3() {
        return child3;
    }


    public void setChild3(Node1 child3) {
        this.child3 = child3;
    }


    public Node1 getChild4() {
        return child4;
    }


    public void setChild4(Node1 child4) {
        this.child4 = child4;
    }





    public int compareTo(Node1 o) {
        // TODO Auto-generated method stub

        if(this.manDistance > o.manDistance)
            return 1;
        else if(this.manDistance == o.manDistance)
            return 0;
        else
            return -1;
    }








}
