
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
public class EnemyBullet {
    int x, y;
    ArrayList<EnemyBullet> shots = null;
    public EnemyBullet(ArrayList<EnemyBullet> shots) {
        this.shots = shots;
    }
    public void move() {
        y+=7;
        if(x < 0 || x > 1100 || y < 0 || y > 660)
            shots.remove(this);
    }
}