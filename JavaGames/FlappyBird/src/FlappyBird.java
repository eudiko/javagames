import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardwidth =360;
    int boardheight = 640;

    Image backgroundImg;
    Image birdImg;
    Image toppipeImg;
    Image bottompipeImg;

    //bird
    int birdX = boardwidth/8;
    int birdY=  boardheight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img = img;
        }
    }

    //pipes
    int pipex = boardwidth;
    int pipey = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        int x = pipex;
        int y = pipey;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false;

        Pipe(Image img){
            this.img = img;
        }
    }

    //logic
    Bird bird;
    int velocityX= -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer palcePipesTimer;

    FlappyBird()
    {
        setFocusable(true);
        addKeyListener(this);

        setPreferredSize(new Dimension(boardwidth,boardheight));
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        toppipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottompipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //pipes timer
        palcePipesTimer =  new Timer(1500, new ActionListener(){
            @Override
            public  void actionPerformed(ActionEvent e){
                PlacePipes();
            }

        });
        palcePipesTimer.start();

        //gametimer
        gameLoop = new Timer(1000/60,this);
        gameLoop.start();
    }

    public void PlacePipes(){
        int randomPipeY = (int)(pipey-pipeHeight/4-Math.random()*pipeHeight/2);
        Pipe  topPipe = new Pipe(toppipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, this.boardwidth,this.boardheight,null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width,bird.height,null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width,pipe.height,null);
        }
    }

    public void move(){
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y = Math.max(bird.y,0);

        //pipes
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.x+=velocityX;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
