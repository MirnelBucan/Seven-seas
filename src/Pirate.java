
public class Pirate {
	private int row;
	private int col;
	private int direction;
  private boolean wreck;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}

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
		this.direction = dir; // TODO random generisati pravac
    this.wreck = false;
	}

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

	void changeDirection(int dir) {
		this.direction = dir;
	}
	/*
	 * Manhattan distance between two points across two-dimensional grid is just
	 * sum of theirs horizontal and vertical difference.
	 */
	private int returnDistance(int xP, int yP, int yD, int xD) {
		return Math.abs(xP - xD) + Math.abs(yP - yD);
	}

	/*
	 * New position of ghost is minimum one amongst four possible steps. If
	 * there is more than one minimum, then we randomly pick one of them.
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
      System.out.println("Distanca je: "+niz[i]+ "index je: "+i);
			if (niz[i] < min) {
				min = niz[i];
			}
		}
    System.out.println("Min: "+min);
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
  /*
   * We iterate over array of steps, until we find one that is at the same
   * time also one or only minimum distance towards the desirable point.
   *
   * The idea behind return statement is that we almost always want to move in
   * the direction where the point lies. That is done by "ran" part. In order
   * to simulate some "AI", we should enable some kind of mistake, because we
   * can't always have the right answer. (in this example, the mistake is just
   * true(1)/false(0)) But we need to protect ourselves, because that new
   * value could go right out of the bounds, so we can take the remaining of
   * that number divided by 4 (since we have just 4 possible steps, so that
   * new values always lies between 0-3).
   */
  private int randomIndex(int[] niz, int min) {
    int ran = (int) (Math.random() * 8);
    while (niz[ran] != min) {
      ran = (int) (Math.random() * 8);
    }
    return (Math.abs(ran - getRandom01())) % 8;
  }

  /*
   * "Some kind of mistake" that we already talked about, completely depends
   * on the value 0.75, since from that depends will there be a mistake or
   * not. Further more that value approaches 1, game will be harder, because
   * there will be less mistakes. In practical experiments, 0.75 proved to be
   * solid measure.
   */
  int getRandom01() {
    if (Math.random() < 0.75) {
      return 0;
    }
    return 1;
  }
}