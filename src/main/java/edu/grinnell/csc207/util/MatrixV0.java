package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Mina Bakrac
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the matrix.
   */
  int size;

  /**
   * The matrix (represented as a 2D array).
   */
  T[][] values;

  /**
   * Default value to fill matrix with.
   */
  T defaulVal;

  /**
   * The width of the matrix.
   */
  int width;

  /**
   * The height of the matrix.
   */
  int height;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.width = width;
    this.height = height;
    this.defaulVal = def;
    this.size = width * height;
    this.values = (T[][]) new Object[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.values[i][j] = def;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return this.values[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    this.values[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if ((row >= 0) && (row <= this.height())) {
      T[][] newMatrix = (T[][]) new Object[this.height + 1][this.width];
      for (int i = 0; i < (this.height + 1); i++) {
        for (int j = 0; j < this.width; j++) {
          if (i == row) {
            newMatrix[i][j] = defaulVal;
          } else if (i < row) {
            newMatrix[i][j] = this.values[i][j];
          } else if (i > row) {
            newMatrix[i][j] = this.values[i - 1][j];
          } // if/else
        } // for
      } // for
      this.values = newMatrix;
      this.height = height + 1;
      this.size = width * height;
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds.");
    } // if/else
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if ((row >= 0) && (row <= this.height())) {
      if (this.width() == vals.length) {
        T[][] newMatrix = (T[][]) new Object[this.height + 1][this.width];
        for (int i = 0; i < (this.height + 1); i++) {
          for (int j = 0; j < this.width; j++) {
            if (i == row) {
              newMatrix[i][j] = vals[j];
            } else if (i < row) {
              newMatrix[i][j] = this.values[i][j];
            } else if (i > row) {
              newMatrix[i][j] = this.values[i - 1][j];
            } // if/else
          } // for
        } // for
        this.values = newMatrix;
        this.height = height + 1;
        this.size = width * height;
      } else {
        throw new ArraySizeException("Error: Values are the wrong length");
      } // if/else
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds.");
    } // if/else
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if ((col >= 0) && (col <= this.width())) {
      T[][] newMatrix = (T[][]) new Object[this.height][this.width + 1];
      for (int i = 0; i < (this.width + 1); i++) {
        for (int j = 0; j < this.height; j++) {
          if (i == col) {
            newMatrix[j][i] = defaulVal;
          } else if (i < col) {
            newMatrix[j][i] = this.values[j][i];
          } else if (i > col) {
            newMatrix[j][i] = this.values[j][i - 1];
          } // if/else
        } // for
      } // for
      this.values = newMatrix;
      this.width = width + 1;
      this.size = width * height;
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds.");
    } // if/else
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if ((col >= 0) && (col <= this.width())) {
      if (this.height() == vals.length) {
        T[][] newMatrix = (T[][]) new Object[this.height][this.width + 1];
        for (int i = 0; i < (this.width + 1); i++) {
          for (int j = 0; j < this.height; j++) {
            if (i == col) {
              newMatrix[j][i] = vals[j];
            } else if (i < col) {
              newMatrix[j][i] = this.values[j][i];
            } else if (i > col) {
              newMatrix[j][i] = this.values[j][i - 1];
            } // if/else
          } // for
        } // for
        this.values = newMatrix;
        this.width = width + 1;
        this.size = width * height;
      } else {
        throw new ArraySizeException("Error: Values are the wrong length");
      } // if/else
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds.");
    } // if/else
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if ((row >= 0) && (row < this.height)) {
      for (int j = row; j < this.height - 1; j++) {
        for (int i = 0; i < this.width; i++) {
          this.values[j][i] = this.values[j + 1][i];
        } // for
      } // for
      this.height--;
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds");
    } // if/else
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if ((col >= 0) && (col < this.width)) {
      for (int j = col; j < this.width - 1; j++) {
        for (int i = 0; i < this.height; i++) {
          this.values[i][j] = this.values[i][j + 1];
        } // for
      } // for
      this.width--;
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds");
    } // if/else
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        this.values[i][j] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (((startRow >= 0) && (endRow <= this.height))
        && ((startCol >= 0) && (endCol <= this.width))) {
      int i = startRow;
      int j = startCol;
      while ((i < endRow) && (j < endCol)) {
        this.values[i][j] = val;
        i = (i + deltaRow);
        j = (j + deltaCol);
      } // while
    } else {
      throw new IndexOutOfBoundsException("Error: Index out of bounds");
    } // if/else
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    return this;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    return ((other instanceof MatrixV0) && (this.equals((MatrixV0) other)));
  } // equals(Object)

  /**
   * Determine if this matrix is equal to another matrix.
   *
   * @param other
   *    The matrix to compare.
   *
   * @return true if the other matrix is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(MatrixV0 other) {
    if ((this.width() != other.width()) || (this.height() != other.height())) {
      return false;
    } else {
      for (int i = 0; i < this.height(); i++) {
        for (int j = 0; j < this.width(); j++) {
          if (!this.values[i][j].equals(other.values[i][j])) {
            return false;
          } // if
        } // for
      } // for
      return true;
    } // else
  } // equals(MatrixV0<T>)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
