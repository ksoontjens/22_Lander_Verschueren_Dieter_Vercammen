package hellotvxlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;

public class HelloTVXlet implements Xlet, UserEventListener {

    Question question1 =  new Question("Wat is de volledige naam van KS?", "Koen Soortjes", "Koen Soontjens", "Koen Selleslagh", "Koen Slachmuylders", 2);
    Question question2 =  new Question("Voor welk ploeg speelt Hazard?", "Lille OC", "Everton FC", "Chelsea FC", "RSC Anderlecht", 3);
    Question question3 =  new Question("In welke straat woont de premier?", "Nieuwstraat", "Wetstraat", "Salesianenlaan", "Kapelstraat", 2);
    Question question4 =  new Question("In welk werelddeel ligt Jordanië?", "Azië", "Afrika", "Europa", "Zuid-Amerika", 1);
    Question question5 =  new Question("Waar werd Toots Thielemans geboren?", "Antwerpen", "Leuven", "Gent", "Brussel", 4);
    
    Question[] quiz = new Question[5];
    
    HStaticText explanation;
    HScene scene;

    int responseNr = 1;
    int currentQuestion = 0;
    Color zwart = Color.BLACK;
    Color wit = Color.WHITE;
    int score = 0;
    
    Observer ob1;
    Subject sub;
    MijnTimer mtt;
    Sprite sprite;
            
    public void initXlet(XletContext arg0) throws XletStateChangeException {        
        quiz[0] = question1;
        quiz[1] = question2;
        quiz[2] = question3;
        quiz[3] = question4;
        quiz[4] = question5;
        
        ob1 = new Observer();       
        sub = new Subject();
        sub.register(ob1, question1);
        question1.drawQuestion();
        mtt = new MijnTimer(sub);
        Timer tim = new Timer();
        tim.scheduleAtFixedRate(mtt, 0, 1000);
    }
    
     public void startXlet() throws XletStateChangeException {
        UserEventRepository mijnrep = new UserEventRepository("naam");
        mijnrep.addAllArrowKeys();
        mijnrep.addKey(HRcEvent.VK_ENTER);
        mijnrep.addKey(HRcEvent.VK_SPACE);
        EventManager mijnManager = EventManager.getInstance();
        mijnManager.addUserEventListener((UserEventListener) this,mijnrep);
        ob1.showScore(score, quiz.length);
    }
     
     public void userEventReceived(UserEvent e){        
        if(e.getType() == KeyEvent.KEY_PRESSED && !quiz[currentQuestion].hasAnswered && quiz[currentQuestion].canAnswer){
            if(e.getCode() == HRcEvent.VK_DOWN){
                if(responseNr >= 4){
                    responseNr = 1;
                }
                else{
                    responseNr++;                    
                }
                quiz[currentQuestion].changeAnswer(responseNr);
   
            }
            else if(e.getCode() == HRcEvent.VK_UP){
                if(responseNr <= 1){
                    responseNr = 4;
                }
                else{
                    responseNr--;                
                }
                quiz[currentQuestion].changeAnswer(responseNr);
  
            }
            else if(e.getCode() == HRcEvent.VK_ENTER){
                if(quiz[currentQuestion].canAnswer){                    
                    if(quiz[currentQuestion].selectAnswer(responseNr)){
                        score++;
                    }
                    quiz[currentQuestion].hasAnswered = true;
                }               
                
            }
        }
        
        if(e.getType() == KeyEvent.KEY_PRESSED && !quiz[currentQuestion].canAnswer){
            if(e.getCode() == HRcEvent.VK_SPACE && currentQuestion < quiz.length-1){
                quiz[currentQuestion].endQuestion();
                sub.unregister(ob1);
                currentQuestion++;
                responseNr = 1;
                mtt.time = 6;
                sub.register(ob1, quiz[currentQuestion]);           
                quiz[currentQuestion].drawQuestion();
                ob1.showScore(score, quiz.length);
            }
            else if(e.getCode() == HRcEvent.VK_SPACE){
                quiz[currentQuestion].endQuestion();
                sub.unregister(ob1);                
                
                scene = HSceneFactory.getInstance().getDefaultHScene();
                Sprite endText = new Sprite(null,100,100);
                endText.setBounds(160,310,400,100);
                endText.setTextContent("Score: " + score + "/" + quiz.length, HVisible.NORMAL_STATE);
                endText.setBackgroundMode(HVisible.BACKGROUND_FILL);
                scene.add(endText);
                scene.validate();
                scene.setVisible(true);
               
            }
        }
    }

    public void pauseXlet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroyXlet(boolean arg0) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

