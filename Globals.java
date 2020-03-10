
import java.awt.Graphics;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhcho
 */
public class Globals {
 
    private static Globals instance = null;
    
    public long steps = 0;

    //hero jet x (xx), y (i)
    public int xx = 550;
    public int i = 600;
    
    //field position vertical
    public int y = 0;

    public Graphics g;
    public JPanel p;
    
    private Globals() {}
    
    public static Globals getInstance() {
        if(instance == null) {
            instance = new Globals();
        }
        return instance;
    }
}
