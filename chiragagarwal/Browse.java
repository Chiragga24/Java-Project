package chiragagarwal;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class Browse {

    JFrame f = new JFrame("Confirmation");
    JPanel pan=new JPanel();
    JButton b1 = new JButton("Yes");
    JButton b2 = new JButton("No");
    JLabel l = new JLabel("Do you want me to search in the web browser??");

    public String link = "https://www.bing.com/search?q=";

    public Browse()
    {

        f.setSize(600, 200);
        l.setBounds(20,20,160,20);
        b1.setBounds (40,60,120,40);
        b2.setBounds(40,120,120,40);
        b1.addActionListener( new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            try{
                  f.dispose();
                  URI u = new URI(link);
                  Desktop d = Desktop.getDesktop();
                  d.browse(u);
                }
              catch(Exception f){
                System.out.println(f);
              }
          }
        });
        b2.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
            f.dispose();
          }
        });
        pan.add(l);
        pan.add(b1);
        pan.add(b2);
        f.add(pan);
        pan.setBackground(new Color(255,0,0));
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e)
    {
      try{
            f.dispose();
            URI u = new URI(link);
            Desktop d = Desktop.getDesktop();
            d.browse(u);
          }
        catch(Exception f){
          System.out.println(f);
        }
    }
}
