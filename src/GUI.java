//import java.awt.Color;
//import java.awt.GridLayout;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class GUI {
//	static JFrame jf;
//	static JPanel jp;
//	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Game game = new Game();
//		game.init();
//		int rowNum = game.board.getDimRow();
//		int colNum = game.board.getDimCol();
//
//		jf = new JFrame("PACMAN");
//		jf.setSize(500, 500);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		jp = new JPanel();
//		jp.setLayout(new GridLayout(rowNum, colNum));
//		jp.setBackground(Color.BLUE);
//		
//		JButton[][] jb = new JButton[rowNum][colNum];
//		for (int i=0; i<rowNum; i++) {
//			for (int j=0; j<colNum; j++) {
//				jb[i][j] = new JButton(i + " " + j);
//				jb[i][j].setSize(30, 30);
//				jb[i][j].setEnabled(false);
//
//				int v = game.board.board[i][j];
//				Color c;
//				
//				if (v == game.board.SHIP) {
//					c = Color.YELLOW;
//				} else if (v == Board.PIRATES) {
//					c = Color.BLUE;	
//				} else if (v == game.board.ISLAND) {
//					c = Color.GRAY;	
//				} else {
//					c = Color.BLACK;		
//				}
//		
//				jb[i][j].setBackground(c);
//				jp.add(jb[i][j]);
//			}
//		}
//		
//		jf.add(jp);
//		jf.setResizable(false);
//		jf.setVisible(true);
//		
//		KeyListener kl = new KeyListener() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				if (e.getKeyCode() == 49) {
//					game.ship.changeDirection(game.ship.LEFT_UP);
//					System.out.println("IDEMO GORE-LIJEVO");
//				}
//				
//				if (e.getKeyCode() == 50) {
//					game.ship.changeDirection(game.ship.UP);
//					System.out.println("IDEMO GORE");
//				}
//				if (e.getKeyCode() == 51) {
//					game.ship.changeDirection(game.ship.RIGHT_UP);
//					System.out.println("IDEMO GORE-DESNO");
//				}
//
//				if (e.getKeyCode() == 54) {
//					game.ship.changeDirection(game.ship.RIGHT);
//					System.out.println("IDEMO DESNO");
//				}
//				if (e.getKeyCode() == 57) {
//					game.ship.changeDirection(game.ship.RIGHT_DOWN);
//					System.out.println("IDEMO DOLJE- DESNO");
//				}
//
//				if (e.getKeyCode() == 56) {
//					game.ship.changeDirection(game.ship.DOWN);
//					System.out.println("IDEMO DOLJE");
//				}
//				if (e.getKeyCode() == 55) {
//					game.ship.changeDirection(game.ship.LEFT_DOWN);
//					System.out.println("IDEMO DOLJE-LIJEVO");
//				}
//				if (e.getKeyCode() == 52) {
//					game.ship.changeDirection(game.ship.LEFT);
//					System.out.println("IDEMO LIJEVO");
//				}
//				
//				game.board.toConsole();
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}			
//		};
//		
//		jf.addKeyListener(kl);
//		while (true) {
//			if (game.end()) break;
//
//			game.moveShip();
//			game.movePirates();
//			
//			for (int i=0; i<rowNum; i++) {
//				for (int j=0; j<colNum; j++) {
//					int v = game.board.board[i][j];
//					Color c;
//					
//					if (v == game.board.SHIP) {
//						c = Color.YELLOW;
//					} else if (v == game.board.PIRATES) {
//						c = Color.BLUE;	
//					} else if (v == game.board.ISLAND) {
//						c = Color.GRAY;	
//					} else {
//						c = Color.BLACK;		
//					}
//			
//					jb[i][j].setBackground(c);
//				}
//			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//		}
//	}
//
//}
