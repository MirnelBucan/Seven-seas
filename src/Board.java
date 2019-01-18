/**
* Public class Board represents our field on which pieces are moved.
* Board is 2D integer array, where each integer is mapped for different piece.
* It's used to store position of each piece for possibility of tracking their
* movements across the board and registering when different events occur.
* Events such as pirate and island collision, pirate and player , etc..
* @author Mirnel Bucan
* @version 1.0.0 (alfa)
* @since 18/1/2019
 */

public class Board {
  /** EMPTY is a constant representing empty field on board */
  static final int EMPTY = 0;
  /** ISLAND is a constant representing island on board */
  static final int ISLAND = 1;
  /** WRECk is a constant representing when 2 pirates collide field on board */
  static final int WRECK = 2;
  /** SHIP is a constant representing player on board */
  static final int SHIP = 3;
  /** PIRATE is a constant representing pirate on board */
  static final int PIRATES = 4;
  /** PORTAL is a constant representing "passage to next level of game" on board */
  static final int PORTAL = 5;
  /** dimRow is number of rows for board */
  static final int dimRow = 20;
  /** dimCol is number of columns for board */
  static final int dimCol = 20;
  /** int[][]board is our matrix for storing information about position of each piece */
  int[][] board;

  Board() {
    this.board = new int[dimRow][dimCol];
    //portal hardcoded to always be on top right corner
    this.board[0][dimCol-1] = this.PORTAL;
  }
  /** Getter for board rows
   * @return int Number of board rows
   */
  public int getDimRow() {
    return dimRow;
  }
  /** Getter for bouard columns
   * @return int Number of board columns
   * */
  public int getDimCol() {
    return dimCol;
  }
  /** Function for placing player on board
   * @param col Represents on which column to place player
   * @param row Represents on which row to place player
   * @return boolean Returns false if player isn't colliding with anything on board,
   * true if player collides with anything on board
   */
  boolean setShip(int row, int col) {
    if(this.board[row][col] == this.WRECK ||
      this.board[row][col] == this.ISLAND ||
      this.board[row][col] == this.PIRATES){
      this.board[row][col] = this.EMPTY;
      return true;
    }
    this.board[row][col] = this.SHIP;
    return false;
  }
  /** Function for placing pirate/computer on board
   * @param col Represents on which column to place player
   * @param row Represents on which row to place player
   * @return boolean Returns false if pirate/computer isn't colliding with anything on board,
   * true if pirate/player collides with anything on board
   */
  boolean setPirate(int row, int col) {
    if(this.board[row][col] == this.ISLAND){
      this.board[row][col] = this.EMPTY;
      return true;
    } else if (this.board[row][col] == this.PIRATES ||
            this.board[row][col] == this.WRECK){
      this.board[row][col] = this.WRECK;
      return true;
    } else if (this.board[row][col] == this.SHIP){
      this.board[row][col] = this.WRECK;
      return true;
    }
    this.board[row][col] = this.PIRATES;
    return false;
  }
  /**
   * Setter for placing player on board
   * @param col Represents on which column to place player
   * @param row Represents on which row to place player
   */
  void setIsland(int row, int col) {
    this.board[row][col] = this.ISLAND;
  }

  /**
   * This function is used to remove ship from board
   * @param row Represents in which row is player currently placed
   * @param col Represents in which column is player currently placed
   */
  void removeShip(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }

  /**
   * This function is used to remove ship from board
   * @param row Represents in which row is pirate/computer currently placed
   * @param col Represents in which column is pirate/computer currently placed
   */
  void removePirate(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }
  /**
   * This function is used for initialization phase, to make sure we
   * don't place pirate over pirate, pirate over island, etc...
   * @param row Represents row in which we are checking
   * @param col Represents column in which we are checking
   * @return boolean Returns true if that field is empty, false if anything else
   */
  boolean checkInitPirates(int row, int col){
    if(this.board[row][col] == this.EMPTY)
      return true;
    return false;
  }

  /**
   * This function is used to print current board state to console
   */
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
