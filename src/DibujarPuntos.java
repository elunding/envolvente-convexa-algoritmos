import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DibujarPuntos extends JPanel {

    private List<Object> shapes = new ArrayList<>();

    public DibujarPuntos(Point2D[] coordenadas) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 448));

        for (int i = 0; i < coordenadas.length; i++) {
            addCircle((int)coordenadas[i].getX(), (int)coordenadas[i].getY());
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object s : shapes) {
            if (s instanceof Circulo) {
                ((Circulo) s).draw(g);
            }
        }
    }

    public void addCircle(int maxX, int maxY) {
        shapes.add(new Circulo(maxX, maxY));
        repaint();
    }

}