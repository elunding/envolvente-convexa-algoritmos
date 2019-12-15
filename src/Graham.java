import java.awt.geom.Point2D;
import java.util.*;

public class Graham {

    // Crea el poligono convexo (como un stack de puntos)
    public static Stack<Point2D> crearPoligonoConvexo(Point2D[] coordenadas) {
        int largo_coordenadas = coordenadas.length;
        int i, j;
        if (largo_coordenadas == 0) throw new IllegalArgumentException("Arreglo de coordenadas vacio...");

        // inicializamos el stack de puntos de la envolvente convexa
        Stack<Point2D> envolvente = new Stack<Point2D>();
        // Ordenamos el arreglo de coordenadas con respecto a los valores del eje Y (de menor a mayor), siendo
        // coordenadas[0] nuestro punto de referencia
        Arrays.sort(coordenadas, new OrdenarPorY());
        // Volvemos a ordenar el arreglo de coordenadas, pero esta vez con respecto al angulo polar entre el
        // primer elemento (el de menor valor Y), y los demas puntos.
        Arrays.sort(coordenadas, 1, largo_coordenadas, new OrdenarPorAnguloPolar(coordenadas[0]));
        // insertamos el primer elemento del arreglo (nuestro punto de referencia) al stack de la envolvente.
        envolvente.push(coordenadas[0]);
        for (i = 1; i < largo_coordenadas; i++) {
            if (!coordenadas[0].equals(coordenadas[i])) {
                break;
            }
        }
        if (i == largo_coordenadas) return null;
        for (j = i + 1; j < largo_coordenadas; j++) {
            if (VerificarOrientacion(coordenadas[0], coordenadas[i], coordenadas[j]) != 0) {
                break;
            }
        }
        envolvente.push(coordenadas[j - 1]);
        for (int k = j; k < largo_coordenadas; k++) {
            Point2D top = envolvente.pop();
            while (VerificarOrientacion(envolvente.peek(), top, coordenadas[k]) <= 0) {
                top = envolvente.pop();
            }
            envolvente.push(top);
            envolvente.push(coordenadas[k]);
        }
        return envolvente;
    }

    // realiza la comparacion con respecto al angulo polar
    // Compare other points relative to polar angle (between 0 and 2*PI) they make with this point
    private static class OrdenarPorAnguloPolar implements Comparator<Point2D> {
        Point2D punto;

        public OrdenarPorAnguloPolar(Point2D punto) {
            this.punto = punto;
        }

        @Override
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.getX() - punto.getX(), dy1 = q1.getY() - punto.getY();
            double dx2 = q2.getX() - punto.getX(), dy2 = q2.getY() - punto.getY();
            if (dy1 >= 0 && dy2 < 0) return -1;
            else if (dy2 >= 0 && dy1 < 0) return +1;
            else if (dy1 == 0 && dy2 == 0) {
                if (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else return 0;
            } else return -VerificarOrientacion(punto, q1, q2);
        }
    }

    // Ordena los puntos por su valor en el eje Y (menor a mayor), si hay dos puntos con el mismo valor en Y, se
    // escoge el de menor valor en eje X.
    private static class OrdenarPorY implements Comparator<Point2D> {
        @Override
        public int compare(Point2D q1, Point2D q2) {
            if (q1.getY() < q2.getY()) return -1;
            if (q1.getY() == q2.getY()) {
                if (q1.getX() < q2.getX()) return -1;
                else if (q1.getX() > q2.getX()) return 1;
                else return 0;
            }
            return 1;
        }
    }

    // Verifica si los tres puntos dados estan ordenados en sentido horario o antihorario:
    // 1 si antihorario
    // -1 si horario
    // 0 si son colineares
    private static int VerificarOrientacion(Point2D a, Point2D b, Point2D c) {
        double area =
                (b.getX() - a.getX()) * (c.getY() - a.getY())
                        - (b.getY() - a.getY()) * (c.getX() - a.getX());
        return (int) Math.signum(area);
    }

    // public void paint(Graphics g) {
    //    Graphics2D g2 = (Graphics2D) g;
    // }

}