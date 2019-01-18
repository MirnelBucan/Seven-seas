import java.util.Scanner;
public class Main {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Game game = new Game();
    Scanner input = new Scanner(System.in);
    String in;
    game.init();
    System.out.println("Level: "+game.getLevel());
    while(game.getLevel() < 6) {
      if(game.end()){
        System.out.println("Game over!");
        while(true){
          System.out.println("To continue/exit enter (Y/N): ");
          in = input.nextLine();
          if(in.equals("Y")){
            game.init();
            break;
          } else if( in.equals("N")){
            System.exit(1);
          } else {
            System.out.println("You entered invalid key! Try again!");
          }
        }
      } else if( game.nextLevel()) {
        game.moveToNextLevel();
        game.init();
      }
      System.out.println("Enter your next move: ");
      in = input.nextLine();
      if(in.equals("W"))
        game.ship.changeDirection(game.ship.UP);
      else if(in.equals("E"))
        game.ship.changeDirection(game.ship.RIGHT_UP);
      else if(in.equals("D"))
        game.ship.changeDirection(game.ship.RIGHT);
      else if(in.equals("C"))
        game.ship.changeDirection(game.ship.RIGHT_DOWN);
      else if(in.equals("S"))
        game.ship.changeDirection(game.ship.DOWN);
      else if(in.equals("Y"))
        game.ship.changeDirection(game.ship.LEFT_DOWN);
      else if(in.equals("A"))
        game.ship.changeDirection(game.ship.LEFT);
      else if(in.equals("Q"))
        game.ship.changeDirection(game.ship.LEFT_UP);
      game.moveShip();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      game.movePirates();
      game.board.toConsole();
    }
  }
}
