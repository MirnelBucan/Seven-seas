/**
 * Public class Pirate represents enemies in the game.
 * It stores information of position of enemy on board,
 * direction in which to move and if enemy has bin destroyed.
 * @author Mirnel Bucan
 * @version 1.0.0 (alfa)
 * @since 18/1/2019
 */
public class Pirate {
	private int row;
	private int col;
	private int direction;
  private boolean destroyed;

  static final int UP = 8;
  static final int RIGHT_UP = 9;
  static final int RIGHT = 6;
  static final int RIGHT_DOWN = 3;
  static final int DOWN = 2;
  static final int LEFT_DOWN = 1;
  static final int LEFT = 4;
  static final int LEFT_UP = 7;

  Pirate(int row, int col, int dir) {
    this.row = row;
    this.col = col;
    this.direction = dir;
    this.destroyed = false;
  }

  /**
   * Getter for row
   * @return int Returns current row in which pirate is.
   */
	public int getRow() {
		return row;
	}

  /**
   * Getter for column
   * @return int Returns current column in which pirate is.
   */
	public int getCol() {
		return col;
	}

  /**
   * Setter for destroyed
   * @param destroyed Represent if pirate has been destroyed during the move it made.
   */
  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }

  /**
   * Getter for destroyed
   * @return boolean Returns in which state is pirate (destroyed or not)
   */
  public boolean getDestroyed() { return this.destroyed; }

  /**
   * This function is used to change state of pirates position.
   */
	void move() {
		if(this.direction == this.LEFT_UP) {
			this.row--;
			this.col--;
		}
		if (this.direction == this.UP) {
			this.row --;
		}
		if(this.direction == this.RIGHT_UP) {
			this.row--;
			this.col++;
		}
		if (this.direction == this.RIGHT) {
			this.col ++;
		}
		if (this.direction == this.LEFT) {
			this.col --;
		}
		if(this.direction == this.LEFT_DOWN) {
			this.row++;
			this.col--;
		}
		if (this.direction == this.DOWN) {
			this.row ++;
		}
		if(this.direction == this.RIGHT_DOWN) {
			this.row++;
			this.col++;
		}
	}

  /**
   * Manhattan distance between two points across two-dimensional grid is just
   * sum of theirs horizontal and vertical difference.
   * @param xP Current player column position (or looked in 2D coordinated system , x coordinate)
   * @param yP Current player row position (or looked in 2D coordinated system , y coordinate)
   * @param yD Current pirate row position (or looked in 2D coordinated system , y coordinate)
   * @param xD Current pirate column position (or looked in 2D coordinated system , x coordinate)
   * @return int Manhattan distance of player and pirate
   */
	private int returnDistance(int xP, int yP, int yD, int xD) {
		return Math.abs(xP - xD) + Math.abs(yP - yD);
	}

  /**
   * New position of pirate is minimum one amongst 8 possible steps. If
   * there is more than one minimum, then we randomly pick one of them.
   * @param x1 Represents current row of player on board.
   * @param y1 Represents current column of player on board.
   */
	void newPosition(int x1, int y1) {
		int[] niz = new int[8]; // up, right, down, left
		niz[0] = returnDistance(x1, y1, this.row - 1, this.col);
		niz[1] = returnDistance(x1, y1, this.row-1, this.col + 1);
		niz[2] = returnDistance(x1, y1, this.row, this.col+1);
		niz[3] = returnDistance(x1, y1, this.row+1, this.col + 1);
		niz[4] = returnDistance(x1, y1, this.row+1, this.col);
		niz[5] = returnDistance(x1, y1, this.row+1, this.col - 1);
		niz[6] = returnDistance(x1, y1, this.row, this.col - 1);
		niz[7] = returnDistance(x1, y1, this.row-1, this.col - 1);
		int min = niz[0];

		for (int i = 0; i < 8; i++) {
			if (niz[i] < min) {
				min = niz[i];
			}
		}
    int random = randomIndex(niz, min);
		if (random == 0) {
			this.direction = this.UP;
		} else if (random == 1) {
			this.direction = this.RIGHT_UP;
		} else if (random == 2) {
			this.direction = this.RIGHT;
		} else if(random == 3) {
			this.direction = this.RIGHT_DOWN;
		} else if(random == 4) {
			this.direction = this.DOWN;
		} else if(random == 5) {
			this.direction = this.LEFT_DOWN;
		} else if(random == 6) {
			this.direction = this.LEFT;
		} else {
			this.direction = this.LEFT_UP;
		}
	}

  /**
   * This function is used to simulate possibility of AI mistake.
   * Since it would be almost impossible to win game if computer is constantly
   * anticipating players every move without mistake.
   * @param niz Is array possible moves to chose from
   * @param min Is minimal distance between player and pirate
   * @return int Returns "choice" made randomly to move
   */
  private int randomIndex(int[] niz, int min) {
    int ran = (int) (Math.random() * 8);
    while (niz[ran] != min) {
      ran = (int) (Math.random() * 8);
    }
    return (Math.abs(ran - getRandom01())) % 8;
  }

  /**
   * This function is used to simulate that mistake, 25% chance to make wrong move.
   * @return int Returns 0 if there is no mistake, 1 if mistake occurs .
   */
  int getRandom01() {
    if (Math.random() < 0.75) {
      return 0;
    }
    return 1;
  }
}