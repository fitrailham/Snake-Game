package com.gameTutorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXlenght = new int[750];
    private int[] snakeYlenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthofsnake = 3;

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeimage;

    private int [] enemyXpos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int [] enemyYpos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};

    private ImageIcon enemyimage;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private int score = 0;

    private int moves =0;


    private ImageIcon titleImage;

    public Gameplay(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){

        if(moves == 0){
            snakeXlenght[2] = 50;
            snakeXlenght[1] = 75;
            snakeXlenght[0] = 100;

            snakeYlenght[2] = 100;
            snakeYlenght[1] = 100;
            snakeYlenght[0] = 100;
        }

        // draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);

        //draw the title image
        titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this,g,25,11);

        // draw border for game play
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        // draw background for the gameplay
        g.setColor(Color.black);
        g.fillRect(25,75,850,575);

        // draw score
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN,14));
        g.drawString("Score: "+score, 780,30);

        // drea length
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Length: "+lengthofsnake, 780, 50);


        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g,snakeXlenght[0],snakeYlenght[0] );

        for(int a = 0; a< lengthofsnake; a++){
            if(a==0 && right){
                rightmouth = new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g,snakeXlenght[a],snakeYlenght[a] );
            }
            if(a==0 && left){
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g,snakeXlenght[a],snakeYlenght[a] );
            }
            if(a==0 && down){
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g,snakeXlenght[a],snakeYlenght[a] );
            }
            if(a==0 && up){
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g,snakeXlenght[a],snakeYlenght[a] );
            }

            if(a!=0){
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g,snakeXlenght[a],snakeYlenght[a] );
            }
        }

        enemyimage = new ImageIcon("enemy.png");
        if((enemyXpos[xpos] == snakeXlenght[0]) && enemyYpos[ypos] == snakeYlenght[0]){
            score++;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this,g,enemyXpos[xpos], enemyYpos[ypos]);

        for(int b = 1; b<lengthofsnake; b++){
            if(snakeXlenght[b] == snakeXlenght[0] && snakeYlenght[b] == snakeYlenght[0]){
                right = false;
                left =false;
                up = false;
                down = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD,50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD,20));
                g.drawString("Space to RESTART", 350, 340);
            }
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(right){
            for(int r = lengthofsnake-1; r>=0; r--){
                snakeYlenght[r+1] = snakeYlenght[r];
            }
            for(int r =lengthofsnake; r>=0; r--){
                if(r==0){
                    snakeXlenght[r] = snakeXlenght[r] + 25;
                }else{
                    snakeXlenght[r] = snakeXlenght[r-1];
                }
                if(snakeXlenght[r] > 850){
                    snakeXlenght[r] = 25;
                }
            }
            repaint();
        }
        if(left){
            for(int r = lengthofsnake-1; r>=0; r--){
                snakeYlenght[r+1] = snakeYlenght[r];
            }
            for(int r =lengthofsnake; r>=0; r--){
                if(r==0){
                    snakeXlenght[r] = snakeXlenght[r] - 25;
                }else{
                    snakeXlenght[r] = snakeXlenght[r-1];
                }
                if(snakeXlenght[r] < 25){
                    snakeXlenght[r] = 850;
                }
            }
            repaint();
        }
        if(up){
            for(int r = lengthofsnake-1; r>=0; r--){
                snakeXlenght[r+1] = snakeXlenght[r];
            }
            for(int r =lengthofsnake; r>=0; r--){
                if(r==0){
                    snakeYlenght[r] = snakeYlenght[r] - 25;
                }else{
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if(snakeYlenght[r] < 75){
                    snakeYlenght[r] = 625;
                }
            }
            repaint();
        }
        if(down){
            for(int r = lengthofsnake-1; r>=0; r--){
                snakeXlenght[r+1] = snakeXlenght[r];
            }
            for(int r =lengthofsnake; r>=0; r--){
                if(r==0){
                    snakeYlenght[r] = snakeYlenght[r] + 25;
                }else{
                    snakeYlenght[r] = snakeYlenght[r-1];
                }
                if(snakeYlenght[r] > 625){
                    snakeYlenght[r] = 750;
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // yang bagian pencet tombol

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moves++;
            right = true;
            if(!left){
                right = true;
            }else{
                right = false;
                left = true;

            }

            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            left = true;
            if(!right){
                left = true;
            }else{
                left = false;
                right = true;
            }

            up = false;
            down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            up = true;
            if(!down){
                up = true;
            }else{
                up = false;
                down = true;
            }

            right = false;
            left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            down = true;
            if(!up){
                down = true;
            }else{
                down = false;
                up = true;
            }

            right = false;
            left = false;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
