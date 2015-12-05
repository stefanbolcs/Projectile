package src;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicMenuUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stefan
 */
public class InputPanel  extends JPanel{
    
    static final int velocity_MIN = 0;
    static final int velocity_MAX = 100;
    static final int velocity_INIT = 20;
    
    static final int angle_MIN = 1;
    static final int angle_MAX = 89;
    static final int angle_INIT = 30;
    
    static final int point_MIN = 1;
    static final int point_MAX = 10;
    static final int point_INIT = 7;
    
    
    
    
    JTextArea myTextArea = null;
   
    
    JSlider sliderVelocity = null;
    JSlider sliderAngle = null;
    JSlider sliderPoint = null;
    String[] labels = {"Velocity: ","Angle :","Points :"};
    
    Horizont other = null;

    public InputPanel(Horizont other) {
        
        this.other = other;
       
        JLabel labelPoints = new JLabel(labels[2],JLabel.TRAILING);
        this.add(labelPoints);
        
        sliderPoint = new JSlider(JSlider.HORIZONTAL, point_MIN,point_MAX,point_INIT);   
        sliderPoint.setMinorTickSpacing(1);
        sliderPoint.setPaintLabels(true);
        
        this.add(sliderPoint);
        
        JTextField pointTextField = new JTextField(3);
        this.add(pointTextField);
        
        sliderPoint.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                Object source = e.getSource();
                JSlider theSlider = (JSlider) source;
                
                pointTextField.setText(String.valueOf(theSlider.getValue()));
            }
        });
        
                
        
        
        JLabel labelVelocity = new JLabel(labels[0],JLabel.TRAILING);
        this.add(labelVelocity);
        
        sliderVelocity = new JSlider(JSlider.HORIZONTAL, velocity_MIN,velocity_MAX,velocity_INIT);   
        sliderVelocity.setMinorTickSpacing(1);
        sliderVelocity.setPaintLabels(true);
        
       this.add(sliderVelocity);
       
       JTextField velocityTextField = new JTextField(3);
       this.add(velocityTextField);
       
       sliderVelocity.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                Object source = e.getSource();
                JSlider theSlider = (JSlider) source;
                
                velocityTextField.setText(String.valueOf(theSlider.getValue()));
            }
        });
       
       
       JLabel labelAngle = new JLabel(labels[1],JLabel.TRAILING);
       this.add(labelAngle);
       
       
       
        sliderAngle = new JSlider(JSlider.HORIZONTAL, angle_MIN,angle_MAX,angle_INIT);   
        sliderAngle.setMinorTickSpacing(1);
        sliderAngle.setPaintLabels(true);
        
       this.add(sliderAngle);
       
       JTextField angleTextField = new JTextField(2);
       this.add(angleTextField);
       
       
       sliderAngle.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                Object source = e.getSource();
                JSlider theSlider = (JSlider) source;
                
                angleTextField.setText(String.valueOf(theSlider.getValue()));
            }
        });
       
       JButton b = new JButton("SEND");
       
 
       
       b.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.out.println("SliderVelocity is: "+sliderVelocity.getValue());
                System.out.println("SliderAngle is : "+sliderAngle.getValue());
                System.out.println("Number of points is: "+sliderPoint.getValue());
                
             
                
              other.sendData(sliderVelocity.getValue(),sliderAngle.getValue(),sliderPoint.getValue());
            }
       
       });
       
       add(b);
       
            myTextArea = new JTextArea(80, 50);
            
            add(myTextArea);
            
    }

    public JTextArea getMyTextArea() {
        return myTextArea;
    }

    public void setMyTextArea(JTextArea myTextArea) {
        this.myTextArea = myTextArea;
    }
    
    
    
    
    
    
}
