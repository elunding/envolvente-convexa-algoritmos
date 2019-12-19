import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class QuickHull {

    //Entra una list con los puntos x,y respectivamente al metodo principal
    public static ArrayList<Point2D> envolventeQuickHull(List<Point2D> listaDePuntos) {
        //Se crea arreglo del tipo POINT2D para los puntos que definiran el cierre convexo
        ArrayList<Point2D> cierreGeometrico = new ArrayList<Point2D>();
        //Se definen los puntos maximos y minimos de una coordenada
        Point2D minX = new Point2D.Double(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Point2D maxX = new Point2D.Double(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
        for (Point2D aux : listaDePuntos) {
            //Se recorre la lista de puntos entrante
            //Se guarda los puntos minimos y maximos mientras se recorren los puntos que entraron 
            if (aux.getX() < minX.getX()) {
                minX = aux;
            }
            if (aux.getX() > maxX.getX()) {
                maxX = aux;
            }
        }
        List<Point2D> S1 = puntosLado(minX, maxX, listaDePuntos);
        List<Point2D> S2 = puntosLado(maxX, minX, listaDePuntos);
        cierreGeometrico.add(minX);
        cierreGeometrico.addAll(encontrarEnvolvente(minX, maxX, S1));
        cierreGeometrico.add(maxX);
        cierreGeometrico.addAll(encontrarEnvolvente(maxX, minX, S2));
        return cierreGeometrico; // returna los puntos finales que generan el cierre
    }
    public static List<Point2D> encontrarEnvolvente(Point2D a, Point2D b, List<Point2D> S) {
        List<Point2D> cierreGeometrico = new ArrayList<Point2D>();
        Point2D c;
        if(!S.isEmpty()) {
            List<Point2D> A;
            List<Point2D> B;
            c = puntoMayorArea(a, b, S);
            A = puntosLado(a, c, S);
            B = puntosLado(c, b, S);
            // llamada recursiva con conjunto A
            cierreGeometrico.addAll(encontrarEnvolvente(a, c, A));
            cierreGeometrico.add(c);
            // llamada recursiva con conjunto B
            cierreGeometrico.addAll(encontrarEnvolvente(c, b, B));
        }
        return cierreGeometrico;

    }

 
    public static List<Point2D> puntosLado(Point2D a, Point2D b, List<Point2D> S) {
        //Se define una nueva lista de Point2D: subConjunto
        List<Point2D> subConjunto = new ArrayList<Point2D>();
        for(Point2D aux:S) { // Se recorren los puntos para encontrar los izquierdos del vector ab
            if(isLeft(a, b, aux)>0) {
                if(!aux.equals(a) && !aux.equals(b)) {
                    subConjunto.add(aux);
                }
            }
        }
        return subConjunto;
    }

    private static double isLeft(Point2D P0, Point2D P1, Point2D P2) {
        return (P1.getX() - P0.getX())*(P2.getY() - P0.getY()) -
                (P2.getX() - P0.getX())*(P1.getY() - P0.getY());
    }


    //funcion que retorna los numeros que estan fuera del area
    public static Point2D puntoMayorArea(Point2D a, Point2D b, List<Point2D> S) {
        Point2D punto = new Point2D.Double();
        double area = Double.MIN_VALUE;
        for(Point2D aux:S) {
            double area_aux = determinante(a, b, aux);
            if(area_aux>area) {
                area = area_aux;
                punto = aux;
            }
        }
        return punto;
    }

    //Retorna el determinanate los puntos
    public static double determinante(Point2D p1, Point2D p2, Point2D p3){
        double det = 0;
        det = p2.getY() * p3.getX() + p1.getY() * p2.getX() + p1.getX() * p3.getY();
        det = p1.getX() * p2.getY() + p2.getX() * p3.getY() + p1.getY() * p3.getX()-det;
        return det;
    }

}