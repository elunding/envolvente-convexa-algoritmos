import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class DibujarConvexa extends JPanel {

    private List<Object> shapes = new ArrayList<>();

    public DibujarConvexa(ArrayList<Point2D> coordenadas) {
        int largo_coordenadas = coordenadas.size();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(512, 448));
        for (int i = 0; i < largo_coordenadas; i++) {
            int next_index = i + 1;
            if (next_index >= largo_coordenadas){
                break;
            }
            //g.drawLine((int)coordenadas.get(i).getX(), (int)coordenadas.get(i).getY());
            int x1 = (int)coordenadas.get(i).getX();
            int y1 = (int)coordenadas.get(i).getY();
            int x2 = (int)coordenadas.get(next_index).getX();
            int y2 = (int)coordenadas.get(next_index).getY();
            addLinea(x1, y1, x2, y2);
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Object s: shapes){
            if (s instanceof Linea){
                ((Linea) s).draw(g);
            }
        }
    }

    public void addLinea(int x1, int y1, int x2, int y2){
        shapes.add(new Linea(x1, y1, x2, y2));
        repaint();
    }
}