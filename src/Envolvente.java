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
                // si producto_vectorial > 0 entonces puntos[i] esta a la izq del vector
                // punto_actual --> punto_siguiente
                if(producto_vectorial > 0){
                    // como esta a la izquierda, quiere decir que es un punto mas exterior,
                    // por lo tanto sera el siguiente punto
                    punto_siguiente = puntos[i];
                    puntos_colineares = new ArrayList<>();
                // si producto_vectorial = 0, entonces los puntos son colineares
                } else if(producto_vectorial == 0){
                    // si se cumple este if, punto_siguiente esta mas cerca a punto_actual que
                    // puntos[i]
                    if(distancia_entre(punto_actual, punto_siguiente, puntos[i]) < 0){
                        // por lo que se añade punto_siguiente a los puntos_colineares
                        // y siendo el siguiente_punto puntos[i]
                        puntos_colineares.add(punto_siguiente);
                        punto_siguiente = puntos[i];
                    } else { puntos_colineares.add(puntos[i]); }
                }
            }
            for(Point2D p: puntos_colineares){ resultados.add(p); }
            // condicion de termino (si el punto_siguiente igual a punto_inicial hemos
            // llegado al fin del recorrido).
            if(punto_siguiente == punto_inicial){ break; }
            resultados.add(punto_siguiente);
            punto_actual = punto_siguiente;
        }

        return new ArrayList<>(resultados);
    }

    /**
     * Producto cruz o vectorial para encontrar la posición de p3 con respecto
     * al vector p1p2.
     *
     * @param p1 Point2D
     * @param p2 Point2D
     * @param p3 Point2D
     * @return > 0 si p3 esta a la izq de p1p2
     *         == 0 si p1, p2 y p3 son colineares
     *         < 0 si p3 esta a la derecha de p1p2
     */
    private static int productoVectorial(Point2D p1, Point2D p2, Point2D p3){

        double x1 = p1.getX() - p2.getX();
        double x2 = p1.getX() - p3.getX();
        double y1 = p1.getY() - p2.getY();
        double y2 = p1.getY() - p3.getY();

        return (int)(y2 * x1 - y1 * x2);
    }

    /**
     *
     * @param p1 Point2D
     * @param p2 Point2D
     * @param p3 Point2D
     * @return < 0 si 'p2' esta mas cerca que 'p1' en comparacion a 'p3'
     *         == 0 si 'p2' y 'p3' estan a la misma distancia de 'p1'
     *         > 0 si 'p3' esta mas cerca de 'p1' que 'p2'
     */
    private static int distancia_entre(Point2D p1, Point2D p2, Point2D p3){
        double x1 = p1.getX() - p2.getX();
        double x2 = p1.getX() - p3.getX();
        double y1 = p1.getY() - p2.getY();
        double y2 = p1.getY() - p3.getY();

        return (Double.compare(y1 * y1 + x1 * x1, y2 * y2 + x2 * x2));
    }
}
