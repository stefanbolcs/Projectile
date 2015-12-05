package src;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Stefan
 */
public class Projectile {
    private static Object horizont;
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int fWIDT = 1024;
        int fHEIGHT = 680;
        
        JFrame frame = new JFrame();
        frame.setSize(fWIDT, fHEIGHT);
        
        frame.setLocation(400, 400);
        frame.setTitle("Projectile of a ball");
        
        GridLayout layout = new GridLayout(2,1);
        frame.setLayout(layout);
        
         
        
        Horizont horizont = new Horizont(fWIDT, fHEIGHT);
        
        horizont.setPreferredSize(new Dimension(640,480));
        horizont.setBackground(Color.white);
        frame.add(horizont);
        
     
        
       JPanel panel2 = new InputPanel(horizont);
        
        panel2.setPreferredSize(new Dimension(320,480));
        
        panel2.setBackground(Color.gray);
        
        frame.add(panel2);
        
        horizont.setInputPanel((InputPanel)panel2);
        
        
        frame.revalidate();
        frame.show(true);
        
        
    }
    
}
