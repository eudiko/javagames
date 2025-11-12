import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener{
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

    //logic
    Bird bird;
    int velocityY = -6;
    int gravity = 1;

    Timer gameLoop;

    FlappyBird()
    {
        setPreferredSize(new Dimension(boardwidth,boardheight));
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        toppipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottompipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        bird = new Bird(birdImg);

        //timer
        gameLoop = new Timer(1000/60,this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImg, 0, 0, this.boardwidth,this.boardheight,null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width,bird.height,null);
    }

    public void move(){
        velocityY+=gravity;
        bird.y+=velocityY;
        bird.y = Math.max(bird.y,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

}
