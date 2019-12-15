import java.awt.geom.Point2D;
import java.util.*;


public class Main{
    public static void main(String[] args) {

        Point2D[] coordenadas = new Point2D[13];

        coordenadas[0] = new Point2D.Double(0, 5);
        coordenadas[1] = new Point2D.Double(-1, 1);
        coordenadas[2] = new Point2D.Double(0, 1);
        coordenadas[3] = new Point2D.Double(1, 1);
        coordenadas[4] = new Point2D.Double(-5, 0);
        coordenadas[5] = new Point2D.Double(-1, 0);
        coordenadas[6] = new Point2D.Double(0, 0);
        coordenadas[7] = new Point2D.Double(1, 0);
        coordenadas[8] = new Point2D.Double(5, 0);
        coordenadas[9] = new Point2D.Double(-1, -1);
        coordenadas[10] = new Point2D.Double(0, -1);
        coordenadas[11] = new Point2D.Double(1, -1);
        coordenadas[12] = new Point2D.Double(0, -5);

        System.out.println("Algoritmo Graham: ");
        Graham graham = new Graham();
        Stack<Point2D> poligonoConvexoGraham = graham.crearPoligonoConvexo(coordenadas);
        // imprime los resultados
        assert poligonoConvexoGraham != null;
        while (!poligonoConvexoGraham.isEmpty()) System.out.println(poligonoConvexoGraham.pop());

        System.out.println("Algoritmo envolvente (gift-wrapping): ");
        Envolvente envolvente = new Envolvente();
        ArrayList<Point2D> poligonoConvexoEnvolvente = envolvente.encontrarEnvolvente(coordenadas);
        if (poligonoConvexoEnvolvente.size() != 0)
            for(int i = 0; i < poligonoConvexoEnvolvente.size(); i++){ System.out.println(poligonoConvexoEnvolvente.get(i)); }

    }
}