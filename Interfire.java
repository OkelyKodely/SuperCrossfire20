import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

public class Interfire implements KeyListener {
   
    private BufferedImage bimg = null;
    
    private ArrayList<Bomber> bombers = new ArrayList<>();

    private ArrayList<BomberShot> bos = new ArrayList<>();

    private BufferedImage bomShotImg = null;
    
    private int hj = 0;
    
    private JPanel jp = new JPanel();

    private JButton b = new JButton();

    private BufferedImage cloudImg = null;

    private BufferedImage cloud2Img = null;

    private String boss2str = "boss1b.png";
    
    private Image boss2Img = null;

    private BufferedImage boss2BulletImg = null;
    
    private Boss2 bs2 = null;
    
    private ArrayList<Boss2Bullet> bs2bullets = new ArrayList<>();
    
    private int explo = 0;
    
    private boolean bs1went = false;

    private ArrayList<Explode> exs = new ArrayList<>();

    private boolean menu = true;

    private boolean a = true;

    private ArrayList<Cloud> clouds = new ArrayList<>();

    private ArrayList<Cloud2> clouds2 = new ArrayList<>();
    
    private Cloud cloud = null;

    private Cloud2 cloud2 = null;

    private Image xp = null;

    private int v = -1;

    private ArrayList<Boss1Bullet> bs1bullets = new ArrayList<>();
    
    private BufferedImage boss1BulletImg = null;

    private BufferedImage boss1Img = null;
    
    private Boss1 bs1 = null;
    
    private Image flatTankImg = null;
    
    private Image tankImg2 = null;
    
    private boolean start = true;

    private int q = -1;

    private Globals globals = null;
    
    private int steeps = 0;
    
    private Tank tk = null;
    
    private Tank tk2 = null;
    
    private boolean notk = true;
    
    private int totank = 0;
    
    private int cc = 0;
    
    private int dd = 0;
    
    private Random rand = new Random();

    private boolean goingDownTk = true;
    
    private boolean goingDownTk2 = true;
    
    private BufferedImage tank2BulletImg = null;
    
    private ArrayList<Boss1Bullet> tk2bullets = new ArrayList<>();
    
    private ArrayList<Boss1Bullet> tkbullets = new ArrayList<>();

    private Image tankImg = null;
    
    private long points = 0;
    
    private boolean aerialFightAgain = false;
    
    private int life = 30;
    
    private int enemies = 10;
    
    private JFrame j = new JFrame();
    
    private JPanel p = new JPanel();
    
    private Graphics g = null;
    
    private BufferedImage bulletImg = null;

    private BufferedImage enemyBulletImg = null;

    private BufferedImage heroImg = null;
    
    private Image enemyImg = null;
    
    private BufferedImage fieldImg1 = null;

    private BufferedImage fieldImg2 = null;
    
    private BufferedImage fieldImg3 = null;

    private ArrayList<Bullet> lasers = new ArrayList<>();

    private ArrayList<EnemyJet> ee = new ArrayList<>();
    
    private ArrayList<FlatTank> ft = new ArrayList<>();

    private ArrayList<EnemyBullet> shots = new ArrayList<>();

    private Game game = new Game();

    private boolean start2 = true;

    private RedPowerUp pu = null;
    
    private MagentaPowerUp pu2 = null;

    private int powerUp = 1;

    private String powerUpKind = "red";
    
    public Interfire() {
        JFrame jj = new JFrame();
        jj.setUndecorated(true);
        jj.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        jj.setLayout(null);
        jj.setBounds(0, 0, 500, 400);
        jj.setVisible(true);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int framewidth=jj.getSize().width;//get the width of the frame
        int frameheigth=jj.getSize().height; //get the heigth of the frame
        int framelocationX=(dim.width-framewidth)/2; 
        int framelocationY=(dim.height-frameheigth)/2;
        jj.setLocation(framelocationX,framelocationY);
        Graphics g = null;
        if(g == null) {
            g = jj.getGraphics();
        }
        BufferedImage backImg = null;
        if(backImg == null) {
            try {
                backImg = ImageIO.read(getClass().getResourceAsStream("field.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(backImg, 0, 0, 500, 400, null);
        BufferedImage boss1Img = null;
        if(boss1Img == null) {
            try {
                boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(boss1Img, 50, 50, 350, 200, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma",Font.BOLD,30));
        g.drawString("Super Crossfire 20", 200, 250);
        g.setFont(new Font("Tahoma",Font.BOLD,13));
        g.drawString("Move: Arrow Buttons", 10, 310);
        g.drawString("Fire: Period Button", 10, 340);
        g.setColor(Color.YELLOW);
        g.drawString("LOADING...", 400, 310);
        g.setColor(Color.WHITE);
        g.drawString("Don't Get Hit By The Enemy Fire!!", 10, 370);
        BufferedImage img = null;
        if(img == null) {
            try {
                img = ImageIO.read(getClass().getResourceAsStream("hero.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(img, 250, 250, 100, 100, null);
        try {
            addSound();
            Thread.sleep(10000);
            jj.dispose();
        } catch(Exception e) {
            e.printStackTrace();
        }

        menu();
        
        j.requestFocus();
    }
    
    class Boss2 {
        int x, y;
        int life = 11;
        Random rr = new Random();
        public void shoot() {
            
            int v = rr.nextInt(250);
            Boss1Bullet eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 90 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 90 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 50 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 50 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 10 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 10 + 60;
            bs1bullets.add(eb);
        }
    }

    class Boss1 {
        int x, y;
        int life = 10;
        Random rr = new Random();
        public void shoot() {
            int v = rr.nextInt(250);
            Boss1Bullet eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 90 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 90 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 50 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 50 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51 + v;
            eb.y = this.y + 10 + 60;
            bs1bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51 + v;
            eb.y = this.y + 10 + 60;
            bs1bullets.add(eb);
        }
    }

    class FlatTank {
        int x, y;
        int q;
        String dir = "left";
        int xplusfive = 5;
        int xminusfive = -5;
        public void move() {
            if(this.x >= 1100) {
                dir = "left";
            }
            if(dir.equals("left")) {
                this.x += xminusfive;
            }
            if(this.x <= 0) {
                dir = "right";
            }
            if(dir.equals("right")) {
                this.x += xplusfive;
            }
            int v = rand.nextInt(19);
            if(v == 0) {
                if(dir.equals("right")) {
                    dir = "left";
                }
                else
                    dir = "right";
            }
        }
        public void shoot() {
            Boss1Bullet eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 90 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 90 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 50 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 50 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 10 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 10 + 60;
            tk2bullets.add(eb);
        }
    }
    
    class RedPowerUp {
        int x, y;
        private void draw() {
            if(g == null) {
                g = p.getGraphics();
            }
            g.setColor(Color.red);
            
            g.fillOval(x, y, 40, 40);
            
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("Red", x, y);
        }
    }
    
    class MagentaPowerUp {
        int x, y;
        private void draw() {
            if(g == null) {
                g = p.getGraphics();
            }
            g.setColor(Color.magenta);
            
            g.fillOval(x, y, 40, 40);
            
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("Magenta", x, y);
        }
    }

    class Cloud2 {
        int x, y;
    }
    
    class Cloud {
        int x, y;
    }
    
    class Explode {
        int x, y;
        int explo = 0;
    }

    class BomberShot {
        int x, y;
        int xm = 0;
        int ym = 0;
        int hj = 0;
        void move() {
            y += 10;
        }
    }
    
    class Bomber {
        int x, y;
        void draw() {
            if(g == null) {
                g = p.getGraphics();
            }
            if(bimg == null) {
                try {
                    bimg = ImageIO.read(getClass().getResourceAsStream("bomber.png"));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            g.drawImage(bimg, x, y, 200, 80, null);
        }
        void shoot() {
            int xi = globals.xx - x;
            int yi = globals.i - y;
            double xhf = ((double)xi/(double)yi)*3.0;
            long yyu = Math.round(xhf);
            long xxu = 3l;
            BomberShot bs = new BomberShot();
            bs.x = this.x;
            bs.y = this.y;
            bs.xm = (int)yyu;
            bs.ym = (int)xxu;
            bos.add(bs);
        }
    }
    
    class EnemyJet {
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
            globals.steps++;
            if(globals.steps % 20 == 1) {
                Random rd = new Random();
                q = rd.nextInt(3);
            }
            if(kk == 0) {
                q = 2;
            }
            if(q == 0) {
                if(globals.xx > this.x + w*v) {
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
                else if(globals.xx < this.x + w*v) {
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
                if(globals.xx + w*v > this.x)
                    this.x += 5;
                else if(globals.xx + w*v < this.x)
                    this.x -= 5;
            }
            else if(q == 2) {
                if(globals.xx > this.x)
                    this.x += 3;
                else if(globals.xx < this.x)
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
    
    class Tank {
        int x, y;
        int life = 130;
        String powerUpK = "red";
        public void shoot() {
            Boss1Bullet eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 90 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 90 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 50 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 50 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 26 + 51;
            eb.y = this.y + 10 + 60;
            tk2bullets.add(eb);
            eb = new Boss1Bullet();
            eb.x = this.x + 36 + 51;
            eb.y = this.y + 10 + 60;
            tk2bullets.add(eb);
        }
    }

    class Boss1Bullet {
        int x, y;
        private void move() {
            y+=5;
        }
    }
    
    class Boss2Bullet {
        int x, y;
        private void move() {
            y+=5;
        }
    }

    public void menu() {
        Thread aa = new Thread() {
            public void run() {
                setGui();
                if(g == null) {
                    g = p.getGraphics();
                    try {
                        Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                        g.drawImage(iii, 0, 0, 1100, 750, null);
                    } catch(Exception e) {}
                    
                    g.setColor(Color.white);
                    
                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Super Crossfire 20", 10, 50);
                            
                    g.setFont(new Font("arial",Font.BOLD,15));
                    g.drawString("> PLAY NEW GAME", 10, 100);
                    g.drawString("Exit Game", 10, 150);

                    BufferedImage boss1Img = null;
                    if(boss1Img == null) {
                        try {
                            boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                            g.drawImage(boss1Img, 300, 300, null);
                        } catch(Exception e) {

                        }
                    }
                }
            }
        };
        aa.start();
    }

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
            
        }
    }
    
    public void shootsound() {
        try {
            File audioFile = new File("lasershot.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            AudioFormat format = audioStream.getFormat();
            
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.start();
            audioStream.close();
        } catch (Exception e) {
            
        }
    }

    public void explodesound() {
        try {
            File audioFile = new File("exp.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            AudioFormat format = audioStream.getFormat();
            
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.start();
            audioStream.close();
        } catch (Exception e) {
            
        }
    }

    public void greatYouKilledEmAll() {
        try {
            File audioFile = new File("speech.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            AudioFormat format = audioStream.getFormat();
            
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            
            audioClip.open(audioStream);
            audioClip.start();
            audioStream.close();
        } catch (Exception e) {
            
        }
        Thread t = new Thread() {
            public void run() {
                if(globals.g == null) {
                    globals.g = globals.p.getGraphics();
                }
                globals.g.setColor(Color.WHITE);
                globals.g.setFont(new Font("arial",Font.BOLD,40));
                Thread a = new Thread() {
                    public void run() {
                        int c = 0;
                        while(true) {
                            globals.g.drawString("gREAT. You killed 'em all.", 200, 400);
                            c++;
                            if(c > 100)
                                break;
                        }
                    }
                };
                a.start();
            }
        };
        t.start();
    }

    public void initClouds() {
        int f = 1;
        for(int x=0; x<f; x++) {
            Cloud cloud = new Cloud();
            cloud.x = 510 + rand.nextInt(500);
            cloud.y = 100 + rand.nextInt(580);
            clouds.add(cloud);
        }
    }

    public void initClouds2() {
        int f = 1;
        for(int x=0; x<f; x++) {
            Cloud2 cloud = new Cloud2();
            cloud.x = 300 + rand.nextInt(500);
            cloud.y = 100 + rand.nextInt(580);
            clouds2.add(cloud);
        }
    }

    private boolean ateMagentaPowerUp() {
        if(globals.xx+40 > pu2.x && globals.xx+40 < pu2.x + 45 &&
           globals.i+50 > pu2.y && globals.i+50 < pu2.y + 45) {
            if(powerUp == 0) 
                powerUp = 1;
            else {
                if(powerUp == 1) 
                    powerUp = 2;
                else
                    powerUp = 3;
            }
            powerUpKind = "magenta";
            return true;
        }
        return false;
    }
    
    private boolean ateRedPowerUp() {
        if(globals.xx+40 > pu.x && globals.xx+40 < pu.x + 45 &&
           globals.i+50 > pu.y && globals.i+50 < pu.y + 45) {
            powerUp ++;
            if(!powerUpKind.equals("red")) {
                powerUpKind = "red";
                powerUp = 1;
            }
            return true;
        }
        return false;
    }

    private void checkForTank2Shot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                if(lasers.get(ii).x > tk2.x && lasers.get(ii).x < tk2.x + 150 &&
                   lasers.get(ii).y > tk2.y && lasers.get(ii).y < tk2.y + 150) {

                    lasers.remove(lasers.get(ii));

                    if(powerUpKind.equals("magenta"))
                        tk2.life -= 10;
                    else
                        tk2.life --;
                    
                    points += 231;
                }
            }
        } catch(Exception npe) {
            
        }
    }

    private void checkForTankShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                if(lasers.get(ii).x > tk.x && lasers.get(ii).x < tk.x + 150 &&
                   lasers.get(ii).y > tk.y && lasers.get(ii).y < tk.y + 150) {

                    lasers.remove(lasers.get(ii));

                    if(powerUpKind.equals("magenta"))
                        tk.life -= 10;
                    else
                        tk.life --;

                    points += 231;
                }
            }
        } catch(Exception npe) {
            
        }
    }
    
    private void checkBomsShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<bos.size(); qq++)
                    if(lasers.get(ii).x > bos.get(qq).x && lasers.get(ii).x < bos.get(qq).x + 25 &&
                       lasers.get(ii).y > bos.get(qq).y && lasers.get(ii).y < bos.get(qq).y + 45) {

                        lasers.remove(lasers.get(ii));

                        bos.remove(bos.get(qq));

                        Explode ex = new Explode();
                        ex.x = lasers.get(ii).x-30;
                        ex.y = lasers.get(ii).y-150;
                        exs.add(ex);

                        points += 231;
                    }
            }
        } catch(Exception e) {}
        
    }
    
    private void checkForEnemyShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<ee.size(); qq++)
                    if(totank > 10) {
                        if(lasers.get(ii).x > ee.get(qq).x && lasers.get(ii).x < ee.get(qq).x + 25 &&
                           lasers.get(ii).y > ee.get(qq).y && lasers.get(ii).y < ee.get(qq).y + 45) {

                            lasers.remove(lasers.get(ii));

                            ee.remove(ee.get(qq));
                            
                            Explode ex = new Explode();
                            ex.x = lasers.get(ii).x-30;
                            ex.y = lasers.get(ii).y-150;
                            exs.add(ex);

                            points += 231;
                        }
                    } else {
                        if(lasers.get(ii).x > ee.get(qq).x && lasers.get(ii).x < ee.get(qq).x + 80 &&
                           lasers.get(ii).y > ee.get(qq).y && lasers.get(ii).y < ee.get(qq).y + 100) {

                            lasers.remove(lasers.get(ii));

                            ee.remove(ee.get(qq));

                            Explode ex = new Explode();
                            ex.x = lasers.get(ii).x-30;
                            ex.y = lasers.get(ii).y-150;
                            exs.add(ex);

                            points += 231;
                        }
                }
            }
        } catch(Exception npe) {
            
        }
    }
    
    private void checkForBomberGotShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<bombers.size(); qq++)
                    if(lasers.get(ii).x > bombers.get(qq).x && lasers.get(ii).x < bombers.get(qq).x + 200 &&
                       lasers.get(ii).y > bombers.get(qq).y && lasers.get(ii).y < bombers.get(qq).y + 80) {

                        lasers.remove(lasers.get(ii));

                        bombers.remove(bombers.get(qq));

                        Explode ex = new Explode();
                        ex.x = lasers.get(ii).x-30;
                        ex.y = lasers.get(ii).y-150;
                        exs.add(ex);

                        points += 231;
                    }
            }
        } catch(Exception npe) {
            
        }
    }

    private void checkForBoss1Shot() {
        for(int ii=0; ii<lasers.size(); ii++) {
            if(lasers.get(ii).x > bs1.x && lasers.get(ii).x < bs1.x + 350 &&
               lasers.get(ii).y > bs1.y && lasers.get(ii).y < bs1.y + 200) {

                lasers.remove(lasers.get(ii));

                if(powerUpKind.equals("magenta"))
                    bs1.life -= 10;
                else
                    bs1.life --;

                Explode ex = new Explode();
                ex.x = lasers.get(ii).x-30;
                ex.y = lasers.get(ii).y-150;
                exs.add(ex);

                points += 231;
            }
        }
    }
    
    private void checkForBoss2Shot() {
        for(int ii=0; ii<lasers.size(); ii++) {
            if(lasers.get(ii).x > bs2.x && lasers.get(ii).x < bs2.x + 350 &&
               lasers.get(ii).y > bs2.y && lasers.get(ii).y < bs2.y + 200) {

                lasers.remove(lasers.get(ii));
                
                if(powerUpKind.equals("magenta"))
                    bs2.life -= 10;
                else
                    bs2.life --;

                Explode ex = new Explode();
                ex.x = lasers.get(ii).x-30;
                ex.y = lasers.get(ii).y-150;
                exs.add(ex);

                points += 231;
            }
        }
    }

    private void checkForFtShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<ft.size(); qq++)
                if(lasers.get(ii).x > ft.get(qq).x && lasers.get(ii).x < ft.get(qq).x + 60 &&
                   lasers.get(ii).y > ft.get(qq).y && lasers.get(ii).y < ft.get(qq).y + 150) {

                    lasers.remove(lasers.get(ii));

                    ft.remove(ft.get(qq));

                    Explode ex = new Explode();
                    ex.x = lasers.get(ii).x-30;
                    ex.y = lasers.get(ii).y-150;
                    exs.add(ex);

                    points += 231;
                }
            }
        } catch(Exception npe) {
            
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

                    points += 23;
                }
            }
        } catch(Exception npe) {
            
        }
    }

    private void checkForBomberShotShot() {

        try {
            for(int ii=0; ii<lasers.size(); ii++) {

                for(int qq=0; qq<bos.size(); qq++)
                if(lasers.get(ii).x > bos.get(qq).x && lasers.get(ii).x < bos.get(qq).x + 25 &&
                   lasers.get(ii).y > bos.get(qq).y && lasers.get(ii).y < bos.get(qq).y + 12) {

                    lasers.remove(lasers.get(ii));

                    bos.remove(bos.get(qq));

                    points += 23;
                }
            }
        } catch(Exception npe) {
            
        }
    }

    private boolean isHeroShotBoms() {

        for(int ii=0; ii<bos.size(); ii++) {

            if(bos.get(ii).x > globals.xx && bos.get(ii).x < globals.xx + 80 &&
               bos.get(ii).y > globals.i && bos.get(ii).y < globals.i + 100) {

                bos.remove(bos.get(ii));
                
                return true;
            }
        }

        return false;
    }
    
    private boolean isHeroShot() {

        for(int ii=0; ii<shots.size(); ii++) {

            if(shots.get(ii).x > globals.xx && shots.get(ii).x < globals.xx + 80 &&
               shots.get(ii).y > globals.i && shots.get(ii).y < globals.i + 100) {

                shots.remove(shots.get(ii));
                
                return true;
            }
        }

        return false;
    }
    
    private boolean isHeroShotByBomber() {

        for(int ii=0; ii<bs1bullets.size(); ii++) {

            if(bs1bullets.get(ii).x > globals.xx && bs1bullets.get(ii).x < globals.xx + 80 &&
               bs1bullets.get(ii).y > globals.i && bs1bullets.get(ii).y < globals.i + 100) {

                bs1bullets.remove(bs1bullets.get(ii));
                
                life -= 5;
                
                return true;
            }
        }

        return false;
    }

    private boolean isHeroShotTk() {

        for(int ii=0; ii<tk2bullets.size(); ii++) {

            if(tk2bullets.get(ii).x > globals.xx && tk2bullets.get(ii).x < globals.xx + 80 &&
               tk2bullets.get(ii).y > globals.i && tk2bullets.get(ii).y < globals.i + 100) {

                tk2bullets.remove(tk2bullets.get(ii));
                
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
        if(menu) {
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(a) {
                    a = false;
                    try {
                        Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                        g.drawImage(iii, 0, 0, 1100, 750, null);
                    } catch(Exception ee) {}
                    
                    g.setColor(Color.white);
                    
                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Super Crossfire 20", 10, 50);
                            
                    g.setFont(new Font("arial",Font.BOLD,15));
                    g.drawString("PLAY NEW GAME", 10, 100);
                    g.drawString("> Exit Game", 10, 150);

                    BufferedImage boss1Img = null;
                    if(boss1Img == null) {
                        try {
                            boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                            g.drawImage(boss1Img, 300, 300, null);
                        } catch(Exception ee) {

                        }
                    }
                } else {
                    a = true;
                    try {
                        Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                        g.drawImage(iii, 0, 0, 1100, 750, null);
                    } catch(Exception ee) {}
                    
                    g.setColor(Color.white);
                    
                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Super Crossfire 20", 10, 50);
                            
                    g.setFont(new Font("arial",Font.BOLD,15));
                    g.drawString("> PLAY NEW GAME", 10, 100);
                    g.drawString("Exit Game", 10, 150);

                    BufferedImage boss1Img = null;
                    if(boss1Img == null) {
                        try {
                            boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                            g.drawImage(boss1Img, 300, 300, null);
                        } catch(Exception ee) {

                        }
                    }
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP) {
                if(a) {
                    a = false;
                    try {
                        Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                        g.drawImage(iii, 0, 0, 1100, 750, null);
                    } catch(Exception ee) {}
                    
                    g.setColor(Color.white);
                    
                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Super Crossfire 20", 10, 50);
                            
                    g.setFont(new Font("arial",Font.BOLD,15));
                    g.drawString("PLAY NEW GAME", 10, 100);
                    g.drawString("> Exit Game", 10, 150);

                    BufferedImage boss1Img = null;
                    if(boss1Img == null) {
                        try {
                            boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                            g.drawImage(boss1Img, 300, 300, null);
                        } catch(Exception ee) {

                        }
                    }
                } else {
                    a = true;
                    try {
                        Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                        g.drawImage(iii, 0, 0, 1100, 750, null);
                    } catch(Exception ee) {}
                    
                    g.setColor(Color.white);
                    
                    g.setFont(new Font("arial",Font.BOLD,30));
                    g.drawString("Super Crossfire 20", 10, 50);
                            
                    g.setFont(new Font("arial",Font.BOLD,15));
                    g.drawString("> PLAY NEW GAME", 10, 100);
                    g.drawString("Exit Game", 10, 150);

                    BufferedImage boss1Img = null;
                    if(boss1Img == null) {
                        try {
                            boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                            g.drawImage(boss1Img, 300, 300, null);
                        } catch(Exception ee) {

                        }
                    }
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                Thread abc = new Thread() {
                    public void run() {
                        if(a) {
                            menu = false;
                            Thread cba = new Thread() {
                                public void run() {
                                    if(globals != null)
                                        globals.y = 0;
                                    powerUp = 0;
                                    life = 30;
                                    points = 0;
                                    ee.clear();
                                    ft.clear();
                                    totank = 0;
                                    bs1 = null;
                                    bs2 = null;
                                    aerialFightAgain = false;
                                    notk = false;
                                    steeps = 0;
                                    setGame();
                                    j.requestFocus();
                                }
                            };
                            cba.start();
                        } else {
                            System.exit(0);
                        }
                    }
                };
                abc.start();
            }
        }
        else if(!menu) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                try {
                    Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                    g.drawImage(iii, 0, 0, 1100, 750, null);
                } catch(Exception ee) {}

                g.setColor(Color.white);

                g.setFont(new Font("arial",Font.BOLD,30));
                g.drawString("Super Crossfire 20", 10, 50);

                g.setFont(new Font("arial",Font.BOLD,15));
                g.drawString("> PLAY NEW GAME", 10, 100);
                g.drawString("Exit Game", 10, 150);
                menu = true;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(globals.xx-10 < 0) {

                } else
                globals.xx-=10;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(globals.xx+10 > 1000) {

                } else
                globals.xx+=10;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP) {
                if(globals.i-10 < 0) {

                } else
                globals.i-=10;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(globals.i+10 > 600) {

                } else
                globals.i+=10;
            }
            if(e.getKeyCode() == KeyEvent.VK_PERIOD) {
                fire(lasers);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void moveFtShots(ArrayList<Boss1Bullet> tk2bullets) {
        for(int i=0; i<tk2bullets.size(); i++) {
            tk2bullets.get(i).move();
        }
    }

    private void moveTk() {
        if(tk.y < 500 && goingDownTk)
            tk.y++;
        else if(tk.y > 100 && !goingDownTk)
            tk.y--;
        
        if(tk.y == 500)
            goingDownTk = false;
        
        if(tk.y == 100)
            goingDownTk = true;

        
        if(tk.powerUpK.equals("red")) {
            g.setColor(Color.black);
            g.drawOval(tk.x, tk.y, 40, 40);
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("?", tk.x, tk.y);
        }

        if(tk.powerUpK.equals("magenta")) {
            g.setColor(Color.black);
            g.drawOval(tk.x, tk.y, 40, 40);
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("?", tk.x, tk.y);
        }
    }
    
    private void moveTk2() {
        if(tk2.y < 500 && goingDownTk2)
            tk2.y++;
        else if(tk2.y > 100 && !goingDownTk2)
            tk2.y--;
        
        if(tk2.y == 500)
            goingDownTk2 = false;
        
        if(tk2.y == 100)
            goingDownTk2 = true;
    
        if(tk2.powerUpK.equals("red")) {
            g.setColor(Color.black);
            g.drawOval(tk2.x, tk2.y, 40, 40);
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("?", tk2.x, tk2.y);
        }

        if(tk2.powerUpK.equals("magenta")) {
            g.setColor(Color.black);
            g.drawOval(tk2.x, tk2.y, 40, 40);
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.PLAIN, 12));
            g.drawString("?", tk2.x, tk2.y);
        }
    }
    
    public void setGame() {
        points = 0;
        life = 30;
        ee.clear();
        globals = Globals.getInstance();
        globals.g = p.getGraphics();
        Thread t = new Thread() {
            public void run() {
                boolean ll2 = false;
                boolean ll1 = false;
                Random rand = new Random();
                while(true && !menu) {
                    if(menu) {
                        points = 0;
                        life = 30;
                        ee.clear();

                        try {
                            Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                            g.drawImage(iii, 0, 0, 1100, 750, null);
                        } catch(Exception e) {}

                        g.setColor(Color.white);

                        g.setFont(new Font("arial",Font.BOLD,30));
                        g.drawString("Super Crossfire 20", 10, 50);

                        g.setFont(new Font("arial",Font.BOLD,15));
                        g.drawString("> PLAY NEW GAME", 10, 100);
                        g.drawString("Exit Game", 10, 150);
                        break;
                    }
                    if (!menu) {
                        if(ee.size() == 0) {
                            if(aerialFightAgain && totank < 2) {
                                steeps = 0;
                                aerialFightAgain = false;
                                notk = true;
                            }
                            steeps++;
                            if(steeps <= 2) {
                                Random rrand = new Random();
                                for(int s = 0; s < 12; s++) {
                                    int v = rrand.nextInt(1100);
                                    int w = -300 + rrand.nextInt(300);
                                    EnemyJet ej = new EnemyJet();
                                    ej.x = v;
                                    ej.y = w;
                                    ee.add(ej);
                                }
                                greatYouKilledEmAll();
                                life+=6;
                                v = rand.nextInt(2);
                                try {
                                    for(int s = 0; s<12; s++) {
                                        int v = 1 + rand.nextInt(30);
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

                                }
                                tk = new Tank();
                                tk.x = 600 + rand.nextInt(200);
                                tk.y = 200;

                                tk2 = new Tank();
                                tk2.x = 170 + rand.nextInt(300);
                                tk2.y = -100;
                            }
                            else if(notk) {

                                notk = false;

                                Random ran = new Random();

                                tk = new Tank();
                                tk.x = 600 + ran.nextInt(200);
                                tk.y = 200;

                                tk2 = new Tank();
                                tk2.x = 170 + ran.nextInt(300);
                                tk2.y = -100;

                                totank ++;
                            }

                        }
                        if(ee.size() > 0) {
                            for(int s=0; s<ee.size(); s++) {
                                if(ee.get(s).y < 350 && cc == 0)
                                    ee.get(s).y+=4;
                                else
                                    cc++;
                                if(cc > 12) {
                                    cc = 0;
                                }
                                if(cc > 0 && cc < 12) {
                                    ee.get(s).y-=7;
                                }
                            }
                        }
                        
                        if(ee.size() == 1 && bombers.size() == 0) {
                            int v = rand.nextInt(10);
                            if(v == v) {
                                for(int s = 0; s<10; s++) {
                                    Bomber bom = new Bomber();
                                    bom.y = 100;
                                    bom.x = 1100 + s*300;
                                    bombers.add(bom);
                                }
                            }
                        }

                        if(bombers.size() > 0) {
                            for(int s = 0; s<bombers.size(); s++) {
                                bombers.get(s).x -= 20;
                            }
                        }
                        
                        if(bombers.size() > 0) {
                            for(int s = 0; s<bombers.size(); s++) {
                                if(bombers.get(s).x < 0)
                                    bombers.remove(bombers.get(s));
                            }
                        }
                        
                        if(bos.size() > 0) {
                            for(int s = 0; s<bos.size(); s++) {
                                bos.get(s).x += bos.get(s).xm;
                                bos.get(s).y += bos.get(s).ym;
                            }
                        }
                        
                        if(bs1 == null && totank == 3) {
                            bs1 = new Boss1();
                            bs1.x = 300;
                            bs1.y = 150;

                        }
                        if(bs2 == null && (totank == 6 || totank == 8 || totank == 10 || totank == 12 || totank == 14 || totank == 16 || totank == 18 || totank == 20)) {
                            bs2 = new Boss2();
                            bs2.x = 300;
                            bs2.y = 150;

                            if(totank >= 14)
                                boss2str = "heli.gif";
                        }

                        if(ft.size() > 0) {
                            for(int s=0; s<ft.size(); s++) {
                                if(ft.get(s).y < 350 && dd == 0)
                                    ft.get(s).y+=4;
                                else
                                    dd++;
                                if(dd > 12) {
                                    dd = 0;
                                }
                                if(dd > 0 && dd < 12) {
                                    ft.get(s).y-=7;
                                }
                            }
                        }
                        try {
                            j.setTitle("Points: " + points + ", Life: " + life);
                            for(int s = 0; s<12; s++) {
                                int v = rand.nextInt(165);
                                if(v == 0) {
                                    if(s < ee.size())
                                        ee.get(s).shoot();
                                    
                                    initClouds();
                                    initClouds2();
                                }
                            }

                            String kkind = "red";
                            int ggb = rand.nextInt(2);
                            if(ggb == 0) {
                                kkind = "red";
                            }
                            else if(ggb == 1) {
                                kkind = "magenta";
                            }
                            
                            if(tk2 != null)
                            if(tk2.life <= 0) {
                                tk2.life = 0;
                                if(kkind.equals("red")) {
                                    pu = new RedPowerUp();
                                    pu.x = tk2.x;
                                    pu.y = tk2.y;
                                    tk2.powerUpK = "red";
                                    powerUpKind = "red";
                                }
                                if(kkind.equals("magenta")) {
                                    pu2 = new MagentaPowerUp();
                                    pu2.x = tk2.x;
                                    pu2.y = tk2.y;
                                    tk2.powerUpK = "magenta";
                                    powerUpKind = "magenta";
                                }
                                tk2 = null;
                                ll2 = true;
                            }

                            if(tk != null) {
                                if(tk.life <= 0) {
                                    tk.life = 0;
                                    if(kkind.equals("red")) {
                                        pu = new RedPowerUp();
                                        pu.x = tk.x;
                                        pu.y = tk.y;
                                        tk.powerUpK = "red";
                                        powerUpKind = "red";
                                    }
                                    if(kkind.equals("magenta")) {
                                        pu2 = new MagentaPowerUp();
                                        pu2.x = tk.x;
                                        pu2.y = tk.y;
                                        tk.powerUpK = "magenta";
                                        powerUpKind = "magenta";
                                    }
                                    tk = null;
                                    ll1 = true;
                                    bs1went = true;
                                }
                            }

                            if(bs2 != null) {
                                if(bs2.life <= 0) {
                                    bs2.life = 0;
                                    bs2 = null;
                                    if (totank == 20) {
                                        JOptionPane.showMessageDialog(null, "You killed 'em all. so you won!");
                                        menu = true;
                                    }
                                }
                            }

                            if(bs1 != null) {
                                if(bs1.life <= 0) {
                                    bs1.life = 0;
                                    bs1 = null;
                                }
                            }

                            if(tk2 != null) {
                                if(tk2.life <= 0) {
                                    tk2.life = 0;
                                    tk2 = null;
                                    ll2 = true;
                                    bs1went = true;
                                }
                            }

                            if(pu != null) { 
                                pu.draw();
                                if(ateRedPowerUp())
                                    pu = null;
                            }

                            if(pu2 != null) { 
                                pu2.draw();
                                if(ateMagentaPowerUp())
                                    pu2 = null;
                            }

                            if(ll2 && ll1) {
                                aerialFightAgain = true;
                                ll2 = false;
                                ll1 = false;
                            }

                            if(tk2 != null)
                                checkForTank2Shot();
                            if(tk != null)
                                checkForTankShot();
                            if(bs1 != null)
                                checkForBoss1Shot();
                            if(bs2 != null)
                                checkForBoss2Shot();
                            checkForFtShot();
                            checkForEnemyShot();
                            checkForBomberGotShot();
                            checkForEnemyShotShot();
                            checkForBomberShotShot();
                            int v = rand.nextInt(165);
                            if(v == 0 && tk2 != null) {
                                tk2.shoot();
                            }
                            isHeroShotByBomber();
                            if(isHeroShot() || isHeroShotTk()) {
                                life--;
                                powerUp = 1;
                            }
                            if(life <= 0)
                                menu = true;
                            if(tk2 != null)
                                moveTk2Shots();
                            if(tk != null)
                                moveTkShots();

                            game.moveShots(shots);
                            if((powerUp == 0 || powerUp == 1) && powerUpKind.equals("red")) {
                                game.moveLasers(lasers);
                            }
                            else {
                                for(int s = 0; s<lasers.size(); s++) {
                                    lasers.get(s).x += lasers.get(s).powerUpRedMove;
                                    lasers.get(s).y -= 30;
                                }
                            }
                            Thread.sleep(70);
                        } catch(Exception e) {

                        }
                        for(int s = 0; s<12; s++) {
                            try {
                                ee.get(s).move();
                            } catch(Exception npe) {

                            }
                        }
                        if(ft.size() > 0) {
                            for(int s = 0; s<12; s++) {
                                try {
                                    ft.get(s).move();
                                } catch(Exception npe) {

                                }
                            }
                        }
                        if(ft.size() > 0) {
                            for(int s = 0; s<12; s++) {
                                int v = rand.nextInt(165);
                                if(v == 0) {
                                    if(s < ft.size())
                                        ft.get(s).shoot();
                                }
                            }
                        }
                        moveFtShots(tk2bullets);
                        drawField();
                        for(Cloud cloud: clouds) {
                            cloud.x+=20;
                        }
                        for(Cloud2 cloud: clouds2) {
                            cloud.x-=20;
                        }
                        drawClouds();
                        drawEnemyJet();
                        drawFlatTank();
                        if(tk != null) {
                            drawTank();
                            moveTk();
                        }
                        if(tk2 != null) {
                            drawTank2();
                            moveTk2();
                            drawTank2Bullets();
                        }
                        game.drawHeroJet(heroImg);
                        if(bombers.size() > 0) {
                            for(int s = 0; s< bombers.size(); s++) {
                                int v = rand.nextInt(10);
                                bombers.get(s).draw();
                                if(v == 0) {
                                    bombers.get(s).shoot();
                                }
                            }
                        }
                        if(bos.size() > 0) {
                            for(int s = 0; s< bos.size(); s++) {
                                final BomberShot b = bos.get(s);
                                Thread t = new Thread() {
                                    public void run() {
                                        try {
                                            Thread.sleep(100);
                                        } catch(Exception e) {}
                                        b.move();
                                        if(b.hj < 300)
                                            b.hj+=40;
                                    }
                                };
                                t.start();
                                drawBomberShot(bos.get(s));
                            }
                        }
                        if(isHeroShotBoms()) {
                            life --;
                        }
                        if(bos.size() > 0) {
                            for(int s = 0; s< bos.size(); s++) {
                                final BomberShot b = bos.get(s);
                                Thread t = new Thread() {
                                    public void run() {
                                        try {
                                            Thread.sleep(100);
                                        } catch(Exception e) {}
                                        if(b.hj > 20)
                                            b.hj-=40;
                                    }
                                };
                                t.start();
                                drawBomberShot(bos.get(s));
                            }
                        }
                        if(ft.size() == 0 && totank >= 1 && bs1 == null && bs2 == null) {
                            Random rrand = new Random();
                            for(int s = 0; s < 12; s++) {
                                int v = rrand.nextInt(1100);
                                int w = -300 + rrand.nextInt(300);
                                FlatTank ej = new FlatTank();
                                ej.x = v;
                                ej.y = w;
                                ft.add(ej);
                                greatYouKilledEmAll();
                            }
                            totank ++;
                        }
                        if(bs1 != null) {
                            Random rrr = new Random();
                            int v = rrr.nextInt(42);
                            if(v == 0) {
                                bs1.shoot();
                            }
                            drawBoss1();
                            drawBoss1Bullets();
                            moveBoss1Bullets();
                        }
                        if(bs2 != null) {
                            Random rrr = new Random();
                            int v = rrr.nextInt(16);
                            if(v == 0) {
                                bs2.shoot();
                            }
                            drawBoss2();
                            drawBoss2Bullets();
                            moveBoss2Bullets();
                        }
                        drawTank2Bullets();
                        for(int h=0; h<exs.size(); h++) {
                            checkExplode(exs.get(h));
                            exs.get(h).explo++;
                        }
                        for(int h=0; h<exs.size(); h++) {
                            if(exs.get(h).explo > 2) {
                                exs.remove(exs.get(h));
                            }
                        }
                        game.drawShots(shots, enemyBulletImg);
                        game.drawLasers(lasers, bulletImg);
                    }
                }

                try {
                    Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                    g.drawImage(iii, 0, 0, 1100, 750, null);
                } catch(Exception e) {}

                g.setColor(Color.white);

                g.setFont(new Font("arial",Font.BOLD,30));
                g.drawString("Super Crossfire 20", 10, 50);

                g.setFont(new Font("arial",Font.BOLD,15));
                g.drawString("> PLAY NEW GAME", 10, 100);
                g.drawString("Exit Game", 10, 150);
 
                BufferedImage boss1Img = null;
                if(boss1Img == null) {
                    try {
                        boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                        g.drawImage(boss1Img, 300, 300, null);
                    } catch(Exception e) {

                    }
                }
            }
                
        };
        t.start();
    }
    
    private void moveTk2Shots() {
        for(int s = 0; s<tk2bullets.size(); s++) {
            tk2bullets.get(s).move();
        }
    }

    private void moveTkShots() {
        for(int s = 0; s<tkbullets.size(); s++) {
            tkbullets.get(s).move();
        }
    }
    
    private void moveBoss2Bullets() {
        for(int s = 0; s<bs2bullets.size(); s++) {
            bs2bullets.get(s).move();
        }
    }

    private void moveBoss1Bullets() {
        for(int s = 0; s<bs1bullets.size(); s++) {
            bs1bullets.get(s).move();
        }
    }

    private void drawTank2Bullets() {
        for(int s = 0; s<tk2bullets.size(); s++) {
            drawTank2Bullet(tk2bullets.get(s));
        }
    }
    
    private void drawBoss2Bullets() {
        for(int s = 0; s<bs2bullets.size(); s++) {
            drawBoss2Bullet(bs2bullets.get(s));
        }
    }

    private void drawBoss1Bullets() {
        for(int s = 0; s<bs1bullets.size(); s++) {
            drawBoss1Bullet(bs1bullets.get(s));
        }
    }

    private void fire(ArrayList<Bullet> lasers) {
        Bullet b = new Bullet(lasers);
        b.x = globals.xx + 27;
        b.y = globals.i - 10;
        shootsound();
        if((powerUp == 0 || powerUp == 1) && powerUpKind.equals("red")) {
            lasers.add(b);
        }
        else if(powerUpKind.equals("red")) {
            lasers.add(b);

            if(powerUp == 2) {

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 21;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 41;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);
            } else if(powerUp == 3) {
                
                b = new Bullet(lasers);
                b.x = globals.xx + 11;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 21;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 41;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);
                
                b = new Bullet(lasers);
                b.x = globals.xx + 51;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);
            } else if(powerUp == 4) {
                
                b = new Bullet(lasers);
                b.x = globals.xx - 11;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 11;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 21;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 41;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);
                
                b = new Bullet(lasers);
                b.x = globals.xx + 51;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 71;
                b.y = globals.i - 10;
                b.powerUpRedMove = -3;
                lasers.add(b);
            }
        }
        else if(powerUpKind.equals("magenta")) {
            lasers.add(b);

            if(powerUp == 1) {

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -5;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 30;
                b.powerUpMagentaMove = -5;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 50;
                b.powerUpMagentaMove = -5;
                lasers.add(b);
            }
            else if(powerUp == 2) {

                b = new Bullet(lasers);
                b.x = globals.xx + 21;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -15;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 41;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -15;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -5;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 30;
                b.powerUpMagentaMove = -10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 50;
                b.powerUpMagentaMove = -10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 70;
                b.powerUpMagentaMove = -5;
                lasers.add(b);
            }
            else if(powerUp == 3) {

                b = new Bullet(lasers);
                b.x = globals.xx + 11;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -25;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 51;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -25;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 21;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -15;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 41;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -15;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 10;
                b.powerUpMagentaMove = -5;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 30;
                b.powerUpMagentaMove = -10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 50;
                b.powerUpMagentaMove = -10;
                lasers.add(b);

                b = new Bullet(lasers);
                b.x = globals.xx + 31;
                b.y = globals.i - 70;
                b.powerUpMagentaMove = -5;
                lasers.add(b);
            }
        }
    }

    private void drawField() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(fieldImg1 == null) {
            try {
                fieldImg1 = ImageIO.read(getClass().getResourceAsStream("field.png"));
            } catch(Exception e) {
                
            }
        }
        if(fieldImg3 == null) {
            try {
                fieldImg3 = ImageIO.read(getClass().getResourceAsStream("field.png"));
            } catch(Exception e) {
                
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
                
            }
        }
        
        globals.y+=1;

        if(globals.y < 760) {
            g.drawImage(fieldImg3, 0, globals.y - 760 - 760, 1100, 760, null);
            g.drawImage(fieldImg2, 0, globals.y - 760, 1100, 760, null);
            g.drawImage(fieldImg1, 0, globals.y, 1100, 760, null);
        }
        else if(globals.y >= 760 && globals.y < 1520) {
            g.drawImage(fieldImg1, 0, globals.y - 760 - 760 - 760, 1100, 760, null);
            g.drawImage(fieldImg3, 0, globals.y - 760 - 760, 1100, 760, null);
            g.drawImage(fieldImg2, 0, globals.y - 760, 1100, 760, null);
        }
        else if(globals.y >= 1520) {
            globals.y = 0;
        }
//        g.setColor(Color.white);
//        g.fillRect(0, 0, 1100, 800);
    }

    private BufferedImage flip(BufferedImage sprite){
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
    }

    private void drawEnemyJet() {
        if(g == null) {
            g = p.getGraphics();
        }
        try {
            if(v == 0)
                enemyImg = ImageIO.read(getClass().getResourceAsStream("flattank.png"));
            else if(v == 1)
                enemyImg = ImageIO.read(getClass().getResourceAsStream("enemy3.png"));

            globals.steps++;
        } catch(Exception e) {

        }
        for(int s = 0; s<12; s++) {
            if(s < ee.size())
                g.drawImage(enemyImg, ee.get(s).x, ee.get(s).y, 80, 100, null);
        }
    }
    
    private void drawBomberShot(BomberShot boms) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(bomShotImg == null) {
            try {
                bomShotImg = ImageIO.read(getClass().getResourceAsStream("missileboss1.png"));
            } catch(Exception e) {

            }
        }
        g.drawImage(bomShotImg, boms.x, boms.y, 20, 40, null);
    }

    private void drawFlatTank() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(flatTankImg == null || totank > 4) {
            if(totank <= 4)
            try {
                for(int s = 0; s<12; s++) {
                    int v = 100 + rand.nextInt(30);
                    int w = 1 + rand.nextInt(30);
                    ft.get(s).x = w*v;
                    int vv = rand.nextInt(3) + 1;
                    if(vv == 1)
                        ft.get(s).y = 100;
                    if(vv == 2)
                        ft.get(s).y = 200;
                    if(vv == 3)
                        ft.get(s).y = 300;
                }
            } catch(Exception e) {
                
            }
            try {
                if(totank > 10) {
                    javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource("enemysoldier2.gif"));
                    flatTankImg = iFb.getImage();
                } else {
                    javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource("flattank.png"));
                    flatTankImg = iFb.getImage();
                }
            } catch(Exception e) {
                
            }
        }
        for(int s = 0; s<12; s++) {
            if(s < ft.size()) {
                if(totank > 10)
                   g.drawImage(flatTankImg, ft.get(s).x, ft.get(s).y, 20, 35, null);
                else
                   g.drawImage(flatTankImg, ft.get(s).x, ft.get(s).y, 80, 100, null);
            }
        }
    }

    private void checkExplode(Explode ee) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(xp == null) {
            try {
                javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource("explode.gif"));
                xp = iFb.getImage();
            } catch(Exception e) {
                
            }
        }
        g.drawImage(xp, ee.x, ee.y, 100, 100, null);
        explodesound();
    }
    
    private void drawTank2() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(tankImg2 == null) {
            try {
                javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource("dragon.gif"));
                tankImg2 = iFb.getImage();
            } catch(Exception e) {
                
            }
        }
        g.drawImage(tankImg2, tk2.x, tk2.y, 150, 150, null);
    }

    private void drawBoss2Bullet(Boss2Bullet b) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(boss2BulletImg == null) {
            try {
                boss2BulletImg = ImageIO.read(getClass().getResourceAsStream("missileboss1.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(boss2BulletImg, b.x, b.y, 16, 47, null);
    }
    
    private void drawBoss2() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(boss2Img == null || totank >= 8) {
            try {
                javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource(boss2str));
                boss2Img = iFb.getImage();
            } catch(Exception e) {
                
            }
        }
        g.drawImage(boss2Img, bs2.x, bs2.y, 350, 200, null);
    }

    private void drawBoss1Bullet(Boss1Bullet b) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(boss1BulletImg == null) {
            try {
                boss1BulletImg = ImageIO.read(getClass().getResourceAsStream("missileboss1.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(boss1BulletImg, b.x, b.y, 16, 47, null);
    }
    
    private void drawBoss1() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(boss1Img == null) {
            try {
                boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(boss1Img, bs1.x, bs1.y, 350, 200, null);
    }
    
    private void drawClouds() {
        for(int i=0; i<clouds.size(); i++) {
            drawCloud(clouds.get(i));
        }
        for(int i=0; i<clouds2.size(); i++) {
            drawCloud(clouds2.get(i));
        }
    }

    private void drawCloud(Cloud cloud) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(cloudImg == null) {
            try {
                cloudImg = ImageIO.read(getClass().getResourceAsStream("cloud.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(cloudImg, cloud.x, cloud.y, 200, 110, null);
    }

    private void drawCloud(Cloud2 cloud) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(cloudImg == null) {
            try {
                cloudImg = ImageIO.read(getClass().getResourceAsStream("cloud.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(cloudImg, cloud.x, cloud.y, 200, 110, null);
    }
    
    private void drawTank2Bullet(Boss1Bullet tb) {
        if(g == null) {
            g = p.getGraphics();
        }
        if(tank2BulletImg == null) {
            try {
                tank2BulletImg = ImageIO.read(getClass().getResourceAsStream("tankbullet.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(tank2BulletImg, tb.x, tb.y, 15, 45, null);
    }

    private void drawTank() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(tankImg == null) {
            try {
                javax.swing.ImageIcon iFb = new javax.swing.ImageIcon(this.getClass().getResource("dragon.gif"));
                tankImg = iFb.getImage();
            } catch(Exception e) {
                
            }
        }
        g.drawImage(tankImg, tk.x, tk.y, 150, 150, null);
    }

    private void drawGuiHere() {
        j.setLayout(null);
        j.setBounds(0, 0, 1100, 830);
        p.setBounds(0, 0, 1100, 760);
        jp.setBounds(0, 760, 1100, 70);
        jp.setBackground(Color.GREEN);
        j.addKeyListener(this);
        j.add(jp);
        b.setBounds(0, 740, 300, 30);
        b.setText("Quit to Main Menu");
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    Image iii = ImageIO.read(getClass().getResourceAsStream("def.png"));
                    g.drawImage(iii, 0, 0, 1100, 750, null);
                } catch(Exception ee) {}

                g.setColor(Color.white);

                g.setFont(new Font("arial",Font.BOLD,30));
                g.drawString("Super Crossfire 20", 10, 50);

                g.setFont(new Font("arial",Font.BOLD,15));
                g.drawString("> PLAY NEW GAME", 10, 100);
                g.drawString("Exit Game", 10, 150);

                BufferedImage boss1Img = null;
                if(boss1Img == null) {
                    try {
                        boss1Img = ImageIO.read(getClass().getResourceAsStream("boss1b.png"));
                        g.drawImage(boss1Img, 300, 300, null);
                    } catch(Exception ee) {

                    }
                }

                a = true;
                menu = true;
                j.setFocusable(true);
                j.requestFocus();
          }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        j.setFocusable(true);
        
        j.add(p);
        jp.add(b);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        j.setVisible(true);
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        int framewidth=j.getSize().width;//get the width of the frame
        int frameheigth=j.getSize().height; //get the heigth of the frame
        int framelocationX=(dim.width-framewidth)/2; 
        int framelocationY=(dim.height-frameheigth)/2;
        j.setLocation(framelocationX,framelocationY);
    }
    
    public void setGui() {
        drawGuiHere();
    }
    
    public static void main(String[] args) {
        new Interfire();
    }
}