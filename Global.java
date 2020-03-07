
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
public class Global {
 
    private static Global instance = null;
    
    public long steps = 0;

    public int xx = 550;
    public int i = 600;
    
    public int y = 0;

    public Graphics g;
    public JPanel p;
    
    private Global() {}
    
    public static Global getInstance() {
        if(instance == null) {
            instance = new Global();
        }
        return instance;
    }
}
