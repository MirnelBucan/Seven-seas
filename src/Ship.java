
public class Ship {
  private int row;
  private int col;
  private int direction;
  static final int LEFT_UP = 7;
  static final int DOWN = 2;
  static final int RIGHT_UP = 9;
  static final int LEFT = 4;
  static final int RIGHT = 6;
  static final int LEFT_DOWN = 1;
  static final int UP = 8;
  static final int RIGHT_DOWN = 3;

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

  Ship(int row, int col) {
    this.row = row;
    this.col = col;
    this.direction = 0; // TODO random generisati pravac
  }

  void move() {
    if (this.direction == this.LEFT_UP) {
      this.row--;
      this.col--;
    }
    if (this.direction == this.UP) {
      this.row--;
    }
    if (this.direction == this.RIGHT_UP) {
      this.row--;
      this.col++;
    }
    if (this.direction == this.RIGHT) {
      this.col++;
    }
    if (this.direction == this.LEFT) {
      this.col--;
    }
    if (this.direction == this.LEFT_DOWN) {
      this.row++;
      this.col--;
    }
    if (this.direction == this.DOWN) {
      this.row++;
    }
    if (this.direction == this.RIGHT_DOWN) {
      this.row++;
      this.col++;
    }
  }

  void changeDirection(int dir) {
    this.direction = dir;
  }

}
