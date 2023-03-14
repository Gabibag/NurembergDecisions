import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;


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
        setSize(width / 3, height / 3);
        setLocation(width / 3, height / 3);
        setTitle("Decision Frame");

        //make a timer that will change the image every 10 second

        setUndecorated(true);
        this.add(new DecisionPanel());
        //setOpacity(0.5f);
        this.setOpacity(0.9f);
        this.setBackground(new Color(52, 78, 65, 0));
//        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 28, 28));
        Area shape2 = new Area(
                new RoundRectangle2D.Double(0, this.getHeight() / 2 - 30, this.getWidth(), this.getHeight() / 2, 18,
                                            18));
        Area shape1 = new Area(new Rectangle(0, 0, this.getWidth(), this.getHeight() / 2));

        shape1.add(shape2);
        this.setShape(shape1);

        setVisible(true);
    }

    public static void animateLeaving(int endX, int endY, String from, JFrame frame) {
        int moveSpeed;
        int currentX = frame.getX();
        int currentY = frame.getY();
        switch (from) {
            case "left" -> {
                currentX -= frame.getWidth();
                currentY = endY;
                frame.setLocation(currentX, currentY);
                frame.setVisible(true);
                while (currentX <= endX) {
                    moveSpeed = easing(currentX, endX + currentX, false);
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
                currentX += frame.getWidth();
                currentY = endY;
                frame.setLocation(frame.getWidth(), currentY);
                frame.setVisible(true);
                while (currentX >= endX) {
                    moveSpeed = easing(currentX, endX - currentX, false);
                    currentX -= moveSpeed;
                    System.out.println(currentX);
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
                frame.setLocation(currentX, currentY);
                frame.setVisible(true);
                while (currentY < endY) {
                    moveSpeed = easing(currentY, endY - currentY, true);
                    currentY += moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.setLocation(endX, endY);
            }
            case "down" -> {
                currentX = endX;
                currentY = frame.getHeight();
                frame.setLocation(currentX, currentY - frame.getHeight());
                frame.setVisible(true);
                while (currentY >= endY) {
                    moveSpeed = easing(currentY, endY - currentY, true);
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
            //if none of the above, then just set the location
            default -> System.out.println("invalid direction \"" + from + "\"");
        }
        frame.dispose();
    }

    public static void animateMoving(int endX, int endY, String from, JFrame frame) {
        int moveSpeed;
        int currentX = frame.getX();
        int currentY = frame.getY();
        switch (from) {
            case "left" -> {
                currentX -= frame.getWidth();
                currentY = endY;
                frame.setLocation(currentX, currentY);
                frame.setVisible(true);
                while (currentX <= endX) {
                    moveSpeed = easing(currentX, endX + currentX, false);
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
                currentX += frame.getWidth();
                currentY = endY;
                frame.setLocation(frame.getWidth(), currentY);
                frame.setVisible(true);
                while (currentX >= endX) {
                    moveSpeed = easing(currentX, endX - currentX, false);
                    currentX -= moveSpeed;
                    System.out.println(currentX);
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
                frame.setLocation(currentX, currentY);
                frame.setVisible(true);
                while (currentY < endY) {
                    moveSpeed = easing(currentY, endY - currentY, true);
                    currentY += moveSpeed;
                    frame.setLocation(currentX, currentY);
                    //wait for 10 milliseconds
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                frame.setLocation(endX, endY);
            }
            case "down" -> {
                currentX = endX;
                currentY = frame.getHeight();
                frame.setLocation(currentX, currentY - frame.getHeight());
                frame.setVisible(true);
                while (currentY >= endY) {
                    moveSpeed = easing(currentY, endY - currentY, true);
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
            //if none of the above, then just set the location
            default -> System.out.println("invalid direction \"" + from + "\"");
        }
    }

    public static void animateScale(int targetWidth, int targetHeight, JFrame frame) {
        int currentHeight = frame.getHeight();
        int currentWidth = frame.getWidth();
        int heightChange = targetHeight - currentHeight;
        int widthChange = targetWidth - currentWidth;
        int heightSpeed = easing(currentHeight, heightChange, true);
        int widthSpeed = easing(currentWidth, widthChange, false);
        frame.setVisible(true);
        while (currentHeight != targetHeight || currentWidth != targetWidth) {
            if (currentHeight != targetHeight) {
                currentHeight += heightSpeed;
                frame.setSize(currentWidth, currentHeight);
            }
            if (currentWidth != targetWidth) {
                currentWidth += widthSpeed;
                frame.setSize(currentWidth, currentHeight);
            }
            //wait for 5 milliseconds
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
