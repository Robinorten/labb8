import java.lang.management.MonitorInfo;

public class Tower {
    int reach, firePower;
    public Tower(){

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
