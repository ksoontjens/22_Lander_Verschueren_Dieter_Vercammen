package hellotvxlet;

public interface SubjectInterface {
    
    void register(ObserverInterface ob, Question vraag);
    void unregister(ObserverInterface ob);

}
