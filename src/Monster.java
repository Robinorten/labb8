import java.util.concurrent.ExecutionException;

public class Monster{
    public int healthPoints;
    Position currentPos,previousPos;
    TowerDefenceLevel level;
    public Monster(TowerDefenceLevel level){
        this.healthPoints = 10;
        this.level = level;
        this.currentPos = new Position(level.startRow,level.startCol);
        this.previousPos = new Position(0,0);
    }

    public Position getCurrentPos() {
        return currentPos;
    }

    public Position move() {
            if (currentPos.col != level.width-1 && level.checkPassable(currentPos.row, currentPos.col + 1) && currentPos.col+1 != previousPos.col) {
                previousPos = currentPos;
                currentPos = new Position(currentPos.row,currentPos.col+1);
                return currentPos;
            } else if (currentPos.row != 0 && level.checkPassable(currentPos.row - 1, currentPos.col) && currentPos.row-1 != previousPos.row) {
                previousPos = currentPos;
                currentPos = new Position(currentPos.row-1,currentPos.col);
                return currentPos;
            } else if (currentPos.row != level.height-1 && level.checkPassable(currentPos.row + 1, currentPos.col) && currentPos.row+1 != previousPos.row) {
                previousPos = currentPos;
                currentPos = new Position(currentPos.row+1,currentPos.col);
                return currentPos;
            } else if (level.checkPassable(currentPos.row, currentPos.col - 1) && currentPos.col-1 != previousPos.col) {
                previousPos = currentPos;
                currentPos = new Position(currentPos.row,currentPos.col-1);
                return currentPos;
            } else {
                return previousPos;
            }
        }
    }


