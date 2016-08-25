package hellotvxlet;

import java.awt.Color;
import org.havi.ui.HIcon;

public class Sprite extends HIcon implements ObserverInterface {

    Color kleur = Color.GRAY;
    
    public Sprite(String bitmap_naam, int x, int y){
        this.setBackground(kleur);
        this.repaint();
    }
    
    public void update(int tijd, Question vraag) {
       // 
    }

}
