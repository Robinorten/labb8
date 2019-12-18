import java.util.concurrent.ExecutionException;

public class Monster{
    public int healthPoints;
    Position currentPos,previousPos;
    TowerDefenceLevel level;
    public Monster(TowerDefenceLevel level){
        this.healthPoints = 10;
        this.level = level;
        this.currentPos = new Position(level.startRow,level.startCol);
    }

    public Position getCurrentPos() {
        return currentPos;
    }

    public Position move() {
            if (level.checkPassable(currentPos.row, currentPos.col + 1)) {
                previousPos = currentPos;
                currentPos.col = currentPos.col + 1;
                return currentPos;
            } else if (level.checkPassable(currentPos.row - 1, currentPos.col)) {
                previousPos = currentPos;
                currentPos.row = currentPos.row - 1;
                return currentPos;
            } else if (level.checkPassable(currentPos.row + 1, currentPos.col)) {
                previousPos = currentPos;
                currentPos.row = currentPos.row + 1;
                return currentPos;
            } else if (level.checkPassable(currentPos.row, currentPos.col - 1)) {
                previousPos = currentPos;
                currentPos.col = currentPos.col - 1;
                return currentPos;
            } else {
                return currentPos;
            }
        }
    }


