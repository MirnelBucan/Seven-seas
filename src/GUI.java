import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * Public class GUI ( Graphical User Interface ) is used to generate interface for players to use.
 * Interface is generated in windowed mode, simple panel and array of buttons with listener attached to them.
 * Uses multiple keys for movement, including numpad keys and qwerty keys.
 * @author Mirnel Bucan
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 */
public class GUI {
  static JFrame jf;
  static JPanel jp;

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("We are here");
    Game game = new Game();
    game.init();
    int rowNum = game.board.getDimRow();
    int colNum = game.board.getDimCol();

    jf = new JFrame("SEVEN SEAS");
    jf.setSize(500, 500);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jp = new JPanel();
    jp.setLayout(new GridLayout(rowNum, colNum));
    jp.setBackground(Color.BLUE);

    JButton[][] jb = new JButton[rowNum][colNum];
    for (int i=0; i<rowNum; i++) {
      for (int j=0; j<colNum; j++) {
        jb[i][j] = new JButton();
        jb[i][j].setSize(30, 30);
        jb[i][j].setEnabled(false);

        int v = game.board.board[i][j];
        Color c;

        if (v == game.board.SHIP) {
          c = Color.YELLOW;
        } else if (v == game.board.PORTAL) {
          //TODO: FIX this
          c = Color.RED;
        } else if (v == game.board.PIRATES) {
          c = Color.BLACK;
        } else if (v == game.board.ISLAND) {
          c = Color.GREEN;
        }  else {
          c = Color.BLUE;
        }

        jb[i][j].setBackground(c);
        jp.add(jb[i][j]);
      }
    }
    jf.add(jp);
    jf.setResizable(false);
    jf.setVisible(true);

    KeyListener kl = new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == 49 || e.getKeyCode() == 97 || e.getKeyCode() == 89) {
          game.ship.changeDirection(game.ship.LEFT_DOWN);
          System.out.println("IDEMO GORE-LIJEVO");
        }

        if (e.getKeyCode() == 55 || e.getKeyCode() == 103 || e.getKeyCode() ==81) {
          game.ship.changeDirection(game.ship.LEFT_UP);
          System.out.println("IDEMO GORE-LIJEVO");
        }

        if (e.getKeyCode() == 56 || e.getKeyCode() == 104 || e.getKeyCode() == 87) {
          game.ship.changeDirection(game.ship.UP);
          System.out.println("IDEMO GORE");
        }
        if (e.getKeyCode() == 57 || e.getKeyCode() == 105 || e.getKeyCode() == 69) {
          game.ship.changeDirection(game.ship.RIGHT_UP);
          System.out.println("IDEMO GORE-DESNO");
        }

        if (e.getKeyCode() == 54 || e.getKeyCode() == 102 || e.getKeyCode() == 68) {
          game.ship.changeDirection(game.ship.RIGHT);
          System.out.println("IDEMO DESNO");
        }
        if (e.getKeyCode() == 51 || e.getKeyCode() == 99 || e.getKeyCode() == 67) {
          game.ship.changeDirection(game.ship.RIGHT_DOWN );
          System.out.println("IDEMO DOLJE- DESNO");
        }

        if (e.getKeyCode() == 50 || e.getKeyCode() == 98 || e.getKeyCode() == 83) {
          game.ship.changeDirection(game.ship.DOWN);
          System.out.println("IDEMO DOLJE");
        }
        if (e.getKeyCode() == 52 || e.getKeyCode() == 100 || e.getKeyCode() == 65) {
          game.ship.changeDirection(game.ship.LEFT);
          System.out.println("IDEMO LIJEVO");
        }
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
      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

      }
      @Override
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
      }
    };

    jf.addKeyListener(kl);

    while (game.getLevel() < 10) {
      if (game.end()) {
          game.restart();
          game.setGameOver(false);
          game.init();
        JOptionPane.showMessageDialog(jf, "Play again?");


      }
      if(game.nextLevel()){
        System.out.println("Newwww one");
        game.moveToNextLevel();
        game.init();
      }
      for (int i=0; i<rowNum; i++) {
        for (int j=0; j<colNum; j++) {
          int v = game.board.board[i][j];
          Color c;

          if (v == game.board.SHIP) {
            c = Color.YELLOW;
          } else if (v == game.board.PIRATES) {
            c = Color.BLACK;
          } else if (v == game.board.PORTAL) {
            c = Color.RED;
          }
          else if (v == game.board.ISLAND) {
            c = Color.GREEN;
          } else {
            c = Color.getColor(null, new Color(1,149, 203));
          }

          jb[i][j].setBackground(c);
        }
      }
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

}