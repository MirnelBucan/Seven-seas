
import java.util.Random;
/**
 * Public class Game represents everything that this game should have,
 * including board (on which we move our pieces ), multiple pirates ( represent environment to play against) and player.
 * @author Mirnel Bucan
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 */
public class Game {
  /** Number of pirates on each level*/
  private int numOfPirates = 1;
  /** Number of islands on each level*/
  private int numOfIslands = 2;
  /** Current level of game*/
  private int level = 1;
  /** Represents state of game if it ended (player lost) or not */
  private boolean gameOver = false;
  /** Represents player */
  Ship ship;
  /** Represents a board on which we play */
  Board board;
  /** Represents multiple enemies on board */
  Pirate[] pirates;

  /**
   * Getter for level
   * @return int Current level of the game
   */
  int getLevel(){ return this.level; }

  /**
   * Setter for gameOver
   * @param gameOver Represents current state of game
   */
  void setGameOver(boolean gameOver) { this.gameOver = gameOver; }

  /**
   * Function init() is used to initialize board and pieces on it.
   * It randomly puts pirates and islands , while player is fixed with his starting position
   * pirates and islands must be atleast 5 places away from player.
   */
  public void init() {
    board = new Board();
    //player on fixed position bottom left corner
    ship = new Ship(17, 2);
    int randomRow;
    int randomCol;
    Random randomPiratePosition = new Random();
    board.setShip(ship.getRow(), ship.getCol());
    int i = 0;
    pirates = new Pirate[this.numOfPirates];
    while( i < this.numOfPirates){
      //Random pirate position row and to be atleast 5 places away from player
      randomRow = randomPiratePosition.nextInt(12);
      //Random pirate position col and to be atleast 5 places away from player
      randomCol = randomPiratePosition.nextInt(15)+5;
      if(board.checkInitPirates(randomRow, randomCol)) {
        pirates[i] = new Pirate(randomRow, randomCol, Pirate.UP);
        board.setPirate(randomRow, randomCol);
        i++;
      }
    }
    i=0;
    while( i < this.numOfIslands){
      //Random island position row and to be atleast 5 places away from player
      randomRow = randomPiratePosition.nextInt(12);
      //Random island position col and to be atleast 5 places away from player
      randomCol = randomPiratePosition.nextInt(15)+5;
      if(board.checkInitPirates(randomRow, randomCol)) {
        board.setIsland(randomRow, randomCol);
        i++;
      }
    }
    System.out.println("Initializing game");
    board.toConsole();

  }

  /**
   * This function is used to move player on the board.
   * It prevents player from moving out of array bounds.
   * it also checks for player collision with env.
   */
  public void moveShip() {
    int direction = ship.getDirection();

    if (checkMove(ship.getRow(), ship.getCol(), direction)) {
      board.removeShip(ship.getRow(), ship.getCol());
      ship.move();
      if (board.setShip(ship.getRow(), ship.getCol()) == true) {
        this.gameOver = true;
      }
      System.out.println("Game over: "+this.gameOver);
    }
  }

  /**
   * This function is used to move pirate on board.
   * It also detects collision with env, and separates collision with player
   * and other obstacles to be able to detects end of game if pirate manages to
   * destroys player.
   */
  public void movePirates() {

    AI();
    for (int i = 0; i < this.numOfPirates; i++) {
      if(pirates[i].getDestroyed() == false){
        board.removePirate(pirates[i].getRow(), pirates[i].getCol());
        pirates[i].move();
        if( board.setPirate(pirates[i].getRow(), pirates[i].getCol()) ){
          pirates[i].setDestroyed(true);
          if(board.board[this.ship.getRow()][this.ship.getCol()] != this.board.SHIP)
            this.gameOver = true;
        }
      }
    }
  }

  /**
   * This function is used for making pirates movements.
   * Represents basic AI, works on distance between player and pirate,
   * than decides which direction to move.
   */
  private void AI() {
    for (int i = 0; i < this.numOfPirates; i++) {
        pirates[i].newPosition(this.ship.getCol(), this.ship.getRow());
    }
  }

  /**
   * This function is used to prevent player from moving out of bounds of map
   * @param row Represents in which row player is currently on
   * @param col Represents in which column player is currently on
   * @param dir Represents in which direction player is attempting to move
   * @return True if players move is allowed, false if not.
   */
  boolean checkMove(int row, int col, int dir) {
    int dimRow = board.getDimRow();
    int dimCol = board.getDimCol();

    if (row == 0 && dir == ship.UP) {
      return false;
    }
    if(row == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if(row == 0 && dir == ship.RIGHT_UP) {
      return false;
    }
    if (col == 0 && dir == ship.LEFT) {
      return false;
    }
    if(col == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if(col == 0 && dir == ship.LEFT_DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.LEFT_DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.RIGHT_DOWN) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT_UP) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT_DOWN) {
      return false;
    }
    return true;
  }

  boolean end() {
      if (this.gameOver == true)
        return true;
    return false;
  }

  /**
   * This function determines if player managed to get to portal
   * @return Returns true if player gets to portal, false otherwise.
   */
  boolean nextLevel() {
    //if player manages to get to portal, put him on next level
    return this.ship.getRow() == 0 && this.ship.getCol() == this.board.getDimCol() - 1;
  }

  /**
   * This function is used to return initial game state.
   */
  void restart(){
    this.level = 1;
    this.numOfIslands = 1;
    this.numOfPirates = 1;

  }

  /**
   * This function is used to simulate next level of difficulty by
   * increasing number of obstacles
   */
  void moveToNextLevel(){
    this.level++;
    this.numOfIslands++;
    this.numOfPirates = this.numOfIslands+1;
  }
}