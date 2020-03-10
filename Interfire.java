import java.awt.Color;
import java.awt.EventQueue;
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
    
    private ArrayList<Boss1Bullet> bs1bullets = new ArrayList<>();
    
    private BufferedImage boss1BulletImg = null;

    private BufferedImage boss1Img = null;
    
    private Boss1 bs1 = null;
    
    private BufferedImage flatTankImg = null;
    
    private BufferedImage tankImg2 = null;
    
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

    private BufferedImage tankImg = null;
    
    private long points = 0;
    
    private boolean aerialFightAgain = false;
    
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
    
    private BufferedImage fieldImg3 = null;

    private ArrayList<Bullet> lasers = new ArrayList<>();

    private ArrayList<EnemyJet> ee = new ArrayList<>();
    
    private ArrayList<FlatTank> ft = new ArrayList<>();

    private ArrayList<EnemyBullet> shots = new ArrayList<>();

    private Game game = new Game();

    private boolean start2 = true;

    private RedPowerUp pu = null;
    
    private int powerUp = 1;

    private String powerUpKind = "red";
    
    public Interfire() {

        addSound();

        setGui();

        setGame();

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
    }

    class RedPowerUp {
        int x, y;
        private void draw() {
            if(g == null) {
                g = p.getGraphics();
            }
            g.setColor(Color.red);
            g.fillOval(x, y, 20, 20);
        }
    }
    
    private boolean ateRedPowerUp() {
        if(globals.xx+40 > pu.x && globals.xx+40 < pu.x + 25 &&
           globals.i+50 > pu.y && globals.i+50 < pu.y + 25) {
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

                    tk2.life--;
                    
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

                    tk.life--;

                    points += 231;
                }
            }
        } catch(Exception npe) {
            
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
                
                bs1.life --;

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

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    class Boss1Bullet {
        int x, y;
        private void move() {
            y+=5;
        }
    }
    
    public void moveFtShots(ArrayList<Boss1Bullet> tk2bullets) {
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
    
        g.setColor(Color.red);
        g.fillOval(tk2.x, tk2.y, 20, 20);
    }
    
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
    
    public void setGame() {
        globals = Globals.getInstance();
        globals.g = p.getGraphics();
        Thread t = new Thread() {
            public void run() {
                boolean ll2 = false;
                boolean ll1 = false;
                Random rand = new Random();
                while(true) {
                    System.out.println(ee.size() + "...,,,");
                    System.out.println(aerialFightAgain);
                    if(ee.size() == 0) {
                        if(aerialFightAgain && totank < 2) {
                            steeps = 0;
                            aerialFightAgain = false;
                            notk = true;
                        }
                        steeps++;
                        if(steeps <= 2) {
                            Random rrand = new Random();
                            for(int s = 0; s < 25; s++) {
                                int v = rrand.nextInt(1100);
                                int w = -300 + rrand.nextInt(300);
                                EnemyJet ej = new EnemyJet();
                                ej.x = v;
                                ej.y = w;
                                ee.add(ej);
                            }
                            greatYouKilledEmAll();
                            life+=2;
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
                            if(cc > 25) {
                                cc = 0;
                            }
                            if(cc > 0 && cc < 25) {
                                ee.get(s).y-=7;
                            }
                        }
                    }
                    if(ft.size() == 0 && totank >= 1) {
                        Random rrand = new Random();
                        for(int s = 0; s < 25; s++) {
                            int v = rrand.nextInt(1100);
                            int w = -300 + rrand.nextInt(300);
                            FlatTank ej = new FlatTank();
                            ej.x = v;
                            ej.y = w;
                            ft.add(ej);
                            greatYouKilledEmAll();
                        }
                    }
                    if(bs1 == null && totank == 2) {
                        bs1 = new Boss1();
                        bs1.x = 300;
                        bs1.y = 150;
                        totank = 111111;
                        
                    }
                    if(bs1 == null) {
                        System.out.println("totank:" + totank);
                    }
                    if(ft.size() > 0) {
                        for(int s=0; s<ft.size(); s++) {
                            if(ft.get(s).y < 350 && dd == 0)
                                ft.get(s).y+=4;
                            else
                                dd++;
                            if(dd > 25) {
                                dd = 0;
                            }
                            if(dd > 0 && dd < 25) {
                                ft.get(s).y-=7;
                            }
                        }
                    }
                    try {
                        j.setTitle("Points: " + points + ", Life: " + life);
                        for(int s = 0; s<25; s++) {
                            int v = rand.nextInt(165);
                            if(v == 0) {
                                if(s < ee.size())
                                    ee.get(s).shoot();
                            }
                        }
                        
                        if(tk2 != null)
                        if(tk2.life <= 0) {
                            tk2.life = 0;
                            pu = new RedPowerUp();
                            pu.x = tk2.x;
                            pu.y = tk2.y;
                            tk2 = null;
                            ll2 = true;
                        }
                        
                        if(tk != null) {
                            if(tk.life <= 0) {
                                tk.life = 0;
                                tk = null;
                                ll1 = true;
                            }
                        }
                        
                        if(bs1 != null) {
                            if(bs1.life <= 0) {
                                bs1.life = 0;
                                bs1 = null;
                                totank = 0;
                            }
                        }

                        if(tk2 != null) {
                            if(tk2.life <= 0) {
                                tk2.life = 0;
                                tk2 = null;
                                ll2 = true;
                            }
                        }
                        
                        if(pu != null) { 
                            pu.draw();
                            if(ateRedPowerUp())
                                pu = null;
                        }
                        
                        if(ll2 && ll1) {
                            System.out.println("all your base~");
                            aerialFightAgain = true;
                            ll2 = false;
                            ll1 = false;
                        } else {
                            System.out.println("aaaaa");
                        }
                        
                        if(tk2 != null)
                            checkForTank2Shot();
                        if(tk != null)
                            checkForTankShot();
                        if(bs1 != null)
                            checkForBoss1Shot();
                        checkForFtShot();
                        checkForEnemyShot();
                        checkForEnemyShotShot();
                        int v = rand.nextInt(165);
                        if(v == 0 && tk2 != null) {
                            tk2.shoot();
                        }
                        if(isHeroShot() || isHeroShotTk()) {
                            life--;
                            powerUp = 1;
                        }
                        if(life == 0)
                            System.exit(0);
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
                        Thread.sleep(50);
                    } catch(Exception e) {
                        
                    }
                    for(int s = 0; s<25; s++) {
                        try {
                            ee.get(s).move();
                        } catch(Exception npe) {
                            
                        }
                    }
                    if(ft.size() > 0) {
                        for(int s = 0; s<25; s++) {
                            try {
                                ft.get(s).move();
                            } catch(Exception npe) {
                                
                            }
                        }
                    }
                    if(ft.size() > 0) {
                        for(int s = 0; s<25; s++) {
                            int v = rand.nextInt(165);
                            if(v == 0) {
                                if(s < ft.size())
                                    ft.get(s).shoot();
                            }
                        }
                    }
                    moveFtShots(tk2bullets);
                    drawField();
                    if(bs1 != null) {
                        Random rrr = new Random();
                        int v = rrr.nextInt(16);
                        if(v == 0) {
                            bs1.shoot();
                        }
                        drawBoss1();
                        drawBoss1Bullets();
                        moveBoss1Bullets();
                    }
                    drawTank2Bullets();
                    drawFlatTank();
                    game.drawHeroJet(heroImg);
                    drawEnemyJet();
                    if(tk != null) {
                        drawTank();
                        moveTk();
                    }
                    if(tk2 != null) {
                        drawTank2();
                        moveTk2();
                        drawTank2Bullets();
                    }
                    game.drawShots(shots, enemyBulletImg);
                    game.drawLasers(lasers, bulletImg);
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
    
    private void drawBoss1Bullets() {
        for(int s = 0; s<bs1bullets.size(); s++) {
            drawBoss1Bullet(bs1bullets.get(s));
        }
    }

    public void fire(ArrayList<Bullet> lasers) {
        Bullet b = new Bullet(lasers);
        b.x = globals.xx + 27;
        b.y = globals.i - 10;
        if((powerUp == 0 || powerUp == 1) && powerUpKind.equals("red")) {
            lasers.add(b);
        }
        else {
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
            } else {
                
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
            }
        }
    }

    public void drawField() {
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
        
        globals.y+=2;

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
                for(int s = 0; s<25; s++) {
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
                
            }
            try {
                enemyImg = ImageIO.read(getClass().getResourceAsStream("flattank.png"));

                globals.steps++;
            } catch(Exception e) {
                
            }
        }
        for(int s = 0; s<25; s++) {
            if(s < ee.size())
                g.drawImage(enemyImg, ee.get(s).x, ee.get(s).y, 80, 100, null);
        }
    }
    
    public void drawFlatTank() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(flatTankImg == null) {
            try {
                for(int s = 0; s<25; s++) {
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
                flatTankImg = ImageIO.read(getClass().getResourceAsStream("enemy2.png"));
            } catch(Exception e) {
                
            }
        }
        for(int s = 0; s<25; s++) {
            if(s < ft.size())
                g.drawImage(flatTankImg, ft.get(s).x, ft.get(s).y, 60, 150, null);
        }
    }

    public void drawTank2() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(tankImg2 == null) {
            try {
                tankImg2 = ImageIO.read(getClass().getResourceAsStream("tank.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(tankImg2, tk2.x, tk2.y, 150, 150, null);
    }

    class Boss1 {
        int x, y;
        int life = 300;
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
    
    public void drawBoss1Bullet(Boss1Bullet b) {
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
    
    public void drawBoss1() {
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

    public void drawTank2Bullet(Boss1Bullet tb) {
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

    public void drawTank() {
        if(g == null) {
            g = p.getGraphics();
        }
        if(tankImg == null) {
            try {
                tankImg = ImageIO.read(getClass().getResourceAsStream("tank.png"));
            } catch(Exception e) {
                
            }
        }
        g.drawImage(tankImg, tk.x, tk.y, 150, 150, null);
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