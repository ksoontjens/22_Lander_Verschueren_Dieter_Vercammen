/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

/**
 *
 * @author student
 */
public class Observer implements ObserverInterface {

    public void update(int tijd) {
        System.out.println( this.toString() + "Tijd = " + tijd );
    }
    
}
