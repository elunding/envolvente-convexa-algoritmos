import java.awt.geom.Point2D;
import java.util.*;

public class Envolvente {
    public static ArrayList<Point2D> encontrarEnvolvente(Point2D[] puntos){
        int largoPuntos = puntos.length;
        // validacion de parametro
        if(largoPuntos == 0) throw new IllegalArgumentException("Arreglo vacio...");


        // encontramos nuestro punto inicial, siendo el que se encuentre al extremo izquierdo
        Point2D puntoInicial = puntos[0];
        for(int i = 1; i < largoPuntos; i++){
            if(puntos[i].getX() < puntoInicial.getX()){
                puntoInicial = puntos[i];
            }
        }
        Point2D puntoActual = puntoInicial;

        // Set<Point2D> resultados = new HashSet<>();
        Stack<Point2D> resultados = new Stack<>();
        // resultados.add(puntoInicial);
        resultados.push(puntoInicial);
        List<Point2D> puntosColineares = new ArrayList<>();
        while(true){
            Point2D puntoSiguiente = puntos[0];
            for(int i = 1; i < largoPuntos; i++){
                if(puntos[i] == puntoActual) continue;
                int productoVectorial = productoVectorial(puntoActual, puntoSiguiente, puntos[i]);
                // si productoVectorial > 0 entonces puntos[i] esta a la izq del vector
                // puntoActual --> puntoSiguiente
                if(productoVectorial > 0){
                    // como esta a la izquierda, quiere decir que es un punto mas exterior,
                    // por lo tanto sera el siguiente punto
                    puntoSiguiente = puntos[i];
                    puntosColineares = new ArrayList<>();
                // si productoVectorial = 0, entonces los puntos son colineares
                } else if(productoVectorial == 0){
                    // si se cumple este if, puntoSiguiente esta mas cerca a puntoActual que
                    // puntos[i]
                    if(distanciaEntre(puntoActual, puntoSiguiente, puntos[i]) < 0){
                        // por lo que se añade puntoSiguiente a los puntosColineares
                        // y siendo el siguiente_punto puntos[i]
                        puntosColineares.add(puntoSiguiente);
                        puntoSiguiente = puntos[i];
                    } else { puntosColineares.add(puntos[i]); }
                }
            }
            // for(Point2D p: puntosColineares){ resultados.add(p); }
            for(Point2D p: puntosColineares){ resultados.push(p); }
            // condicion de termino (si el puntoSiguiente igual a puntoInicial hemos
            // llegado al fin del recorrido).
            if(puntoSiguiente == puntoInicial){ break; }
            // resultados.add(puntoSiguiente);
            resultados.push(puntoSiguiente);
            puntoActual = puntoSiguiente;
        }

        return new ArrayList<>(resultados);
    }

    /**
     * Producto cruz o vectorial para encontrar la posición de p3 con respecto
     * al vector p1p2.
     *
     * @param p1 objeto Point2D
     * @param p2 objeto Point2D
     * @param p3 objeto Point2D
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
     * @param p1 objeto Point2D
     * @param p2 objeto Point2D
     * @param p3 objeto Point2D
     * @return < 0 si 'p2' esta mas cerca que 'p1' en comparacion a 'p3'
     *         == 0 si 'p2' y 'p3' estan a la misma distancia de 'p1'
     *         > 0 si 'p3' esta mas cerca de 'p1' que 'p2'
     */
    private static int distanciaEntre(Point2D p1, Point2D p2, Point2D p3){
        double x1 = p1.getX() - p2.getX();
        double x2 = p1.getX() - p3.getX();
        double y1 = p1.getY() - p2.getY();
        double y2 = p1.getY() - p3.getY();

        return (Double.compare(y1 * y1 + x1 * x1, y2 * y2 + x2 * x2));
    }
}
