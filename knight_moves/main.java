class Solution{
	public static void main(String[] args){
    int[][] mat = {{1, 1, 1, 0},
                   {0, 1, 1, 1},
                   {1, 1, 0, 1},
                   {1, 1, 1, 1}};
    int x = 1;
    int y = 0;
    System.out.println(knightMovesCount(x, y, mat));
  }
  
  public static int knightMovesCount(int x, int y, int[][] mat){
    int count = 0;
    List<Integer[]> moves = new ArrayList<Integer[]>();
    if(y-2 >= 0){
      if(x-1 >= 0){moves.add(new Integer[]{y-2, x-1});}
      if(x+1 < mat[0].length){moves.add(new Integer[]{y-2, x+1});}
    }
    if(y+2 < mat.length){
      if(x-1 >= 0){moves.add(new Integer[]{y+2, x-1});}
      if(x+1 < mat[0].length){moves.add(new Integer[]{y+2, x+1});}
    }
    if(x-2 >= 0){
      if(y-1 >= 0){moves.add(new Integer[]{y-1, x-2});}
      if(y+1 < mat.length){moves.add(new Integer[]{y+1, x-2});}
    }
    if(x+2 < mat[0].length){
      if(y-1 >= 0){moves.add(new Integer[]{y-1, x+2});}
      if(y+1 < mat.length){moves.add(new Integer[]{y+1, x+2});}
    }
    for(Integer[] pos : moves){
      if(mat[pos[0]][pos[1]] == 0){count++;}
    }
    return count;
  }
}