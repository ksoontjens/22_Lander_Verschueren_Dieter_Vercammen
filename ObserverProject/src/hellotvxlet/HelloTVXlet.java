package hellotvxlet;

import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;


public class HelloTVXlet implements Xlet {

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
        
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        Observer ob1 = new Observer();
        Observer ob2 = new Observer();
        Observer ob3 = new Observer();
        Subject sub = new Subject();
        sub.register( ob1 );
        sub.register( ob2 );
        sub.register( ob3 );
        
        MijnTimerTask mtt = new MijnTimerTask( sub );
        Timer tim = new Timer();
        tim.scheduleAtFixedRate( mtt, 0, 10 ); //start op 0 elke 1000ms
        
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();
        
        for( int i = 0; i < 10; i++ ) {
            Sprite knop = new Sprite( "spaceship.png", 50 + 30 * i, 50 + 30 * i );
            knop.setBounds( 200, 200, 100, 100 );
            knop.setBackgroundMode( HVisible.BACKGROUND_FILL );
            sub.register( knop );
            scene.add( knop );
        }
        scene.validate();
        scene.setVisible( true );
    }

    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
        
    }
    
    
    
}
