package hellotvxlet;

import java.awt.Font;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;

public class Observer extends HComponent implements ObserverInterface {
    
    Sprite timer, score, explanation;
    HScene scene;
    
    public Observer(){
        //
    }
    
    public void paint(int time){     
        timer.setTextContent(Integer.toString(time), HVisible.NORMAL_STATE);
        timer.repaint();          
    }
    
   
    public void showTimer(){
        this.setBounds(0,0,200,200);
        scene = HSceneFactory.getInstance().getDefaultHScene();
        timer = new Sprite(null,100,100);
        timer.setBounds(415,10,295,100);
        timer.setFont(new Font("ARIAL", Font.PLAIN, 20));
        timer.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(timer);
    }
    
    public void update(int time, Question question) {       
       if(time == 5){
            showTimer();
        } 
        
       if(time == 0){
            question.canAnswer = false;
        }
        
        if(time >= 0){
            paint(time);
        }        
    }
    
    public void showInfo() {
        this.setBounds(0,0,200,200);
        scene = HSceneFactory.getInstance().getDefaultHScene();
        explanation = new Sprite(null,100,100);
        explanation.setBounds(415,210,295,300);
        explanation.setFont(new Font("ARIAL", Font.PLAIN, 16));
        explanation.setBackgroundMode(HVisible.BACKGROUND_FILL);                
        explanation.setTextContent("\u2193,\u2191 = Antwoord selecteren.\n" +
                "ENTER = Antwoord kiezen.\n" +
                "SPACE = Volgende vraag.", HVisible.NORMAL_STATE);
        scene.add(explanation);
        scene.repaint();
    }
    
    public void showScore(int scoreInt, int quizLength) {
        this.setBounds(0,0,200,200);
        scene = HSceneFactory.getInstance().getDefaultHScene();
        score = new Sprite(null,100,100);
        score.setBounds(415,110,295,100);
        score.setFont(new Font("ARIAL", Font.PLAIN, 16));
        score.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        score.setTextContent("Score: " + Integer.toString(scoreInt) + "/" + Integer.toString(quizLength), HVisible.NORMAL_STATE);
        scene.add(score);
        scene.setVisible(true);
        showInfo();
    }
}
