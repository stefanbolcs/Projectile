package src;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import objects.Ball;


/**
 *
 * @author Stefan
 */
public class Horizont extends JPanel{
    
    Ball oldBall, newBall;
    Double g = 9.8;
    Double angle = Math.PI;
    public double panelWidth, panelHeight;
    
    public Point zeroPoint = null;
    
    public int universalCounter = 0;
    
    public InputPanel inputPanel = null;
    
   
    
    
    String host = "localhost";      
    final int port = 12345;
    
    Socket connection = null;
    
    BufferedWriter out = null;
    
    public double kapottAdat[][] = new double[8][2];
    
    String outV0, outAngle;
    int howMany2 = 6;
    
    public int outputCounter=0;
    
     public double drawLines[][] = new double[howMany2][2];
    
    
        
        
    
    
   
    public Horizont(int frameWIDTH, int frameHEIGHT) {
        super();
      
        panelWidth = frameWIDTH;
        panelHeight = frameHEIGHT/2;
        
        
        this.setBackground(Color.white);
        
        oldBall = new Ball(frameWIDTH, frameHEIGHT, true);
        newBall = new Ball(frameWIDTH, frameHEIGHT, true);
        
        zeroPoint = new Point();
        zeroPoint.setLocation(35,panelHeight-50);
        /* ezt kellet kikomentalnom gyilkos
         try {
        connection = new Socket(host, port);
        }catch(Exception ex){
        
            System.out.println("ERROR IN HORIZONT CONSTRUCTOTR");}*/
       
    }

    

    
    
    

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
         Graphics2D g2 = (Graphics2D) g;
        
        g2.draw(new Line2D.Double(35, 5, zeroPoint.getX(), zeroPoint.getY()));
        g2.draw(new Line2D.Double(zeroPoint.getX(), zeroPoint.getY(), panelWidth-50,panelHeight-50 ));
        
        g2.draw(new Line2D.Double(panelWidth-50,panelHeight-60,panelWidth-50,panelHeight-40));
        g2.draw(new Line2D.Double(30,5,40,5));
        
        Font font = new Font("Serif",Font.PLAIN, 14);
        g2.setFont(font);
        g2.drawString("1020m",(int)panelWidth-65, (int)panelHeight-25);
        
        g2.drawString("510m", (int) 2, 15);
        
        double hundredMeterX =100*(939.0/1020.0);
        double hundredMeterY = 100 * (285.0/510.0);
        
        for(int u=1; u<6;u++){
        
            g2.draw(new Line2D.Double(30, zeroPoint.getY()-(hundredMeterY*u),40,zeroPoint.getY()-(hundredMeterY*u)));
        }
        for(int o=1; o<11;o++){
        
            g2.draw(new Line2D.Double(zeroPoint.getX()+(hundredMeterX*o),zeroPoint.getY()-10,zeroPoint.getX()+(hundredMeterX*o),zeroPoint.getY()+10));
        }
       
        
      
        if(universalCounter < howMany2)
         g2.draw(new Ellipse2D.Double(oldBall.getX(),oldBall.getY(), 25,25));
         
         if(universalCounter == howMany2){
             
             universalCounter = 0; //reset
             
             drawTrajectory(g2, drawLines);
         
         
         
         }
        
        
    }
    
    public void drawTrajectory(Graphics2D g2, double[][] tomb){
    
        double miniWidth = 10;
    
    
            for(int i=0; i< howMany2-1 ; i++){
            
            
               
                
                    g2.draw(new Line2D.Double(tomb[i][0],tomb[i][1], tomb[(i+1)][0], tomb[(i+1)][1]));
                    g2.fill(new Ellipse2D.Double(tomb[i][0]-(miniWidth/2),tomb[i][1]-miniWidth/2,miniWidth,miniWidth));
                    
                    
                
            
            
            }
            
            g2.fill(new Ellipse2D.Double(tomb[howMany2-1][0]-(miniWidth/2),tomb[howMany2-1][1]-miniWidth/2,miniWidth,miniWidth));
        
    
    }
    
    public void sendData22222(int v0, int angle){
   
         
     String one,two;
       
        
        try {
            
            
           
        connection = new Socket(host, port);
       
        
            System.out.println("ERROR IN HORIZONT CONSTRUCTOTR");} catch (IOException ex) {
            Logger.getLogger(Horizont.class.getName()).log(Level.SEVERE, null, ex);
        }
       try{
    
            
            
            //PrintWriter out2 = new PrintWriter(connection.getOutputStream());
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            
            out.write("This is send from the klient!!!!!\n");
            out.flush();
            //pociatocna rychlost
            out.write("50\n");
            out.flush();
            
            out.write("45\n");
            out.flush();
            
            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
            for(int k=0;k<7;k++){
            one = br.readLine();
            two = br.readLine();
            
            
                System.out.println("KAPOTT ADAT : "+one+",  "+two);
                oldBall.setX(-oldBall.getWIDTH()/2 + zeroPoint.getX() + parseANDMultiply(one, 3));
                oldBall.setY(-oldBall.getWIDTH()/2 + zeroPoint.getY() - parseANDMultiply(two, 3));
            
           
            revalidate();
            repaint();
            show();
            
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Horizont.class.getName()).log(Level.SEVERE, null, ex);
        } 
                
        
    }
    
    public void sendData(int v0,int angle){
        
        outV0 = String.valueOf(v0);
        outAngle = String.valueOf(angle);
        outputCounter = 0;
        ServerTask task;
        
        ( task = new ServerTask()).execute();
        
    }
    
    private static class FlipPair {
    
        private final  String xData, yData;
        
        FlipPair(String x, String y){
        
            this.xData = x;
            this.yData = y;
        }
    
    }

    
  
  
  private class ServerTask extends SwingWorker<Void, FlipPair>{
 
        @Override
        protected Void doInBackground() throws Exception {
            
            try {
            
        connection = new Socket(host, port);
       
        
            } catch (IOException ex) {
            Logger.getLogger(Horizont.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR IN HORIZONT CONSTRUCTOTR");
        }
            
            try {
            //PrintWriter out2 = new PrintWriter(connection.getOutputStream());
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            
            out.write("This is send from the klient!!!!!\n");
            out.flush();
            //pociatocna rychlost
            out.write(outV0+"\n");
            out.flush();
            
            out.write(outAngle+"\n");
            out.flush();
            
            String xData,yData;
            
            inputPanel.myTextArea.setText("");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
            for(int k=0;k<howMany2;k++){
            xData = br.readLine();
            yData = br.readLine();
            
           
              
           /*     oldBall.setX(-oldBall.getWIDTH()/2 + zeroPoint.getX() + parseANDMultiply(one, 3));
                oldBall.setY(-oldBall.getWIDTH()/2 + zeroPoint.getY() - parseANDMultiply(two, 3));
                   
            revalidate();
            repaint();
            show();*/
               
            publish(new FlipPair(xData,yData));
              
            
            }
            }
            
        catch (IOException ex) {
            Logger.getLogger(Horizont.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception in doInBackground");
        } 
           
            return null;
            
        }

        @Override
        protected void process(List<FlipPair> chunks) {
            
            FlipPair pair = chunks.get(chunks.size() -1);
            
           // System.out.println("Jonnek : "+pair.xData+",  "+pair.yData+",  counter: "+universalCounter);
            
       
            
            oldBall.setX(-oldBall.getWIDTH()/2 + zeroPoint.getX() + parseANDMultiply(pair.xData, 939.0/1020.0));
            oldBall.setY(-oldBall.getWIDTH()/2 + zeroPoint.getY() - parseANDMultiply(pair.yData, 285.0/510.0));
            
            
                System.out.println("Labda uj position: "+oldBall.getX()+",  "+oldBall.getY());
                System.out.println(",");
            
                inputPanel.myTextArea.setText(inputPanel.myTextArea.getText()+"\n"+universalCounter+": x = "+pair.xData+", y = "+pair.yData);
                
                
                if(universalCounter < howMany2){
                    
                    drawLines[universalCounter][0] = (oldBall.getX() + oldBall.getWIDTH()/2);
                    drawLines[universalCounter][1] = (oldBall.getY() + oldBall.getWIDTH()/2);
                    
                    System.out.println("Drawlines position counter "+universalCounter+" : "+drawLines[universalCounter][0]+",  "+drawLines[universalCounter][1]);
                
                }
                
                universalCounter++;
                
            revalidate();
            repaint();
            show();
            
            
        }
        
  }
  
  public InputPanel getInputPanel() {
        return inputPanel;
    }

    public void setInputPanel(InputPanel inputPanel) {
        this.inputPanel = inputPanel;
    }
    
    public double parseANDMultiply(String input, double multiply){
     
      return Double.parseDouble(input)*multiply;
  
  
  }
   
}
