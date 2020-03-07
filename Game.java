
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
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
public class Game {

    Random rand = new Random();
    
    Global global = Global.getInstance();

    public void moveLasers(ArrayList<Bullet> lasers) {
        for(int i=0; i<lasers.size(); i++) {
            lasers.get(i).move();
        }
    }

    public void moveShots(ArrayList<EnemyBullet> shots) {
        for(int i=0; i<shots.size(); i++) {
            shots.get(i).move();
        }
    }
    
    public void drawLasers(ArrayList<Bullet> lasers, BufferedImage bulletImg) {
        for(int i=0; i<lasers.size(); i++) {
            drawLaser(bulletImg, lasers.get(i));
        }
    }

    public void drawShots(ArrayList<EnemyBullet> shots, BufferedImage enemyBulletImg) {
        for(int i=0; i<shots.size(); i++) {
            drawShot(enemyBulletImg, shots.get(i));
        }
    }

    public void drawLaser(BufferedImage bulletImg, Bullet b) {
        if(global.g == null) {
            global.g = global.p.getGraphics();
        }
        if(bulletImg == null) {
            try {
                bulletImg = ImageIO.read(getClass().getResourceAsStream("laser.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        global.g.drawImage(bulletImg, b.x, b.y, 25, 12, null);
    }
    
    public void drawShot(BufferedImage enemyBulletImg, EnemyBullet eb) {
        if(global.g == null) {
            global.g = global.p.getGraphics();
        }
        if(enemyBulletImg == null) {
            try {
                enemyBulletImg = ImageIO.read(getClass().getResourceAsStream("bullet.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        global.g.drawImage(enemyBulletImg, eb.x, eb.y, 25, 25, null);
    }

    public void drawHeroJet(BufferedImage heroImg) {
        if(global.g == null) {
            global.g = global.p.getGraphics();
        }
        if(heroImg == null) {
            try {
                heroImg = ImageIO.read(getClass().getResourceAsStream("hero.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        global.g.drawImage(heroImg, global.xx, global.i, 80, 100, null);
    }
}