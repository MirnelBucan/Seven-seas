
public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Game game = new Game();
    while(game.getLevel() < 6) {
      System.out.println("Level: "+game.getLevel());
      game.init();
      game.moveToNextLevel();
      game.increaseObstacale();
    }
/*    game.movePirates();

    game.moveShip();
    game.movePirates();

    game.moveShip();
    game.movePirates();

    game.moveShipManRandom();
    game.movePirates();*/
  }
}
