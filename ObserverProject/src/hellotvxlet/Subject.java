/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Subject implements SubjectInterface {

    ArrayList lijstSubscribers = new ArrayList();
    
    public void register(ObserverInterface ob) {
        lijstSubscribers.add( ob );
    }

    public void unregister(ObserverInterface ob) {
        lijstSubscribers.remove( ob );
    }
    
    public void update_observers( int tijd ) {
        for( int i = 0; i < lijstSubscribers.size(); i++ ) {
            ( (ObserverInterface)lijstSubscribers.get( i ) ).update( tijd );
        }
    }

}
