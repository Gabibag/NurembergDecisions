import javax.swing.*;
import java.awt.*;


public class DecisionFrame extends JFrame {
    public static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(Main.getImage("test-image"), 0, 0, this);
    }
    public DecisionFrame() {
        System.out.println("a");
        //get dimensions of screen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width/3, height/3);
        setLocation(width/3, height/3);
        setTitle("Decision Frame");

        //make a timer that will change the image every 10 second

        setUndecorated(true);
        this.add(new DecisionPanel());
        setVisible(true);
    }
    public static void animate(int endX, int endY, String from, JFrame frame) {
        int moveSpeed;
        int currentX = frame.getX();
        int currentY = frame.getY();
        System.out.println(from);
        switch (from) {
            case "left" -> {
                currentX -= frame.getWidth();
                currentY = endY;
                frame.setLocation(currentX + frame.getWidth(), currentY);
                frame.setVisible(true);
                while (currentX < endX) {
                    System.out.println(currentX);
                    moveSpeed = easing(currentX, endX-currentX);
                    currentX += moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "right" -> {
                currentX -= frame.getWidth();
                currentY = endY;
                frame.setLocation(currentX - frame.getWidth(), currentY);
                frame.setVisible(true);
                while (currentX > endX) {
                    moveSpeed = easing(currentX, endX-currentX);
                    System.out.println(currentX);
                    currentX -= moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "up" -> {
                currentX = endX;
                frame.setLocation(currentX, currentY + frame.getHeight());
                frame.setVisible(true);
                while (currentY < endY ) {
                    moveSpeed = easing(currentY, endY-currentY);
                    currentY += moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "down" -> {
                currentX = endX;
                currentY = frame.getHeight();
                frame.setLocation(currentX, currentY - frame.getHeight());
                frame.setVisible(true);
                while (currentY > endY) {
                    moveSpeed = easing(currentY, endY-currentY);
                    currentY -= moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("done");
    }
    public static int easing( int b, int c) {
        //return c* t / d + b, if the result is less than 1, return 1
        //c is the change in position
        //b is the beginning position
        //d is the duration
        int d = 4;
        System.out.println((d + b)/c);
        return Math.max( d + b/c, 1);

    }
}
