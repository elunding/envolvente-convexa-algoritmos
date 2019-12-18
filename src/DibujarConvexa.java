import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DibujarConvexa extends JPanel {

    private List<Object> shapes = new ArrayList<>();
    // private int xPanel = getWidth();
    // private int yPanel = getHeight();

    public DibujarConvexa(ArrayList<Point2D> coordenadas, Point2D[] puntos) {
        super(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 448));
        int largo_coordenadas = coordenadas.size();
        int largo_puntos = puntos.length;

        for (int i = 0; i < largo_puntos; i++) {
            agregarCirculo((int)puntos[i].getX(), (int)puntos[i].getY());
        }

        for (int i = 0; i < largo_coordenadas; i++) {
            int next_index = i + 1;
            if (next_index >= largo_coordenadas){
                next_index = 0;
            }

            int x1 = (int)coordenadas.get(i).getX();
            int y1 = (int)coordenadas.get(i).getY();
            int x2 = (int)coordenadas.get(next_index).getX();
            int y2 = (int)coordenadas.get(next_index).getY();
            agregarLinea(x1, y1, x2, y2);
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (Object s: shapes){
            if (s instanceof Linea){
                ((Linea) s).draw(g);
            }
            else if(s instanceof Circulo){
                ((Circulo) s).draw(g);
            }
        }
    }

    public void agregarLinea(int x1, int y1, int x2, int y2){
        shapes.add(new Linea(x1, y1, x2, y2));
        repaint();
    }

    public void agregarCirculo(int maxX, int maxY) {
        shapes.add(new Circulo(maxX, maxY));
        repaint();
    }

}