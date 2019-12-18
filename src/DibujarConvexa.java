import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DibujarConvexa extends JPanel {

    private List<Object> shapes = new ArrayList<>();

    public DibujarConvexa(ArrayList<Point2D> coordenadas, Point2D[] puntos) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 448));
        int largoCoordenadas = coordenadas.size();
        int largoPuntos = puntos.length;

        for (int i = 0; i < largoPuntos; i++) {
            agregarCirculo((int)puntos[i].getX(), (int)puntos[i].getY());
        }

        for (int i = 0; i < largoCoordenadas; i++) {
            int indiceSiguiente = i + 1;
            if (indiceSiguiente >= largoCoordenadas){
                indiceSiguiente = 0;
            }

            int x1 = (int)coordenadas.get(i).getX();
            int y1 = (int)coordenadas.get(i).getY();
            int x2 = (int)coordenadas.get(indiceSiguiente).getX();
            int y2 = (int)coordenadas.get(indiceSiguiente).getY();
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