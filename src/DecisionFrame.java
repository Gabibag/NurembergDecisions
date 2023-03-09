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
        switch (from) {
            case "left" -> {
                currentX -= frame.getWidth();
                currentY = endY;
                frame.setLocation(currentX + frame.getWidth(), currentY);
                frame.setVisible(true);
                while (currentX < endX) {
                    moveSpeed = easing(currentX, endX+currentX, false);
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
                    moveSpeed = easing(currentX, endX-currentX, false);
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
                    moveSpeed = easing(currentY, endY-currentY, true);
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
                    moveSpeed = easing(currentY, endY-currentY, true);
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
    }
    public static int easing(int b, int c, boolean isY) {
        int d = 4;
        //if is y is true, subtract c from the height of the frame
        if (!isY) {
            c = height - c;
        }
        //c is the change in position
        //b is the beginning position
        //d is the multiplier
//        System.out.println("d:" + d + " b:" + b + " c:" + c + " result:" + (d + b/c) + (d+b/c < 2 ? ", gets set to 2" : ""));
        return Math.max( d + b/c, 2);

    }
}
