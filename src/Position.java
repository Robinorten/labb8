// A class representing a position in the game

public class Position {
    public int row, col;
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        String pos = (row) + "," + col;
        return pos;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Position that = (Position) obj;
        if (this.row != that.row) return false;
        if (this.col != that.col) return  false;
        if (this.row == that.row && this.col == that.col) return true;
        return true;
    }
}