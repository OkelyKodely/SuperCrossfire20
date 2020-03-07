import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Interfire implements KeyListener {

    private int life = 10;
    
    private int enemies = 10;
    
    private JFrame j = new JFrame();
    
    private JPanel p = new JPanel();
    
    private Graphics g = null;
    
    private BufferedImage bulletImg = null;

    private BufferedImage enemyBulletImg = null;

    private BufferedImage heroImg = null;
    
    private BufferedImage enemyImg = null;
    
    private BufferedImage fieldImg1 = null;

    private BufferedImage fieldImg2 = null;
    
    private ArrayList<Bullet> lasers = new ArrayList<>();

    private ArrayList<EnemyJet> ee = new ArrayList<>();
    
    private ArrayList<EnemyBullet> shots = new ArrayList<>();

    public void addSound() {
        try {
            File audioFile = new File("theme.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            AudioFormat format = audioStream.getFormat();
            
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkForEnemyShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<ee.size(); qq++)
                if(lasers.get(ii).x > ee.get(qq).x && lasers.get(ii).x < ee.get(qq).x + 80 &&
                   lasers.get(ii).y > ee.get(qq).y && lasers.get(ii).y < ee.get(qq).y + 100) {

                    lasers.remove(lasers.get(ii));

                    ee.remove(ee.get(qq));
                }
            }
        } catch(Exception npe) {
            npe.printStackTrace();
        }
    }
    
    private void checkForEnemyShotShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<shots.size(); qq++)
                if(lasers.get(ii).x > shots.get(qq).x && lasers.get(ii).x < shots.get(qq).x + 25 &&
                   lasers.get(ii).y > shots.get(qq).y && lasers.get(ii).y < shots.get(qq).y + 12) {

                    lasers.remove(lasers.get(ii));

                    shots.remove(shots.get(qq));
                }
            }
        } catch(Exception npe) {
            npe.printStackTrace();
        }
    }

    private boolean isHeroShot() {

        for(int ii=0; ii<shots.size(); ii++) {

            if(shots.get(ii).x > xx && shots.get(ii).x < xx + 80 &&
               shots.get(ii).y > i && shots.get(ii).y < i + 100) {

                shots.remove(shots.get(ii));
                
                return true;
            }
        }

        return false;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(xx-10 < 0) {
                
            } else
            xx-=10;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(xx+10 > 1000) {
                
            } else
            xx+=10;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(i-10 < 0) {
                
            } else
            i-=10;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(i+10 > 600) {
                
            } else
            i+=10;
        }
        if(e.getKeyCode() == KeyEvent.VK_PERIOD) {
            fire();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Interfire() {

        addSound();

        setGui();

        setGame();

    }
    
        boolean start = true;
        int q = -1;
        long steps = 0;

    public class EnemyJet {
        int x, y;
        public void move() {
            if(start) {
                start = false;
                Random rd = new Random();
                q = rd.nextInt(3);
            }
            Random rand = new Random();
            int v = 100 + rand.nextInt(200);
            int w = 1 + rand.nextInt(2);
            int kk = rand.nextInt(10);
            steps++;
            System.out.println(steps);
            if(steps % 20 == 1) {
                Random rd = new Random();
                q = rd.nextInt(3);
            }
            if(kk == 0) {
                q = 2;
            }
            if(q == 0) {
                if(xx > this.x + w*v) {
                    boolean pass = true;
                    for(int z=0; z<ee.size(); z++) {
                        if(ee.get(z).x > this.x+5 && ee.get(z).x+80 < this.x+5 &&
                           ee.get(z).y > this.y && ee.get(z).y+100 < this.y) {
                            pass = false;
                        }
                    }
                    if(pass)
                        this.x += 5;
                }
                else if(xx < this.x + w*v) {
                    boolean pass = true;
                    for(int z=0; z<ee.size(); z++) {
                        if(ee.get(z).x > this.x+5 && ee.get(z).x+80 < this.x+5 &&
                           ee.get(z).y > this.y && ee.get(z).y+100 < this.y) {
                            pass = false;
                        }
                    }
                    if(pass)
                        this.x -= 5;
                }
            }
            else if(q == 1) {
                if(xx + w*v > this.x)
                    this.x += 5;
                else if(xx + w*v < this.x)
                    this.x -= 5;
            }
            else if(q == 2) {
                if(xx > this.x)
                    this.x += 3;
                else if(xx < this.x)
                    this.x -= 3;
                int kkk = rand.nextInt(10) - rand.nextInt(10);
                this.x += kkk;
            }
        }
        public void shoot() {
            EnemyBullet eb = new EnemyBullet(shots);
            eb.x = this.x + 26;
            eb.y = this.y + 70;
            shots.add(eb);
        }
    }
    
    public void setGame() {
        Thread t = new Thread() {
            public void run() {
                Random rand = new Random();
                while(true) {
                    if(ee.size() == 0) {
                        Random rrand = new Random();
                        for(int s = 0; s < 50; s++) {
                            int v = rrand.nextInt(1100);
                            int w = rrand.nextInt(300);
                            EnemyJet ej = new EnemyJet();
                            ej.x = v;
                            ej.y = w;
                            ee.add(ej);
                        }
                        life+=2;
                    }
                    try {
                        j.setTitle("Life: " + life);
                        for(int s = 0; s<50; s++) {
                            int v = rand.nextInt(165);
                            if(v == 0) {
                                if(s < ee.size())
                                    ee.get(s).shoot();
                            }
                        }
                        checkForEnemyShot();
                        checkForEnemyShotShot();
                        if(isHeroShot()) {
                            life--;
                        }
                        if(life == 0)
                            System.exit(0);
                        moveShots();
                        moveLasers();
                        Thread.sleep(30);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    for(int s = 0; s<50; s++) {
                        try {
                            if(ee.size() != s)
                                ee.get(s).move();
                        } catch(Exception npe) {
                            npe.printStackTrace();
                        }
                    }
                    drawField();
                    drawHeroJet();
                    drawEnemyJet();
                    drawShots();
                    drawLasers();
                }
            }
        };
        t.start();
    }
    
    public int xx = 550;
    public int i = 600;
    
    public int y = 0;

    Random rand = new Random();
    public void fire() {
        Bullet b = new Bullet(lasers);
        b.x = xx + 27;
        b.y = i - 10;
        lasers.add(b);
    }

    public void moveLasers() {
        for(int i=0; i<lasers.size(); i++) {
            lasers.get(i).move();
        }
    }

    public void moveShots() {
        for(int i=0; i<shots.size(); i++) {
            shots.get(i).move();
        }
    }
    
    public void drawLasers() {
        for(int i=0; i<lasers.size(); i++) {
            drawLaser(lasers.get(i));
        }
    }

    public void drawShots() {
        for(int i=0; i<shots.size(); i++) {
            drawShot(shots.get(i));
        }
    }

    public void drawLaser(Bullet b) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(bulletImg == null) {
            try {
                bulletImg = ImageIO.read(getClass().getResourceAsStream("laser.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        g.drawImage(bulletImg, b.x, b.y, 25, 12, null);
    }
    
    public void drawShot(EnemyBullet eb) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(enemyBulletImg == null) {
            try {
                enemyBulletImg = ImageIO.read(getClass().getResourceAsStream("bullet.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        g.drawImage(enemyBulletImg, eb.x, eb.y, 25, 25, null);
    }

    public void drawHeroJet() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(heroImg == null) {
            try {
                heroImg = ImageIO.read(getClass().getResourceAsStream("hero.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        g.drawImage(heroImg, xx, i, 80, 100, null);
    }
    
    public void drawField() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(fieldImg1 == null) {
            try {
                fieldImg1 = ImageIO.read(getClass().getResourceAsStream("field.png"));
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        if(fieldImg2 == null) {
            try {
                fieldImg2 = ImageIO.read(getClass().getResourceAsStream("field.png"));

                // Flip the image vertically
                AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                tx.translate(0, -fieldImg2.getHeight(null));
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                fieldImg2 = op.filter(fieldImg2, null);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        

        g.drawImage(fieldImg2, 0, y - 760, 1100, 760, null);
        g.drawImage(fieldImg1, 0, y, 1100, 760, null);
        y+=5;
        if(y == 760) {
            y = 0;
        }
    }
    

    public BufferedImage flip(BufferedImage sprite){
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
    }

    public void drawEnemyJet() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(enemyImg == null) {
            try {
                for(int s = 0; s<50; s++) {
                    int v = 100 + rand.nextInt(30);
                    int w = 1 + rand.nextInt(30);
                    ee.get(s).x = w*v;
                    int vv = rand.nextInt(3) + 1;
                    if(vv == 1)
                        ee.get(s).y = 100;
                    if(vv == 2)
                        ee.get(s).y = 200;
                    if(vv == 3)
                        ee.get(s).y = 300;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            try {
                if(ee.size() != 0)
                    enemyImg = ImageIO.read(getClass().getResourceAsStream("enemy3.png"));
                else if(steps == 0)
                    enemyImg = ImageIO.read(getClass().getResourceAsStream("enemy.png"));
                else if(steps == 1)
                    enemyImg = ImageIO.read(getClass().getResourceAsStream("enemy2.png"));

                steps++;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        for(int s = 0; s<50; s++) {
            if(s < ee.size())
                g.drawImage(enemyImg, ee.get(s).x, ee.get(s).y, 80, 100, null);
        }
    }
    
    private void drawGuiHere() {
        j.setLayout(null);
        j.setBounds(0, 0, 1100, 760);
        p.setBounds(j.getBounds());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.addKeyListener(this);
        j.setVisible(true);
    }
    
    public void setGui() {
        drawGuiHere();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Interfire();
    }
    
}
