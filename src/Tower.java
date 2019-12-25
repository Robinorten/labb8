import java.lang.management.MonitorInfo;

public class Tower {
    int firePower;
    Position[] reach = new Position[8];
    Position position;
    public Tower(Position position){
        this.firePower = 1;
        this.position = position;
        reach[0] = new Position(position.row-1,position.col-1);
        reach[1] = new Position(position.row,position.col-1);
        reach[2] = new Position(position.row-1,position.col);
        reach[3] = new Position(position.row+1,position.col-1);
        reach[4] = new Position(position.row-1,position.col+1);
        reach[5] = new Position(position.row+1,position.col+1);
        reach[6] = new Position(position.row+1,position.col);
        reach[7] = new Position(position.row,position.col+1);

    }
    public int attack(){
        double x = Math.random();
        if (x < 0.5){
            return firePower;
        } else {
            return 0;
        }
    }

}
