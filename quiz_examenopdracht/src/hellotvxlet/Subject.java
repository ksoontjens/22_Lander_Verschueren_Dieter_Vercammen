package hellotvxlet;

import java.util.ArrayList;

public class Subject implements SubjectInterface {

    ArrayList subscribers = new ArrayList();
    Question question;
    
    public void register(ObserverInterface ob, Question vraag){
        subscribers.add(ob);
        question = vraag;
    }
    
    public void unregister(ObserverInterface ob){
        subscribers.remove(ob);
    }
    
    public void update_observers(int time){
        for(int i=0;i<subscribers.size();i++){
            ((ObserverInterface)subscribers.get(i)).update(time, question);
        }
    }
}
