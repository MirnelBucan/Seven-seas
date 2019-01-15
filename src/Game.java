import java.util.Random;
public class Game {
  private int numberOfPirates = 1;
  private int numberOfIslands = 1;
  private int level = 1;
  Ship ship;
  Board board;
  Pirate[] pirates;

  public void init() {
    board = new Board();
    //player on fixed position bottom left corner
    ship = new Ship(17, 2);
    int randomRow;
    int randomCol;
    Random randomPiratePosition = new Random();
    System.out.println("Num of pirates: "+this.numberOfPirates);
    System.out.println("Num of islands: "+this.numberOfIslands);
    board.setShip(ship.getRow(), ship.getCol());
    int i = 0;
    pirates = new Pirate[this.numberOfPirates];
    while( i < this.numberOfPirates){
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
    while( i < this.numberOfIslands){
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

    /*while (true) {
      if (this.end()) break;

      moveShipManRandom();
      movePirates();
      board.toConsole();

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }*/

  }
  public void moveShipManRandom() {
    int dir = (int) (Math.random() * 4 + 1);
    this.ship.setDirection(dir);
    moveShip();
  }

  public void moveShip() {
    int pacRow = ship.getRow();
    int pacCol = ship.getCol();
    int direction = ship.getDirection();

    if (checkMove(pacRow, pacCol, direction)) {
      board.removeShip(ship.getRow(), ship.getCol());
      ship.move();
      board.setShip(ship.getRow(), ship.getCol());
    }
  }

  public void movePirates() {
    AI();

    for (int i = 0; i < this.numberOfPirates; i++) {
      int eRow = pirates[i].getRow();
      int eCol = pirates[i].getCol();
      int direction = pirates[i].getDirection();

      if (checkMove(eRow, eCol, direction)) {
        board.removePirate(pirates[i].getRow(), pirates[i].getCol());
        pirates[i].move();
        board.setPirate(pirates[i].getRow(), pirates[i].getCol());
      }
    }
  }

  private void AI() {
    for (int i = 0; i < this.numberOfPirates; i++) {
      while (true) {
        int eRow = pirates[i].getRow();
        int eCol = pirates[i].getCol();
        pirates[i].novaPozicija(this.ship.getRow(), this.ship.getCol());
        int direction = pirates[i].getDirection();

        if (checkMove(eRow, eCol, direction)) {
          break;
        }
      }
    }
  }

  boolean end() {
    for (int i = 0; i < this.numberOfPirates; i++) {
      if (this.ship.getRow() == this.pirates[i].getRow() && this.ship.getCol() == this.pirates[i].getCol()) {
        return true;
      }
    }
    return false;
  }
  boolean nextLevel() {
    //if player manages to get to portal, put him on next level
    return this.ship.getRow() == 0 && this.ship.getCol() == this.board.getDimCol() - 1;
  }

  boolean checkMove(int row, int col, int dir) {
    int dimRow = board.getDimRow();
    int dimCol = board.getDimCol();

    if (row == 0 && dir == ship.UP) {
      return false;
    }
    if (row == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if (row == 0 && dir == ship.RIGHT_UP) {
      return false;
    }
    if (col == 0 && dir == ship.LEFT) {
      return false;
    }
    if (col == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if (col == 0 && dir == ship.LEFT_DOWN) {
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


    if (dir == ship.RIGHT_UP) {
      row = row - 1;
      col = col + 1;
    }
    if (dir == ship.UP) {
      row = row - 1;
    }
    if (dir == ship.LEFT_UP) {
      row = row - 1;
      col = col - 1;
    }

    if (dir == ship.LEFT) {
      col = col - 1;
    }

    if (dir == ship.LEFT_DOWN) {
      row = row + 1;
      col = col - 1;
    }
    if (dir == ship.DOWN) {
      row = row + 1;
    }
    if (dir == ship.RIGHT_DOWN) {
      row = row + 1;
      col = col + 1;
    }

    if (dir == ship.RIGHT) {
      col = col + 1;
    }

    if (board.board[row][col] == board.ISLAND) {
      return false;
    }

    return true;
  }
  //getters & setters
  int getLevel(){ return this.level; }
  void setLevel(int lvl){ this.level = lvl; }
  void increaseObstacale(){
    this.numberOfIslands++;
    this.numberOfPirates = this.numberOfIslands+1;
  }
  void moveToNextLevel(){ this.level++; }
}
