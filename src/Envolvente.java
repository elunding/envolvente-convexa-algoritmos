import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Envolvente {
    public static ArrayList<Point2D> encontrarEnvolvente(Point2D[] puntos){
        int largo_puntos = puntos.length;
        // validacion de parametro
        if(largo_puntos == 0) throw new IllegalArgumentException("Arreglo vacio...");
        // encontramos nuestro punto inicial, siendo el que se encuentre al extremo izquierdo
        Point2D punto_inicial = puntos[0];
        for(int i = 1; i < largo_puntos; i++){
            if(puntos[i].getX() < punto_inicial.getX()){
                punto_inicial = puntos[i];
            }
        }
        Point2D punto_actual = punto_inicial;

        Set<Point2D> resultados = new HashSet<>();
        resultados.add(punto_inicial);
        List<Point2D> puntos_colineares = new ArrayList<>();
        while(true){
            Point2D punto_siguiente = puntos[0];
            for(int i = 1; i < largo_puntos; i++){
                if(puntos[i] == punto_actual) continue;
                int producto_vectorial = productoVectorial(punto_actual, punto_siguiente, puntos[i]);
                if(producto_vectorial > 0){
                    punto_siguiente = puntos[i];
                    puntos_colineares = new ArrayList<>();
                } else if(producto_vectorial == 0){
                    if(distancia_entre(punto_actual, punto_siguiente, puntos[i]) < 0){
                        puntos_colineares.add(punto_siguiente);
                        punto_siguiente = puntos[i];
                    } else { puntos_colineares.add(puntos[i]); }
                }
            }
            for(Point2D p: puntos_colineares){ resultados.add(p); }
            if(punto_siguiente == punto_inicial){ break; }
            resultados.add(punto_siguiente);
            punto_actual = punto_siguiente;
        }
        return new ArrayList<>(resultados);
    }

    private static int productoVectorial(Point2D p1, Point2D p2, Point2D p3){
        double x1 = p1.getX() - p2.getX();
        double x2 = p1.getX() - p3.getX();
        double y1 = p1.getY() - p2.getY();
        double y2 = p1.getY() - p3.getY();

        return (int)(y2 * x1 - y1 * x2);
    }

    private static int distancia_entre(Point2D p1, Point2D p2, Point2D p3){
        double x1 = p1.getX() - p2.getX();
        double x2 = p1.getX() - p3.getX();
        double y1 = p1.getY() - p2.getY();
        double y2 = p1.getY() - p3.getY();

        return (Double.compare(y1 * y1 + x1 * x1, y2 * y2 + x2 * x2));
    }
    /*
    public static void main(String[] args){
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

        ArrayList<Point2D> envolvente = encontrarEnvolvente(coordenadas);

        if (envolvente.size() != 0)
            for(int i = 0; i < envolvente.size(); i++){ System.out.println(envolvente.get(i)); }
    }
     */
}
