import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;
import java.util.Random;


public class Main{

    // retorna un arreglo de Point2D
    public static Point2D[] crearArregloCoordenadas(int numPuntos){
        Point2D[] puntos = new Point2D[numPuntos];
        Random random = new Random();

        for(int i = 0; i < puntos.length; i++) {
            puntos[i] = new Point2D.Double(random.nextInt(100), random.nextInt(100));
        }

        return puntos;
    }

    // retorna una lista de Point2D
    public static List<Point2D> crearListaCoordenadas(int numPuntos){
        List<Point2D> puntos = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < numPuntos; i++){
            puntos.add(new Point2D.Double(random.nextInt(100), random.nextInt(100)));
        }

        return puntos;
    }

    public static int validarNumPuntos(int numPuntos){
        return Math.min(numPuntos, 100);
    }


    public static void main(String[] args) {
        // ventana principal
        JFrame framePrincipal = new JFrame("Tarea 2 Analisis de Algoritmos");
        framePrincipal.setSize(800, 600);
        framePrincipal.setLayout(new BorderLayout());

        // texto numero coordenadas
        JLabel numCoordenadasLabel = new JLabel("Ingrese número de coordenadas (100 máximo)");

        // text box coordenadas
        JTextField numCoordenadasTxtField = new JTextField();

        // paneles
        JPanel panelBotones = new JPanel();
        JPanel panelTextBox = new JPanel();
        JPanel panelNumPuntos = new JPanel(new BorderLayout());
        BoxLayout box = new BoxLayout(panelTextBox, BoxLayout.PAGE_AXIS);
        panelNumPuntos.setBorder(new EmptyBorder(10,10,10,10));
        panelTextBox.setLayout(box);
        panelTextBox.setBorder(new EmptyBorder(10, 10, 10, 10));

        // botones
        JButton btnGraham = new JButton("Graham");
        JButton btnJarvis = new JButton("Envolvente (Jarvis)");
        JButton btnQuickHull = new JButton("QuickHull");

        panelBotones.add(btnGraham);
        panelBotones.add(btnJarvis);
        panelBotones.add(btnQuickHull);
        panelNumPuntos.add(numCoordenadasLabel, BorderLayout.NORTH);
        panelNumPuntos.add(numCoordenadasTxtField, BorderLayout.SOUTH);
        panelTextBox.add(panelNumPuntos);

        framePrincipal.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        framePrincipal.getContentPane().add(panelTextBox, BorderLayout.CENTER);
        framePrincipal.getContentPane().add(panelNumPuntos, BorderLayout.NORTH);

        // Listener boton Graham
        btnGraham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                int numPuntos = validarNumPuntos(numPuntosInput);
                Point2D[] puntos = crearArregloCoordenadas(numPuntos);
                Graham graham = new Graham();
                Stack<Point2D> poligonoConvexoGraham = graham.crearPoligonoConvexo(puntos);
                ArrayList<Point2D> puntosGraham = new ArrayList<>();
                while (!poligonoConvexoGraham.isEmpty()){
                    puntosGraham.add(poligonoConvexoGraham.pop());
                }
                JFrame frameGraham = new JFrame("Algoritmo Graham");
                frameGraham.getContentPane().add(new DibujarConvexa(puntosGraham, puntos));
                frameGraham.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameGraham.pack();
                frameGraham.setBounds(40,40,300,300);
                frameGraham.setLocationRelativeTo(null);
                frameGraham.setVisible(true);
            }
        });

        // Listener boton Jarvis
        btnJarvis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                int numPuntos = validarNumPuntos(numPuntosInput);
                Point2D[] puntos = crearArregloCoordenadas(numPuntos);
                Envolvente jarvis = new Envolvente();
                ArrayList<Point2D> poligonoConvexoEnvolvente = jarvis.encontrarEnvolvente(puntos);
                JFrame frameJarvis = new JFrame("Algoritmo envolvente (Jarvis)");
                frameJarvis.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameJarvis.pack();
                frameJarvis.setBounds(40,40,300,300);
                frameJarvis.getContentPane().add(new DibujarConvexa(poligonoConvexoEnvolvente, puntos));
                frameJarvis.setLocationRelativeTo(null);
                frameJarvis.setVisible(true);
            }
        });

        // Listener boton QuickHull
        btnQuickHull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                int numPuntos = validarNumPuntos(numPuntosInput);
                List<Point2D> puntos = crearListaCoordenadas(numPuntos);
                Point2D[] puntosArray = (puntos.toArray(new Point2D[numPuntos]));
                QuickHull quickHull = new QuickHull();
                ArrayList<Point2D> poligonoConvexoQuickhull = quickHull.envolventeQuickHull(puntos);
                JFrame frameQuikhull = new JFrame("Algoritmo QuickHull");
                frameQuikhull.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameQuikhull.pack();
                frameQuikhull.setBounds(40,40,300,300);
                frameQuikhull.getContentPane().add(new DibujarConvexa(poligonoConvexoQuickhull, puntosArray));
                frameQuikhull.setLocationRelativeTo(null);
                frameQuikhull.setVisible(true);
            }
        });
        framePrincipal.pack();
        framePrincipal.setVisible(true);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}