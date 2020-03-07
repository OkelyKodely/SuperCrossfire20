
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dhcho
 */

public class Bullet {
    int x, y;
    ArrayList<Bullet> lasers = null;
    public Bullet(ArrayList<Bullet> lasers) {
        this.lasers = lasers;
    }
    public void move() {
        y-=10;
        if(x < 0 || x > 1100 || y < 0 || y > 660)
            this.lasers.remove(this);
    }
}