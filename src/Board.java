public class Board {
  static final int EMPTY = 0;
  static final int ISLAND = 1;
  static final int WRECK = 2;
  static final int SHIP = 3;
  static final int PIRATES = 4;
  static final int PORTAL = 5;

  static final int dimRow = 20;
  static final int dimCol = 20;

  int[][] board;

  Board() {
    this.board = new int[dimRow][dimCol];
    //portal hardcoded to always be on top right corner
    this.board[0][dimCol-1] = this.PORTAL;
  }

  public int getDimRow() {
    return dimRow;
  }

  public int getDimCol() {
    return dimCol;
  }

  void setShip(int row, int col) {
    this.board[row][col] = this.SHIP;
  }

  void setPirate(int row, int col) {
    this.board[row][col] = this.PIRATES;
  }
  void setWreck(int row, int col) { this.board[row][col] = this.WRECK; }
  void setIsland(int row, int col) {
    this.board[row][col] = this.ISLAND;
  }

  void removeShip(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }

  void removePirate(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }
  //to check if position of pirate during initialisation is valid
  //explanation: don't put another pirate over island or another pirate
  boolean checkInitPirates(int row, int col){
    if(this.board[row][col] == 0)
      return true;
    return false;
  }
  void toConsole() {
    for (int i = 0; i < this.dimRow; i++) {
      for (int j = 0; j < this.dimCol; j++) {
        System.out.print(this.board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

}
