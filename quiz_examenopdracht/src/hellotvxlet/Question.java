package hellotvxlet;

import java.awt.Color;
import java.awt.Font;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;

public class Question extends HComponent{
    
    Subject su;
    String question = "";
    String response1 = "";
    String response2 = "";
    String response3 = "";
    String response4 = "";
    int correctAnswer = 0;
    Sprite[] buttons = new Sprite[4];
    Color zwart = Color.BLACK;
    Color wit = Color.WHITE;
    boolean canAnswer = false;
    boolean hasAnswered = false;
    HScene scene;

            
    public Question(String vrg, String resp1, String resp2, String resp3, String resp4, int corrAnsw){
        question = vrg;
        response1 = resp1;
        response2 = resp2;
        response3 = resp3;
        response4 = resp4;
        correctAnswer = corrAnsw;
        canAnswer = true;
        this.setBounds(0,0,720,576);
        
    }
    
    public void drawQuestion(){
        scene = HSceneFactory.getInstance().getDefaultHScene();
        Observer ob1 = new Observer();
        scene.add(ob1);
        Sprite button1 = new Sprite(null,100,100);
        button1.setBounds(10,110,400,100);
        button1.setFont(new Font("ARIAL", Font.PLAIN, 20));
        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button1.setTextContent(response1, HVisible.NORMAL_STATE);
        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(button1);
        
        Sprite button2 = new Sprite(null,100,100);
        button2.setBounds(10,210,400,100);
        button2.setForeground(zwart);
        button2.setFont(new Font("ARIAL", Font.PLAIN, 20));
        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button2.setTextContent(response2, HVisible.NORMAL_STATE);
        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(button2);
        
        Sprite button3 = new Sprite(null,100,100);
        button3.setBounds(10,310,400,100);
        button3.setForeground(zwart);
        button3.setFont(new Font("ARIAL", Font.PLAIN, 20));
        button3.setBackgroundMode(HVisible.BACKGROUND_FILL); 
        button3.setTextContent(response3, HVisible.NORMAL_STATE);
        button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(button3);
        
        Sprite button4 = new Sprite(null,100,100);
        button4.setBounds(10,410,400,100);
        button4.setForeground(zwart);
        button4.setFont(new Font("ARIAL", Font.PLAIN, 20));
        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button4.setTextContent(response4, HVisible.NORMAL_STATE);
        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(button4);
        
        Sprite lblQuestion = new Sprite(null,100,100);
        lblQuestion.setBounds(10,10,400,100);
        lblQuestion.setFont(new Font("ARIAL", Font.PLAIN, 20));
        lblQuestion.setBackgroundMode(HVisible.BACKGROUND_FILL);
        lblQuestion.setTextContent(question, HVisible.NORMAL_STATE);
        scene.add(lblQuestion);
       
        buttons[0] = button1;
        buttons[1] = button2;
        buttons[2] = button3;
        buttons[3] = button4;
        
        scene.validate();
        scene.setVisible(true);
    }

    public void changeAnswer(int currAnswer){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setForeground(zwart);
            buttons[i].repaint();
        }
        buttons[currAnswer-1].setForeground(wit);
        buttons[currAnswer-1].repaint();
    }
    
    public boolean selectAnswer(int answer){
        if(answer == correctAnswer){
            buttons[answer-1].setBackground(Color.GREEN);
            buttons[answer-1].repaint();
            return true;
        }
        else {
            buttons[answer-1].setBackground(Color.RED);
            buttons[answer-1].repaint();
            return false;
        }
    }
    
    public void endQuestion(){
        canAnswer = false;
        scene.dispose();     
    }
}