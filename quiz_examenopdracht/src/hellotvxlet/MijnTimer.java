package hellotvxlet;

import java.util.TimerTask;

public class MijnTimer extends TimerTask {
    
    Subject su;
    int time = 6;
 
    public MijnTimer(Subject s){
        su = s;
    }
    
    public void run() {
        time--;
        if(su!=null) {
            
            su.update_observers(time);
        }
    }
}
