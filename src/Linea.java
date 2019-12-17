import java.awt.*;

public class Linea {

    int x1, y1, x2, y2, width, height;

    public Linea(int x1, int y1, int x2, int y2, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.width = width;
        this.height = height;

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);
        g2d.drawLine(this.x1, this.y1, this.x2, this.y2);

    }

}