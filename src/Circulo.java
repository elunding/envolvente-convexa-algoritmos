import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Circulo {

    int x, y, width, height;

    public Circulo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circulo = new Ellipse2D.Double(x, y, 10, 10);

        g2d.setColor(Color.RED);
        g2d.fill(circulo);
    }

}