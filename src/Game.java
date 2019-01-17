import java.util.Random;
public class Game {
  private int numOfPirates = 0;
  private int numOfIslands = 5;
  private int level = 1;
  private boolean gameOver = false;
  Ship ship;
  Board board;
  Pirate[] pirates;

  //getters & setters
  int getLevel(){ return this.level; }
  void setLevel(int lvl){ this.level = lvl; }
  void restart(){
    this.level = 1;
    this.numOfIslands = 1;
    this.numOfPirates = 1;

  }
  void moveToNextLevel(){
    this.level++;
    this.numOfIslands++;
    this.numOfPirates = this.numOfIslands+1;
  }

  public void init() {
    board = new Board();
    //player on fixed position bottom left corner
    ship = new Ship(17, 2);
    int randomRow;
    int randomCol;
    Random randomPiratePosition = new Random();
    System.out.println("Num of pirates: "+this.numOfPirates);
    System.out.println("Num of islands: "+this.numOfIslands);
    board.setShip(ship.getRow(), ship.getCol());
    int i = 0;
    pirates = new Pirate[this.numOfPirates];
    while( i < this.numOfPirates){
      //Random pirate position row and to be atleast 5 places away from player
      randomRow = randomPiratePosition.nextInt(12);
      //Random pirate position col and to be atleast 5 places away from player
      randomCol = randomPiratePosition.nextInt(15)+5;
      if(board.checkInitPirates(randomRow, randomCol)) {
        //TODO: remove this "console.log"
        System.out.println("ROw: "+ randomRow +" Col: "+ randomCol);
        pirates[i] = new Pirate(randomRow, randomCol, Pirate.UP);    // TODO Postavi duhove na OK pozicije
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
        //TODO: remove this "console.log"
        System.out.println("ROw: "+ randomRow +" Col: "+ randomCol);
        board.setIsland(randomRow, randomCol);
        i++;
      }
    }
    System.out.println("Initializing game");
    board.toConsole();

  }
  public void moveShipMRandom() {
    int dir = (int) (Math.random() * 4 + 1);
    this.ship.setDirection(dir);
    moveShip();
  }

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

  public void movePirates() {

    AI();
    for (int i = 0; i < this.numOfPirates; i++) {
      board.removePirate(pirates[i].getRow(), pirates[i].getCol());
      if(pirates[i].getDestroyed() == false){
        pirates[i].move();
        if( board.setPirate(pirates[i].getRow(), pirates[i].getCol()) ){
          pirates[i].setDestroyed(true);
          if(board.board[this.ship.getRow()][this.ship.getCol()] != this.board.SHIP)
            this.gameOver = true;
        }
      }
    }
  }
  private void AI() {
    for (int i = 0; i < this.numOfPirates; i++) {
        pirates[i].newPosition(this.ship.getCol(), this.ship.getRow());
    }
  }

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
  boolean nextLevel() {
    //if player manages to get to portal, put him on next level
    return this.ship.getRow() == 0 && this.ship.getCol() == this.board.getDimCol() - 1;
  }
}