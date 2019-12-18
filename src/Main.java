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

    public static Point2D[] crearArregloCoordenadas(int numPuntos){
        Point2D[] puntos = new Point2D[numPuntos];
        // genera el arreglo de puntos
        Random random = new Random();

        for(int i = 0; i < puntos.length; i++) {
            puntos[i] = new Point2D.Double(random.nextInt(100), random.nextInt(100));
        }

        return puntos;
    }

    public static List<Point2D> crearListaCoordenadas(int numPuntos){
        List<Point2D> puntos = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < numPuntos; i++){
            puntos.add(new Point2D.Double(random.nextInt(100), random.nextInt(100)));
        }

        return puntos;
    }


    public static void main(String[] args) {
        /**
         * debug:
        puntos[0] = new Point2D.Double(54.0, 32.0);
        puntos[1] = new Point2D.Double(97.0, 25.0);
        puntos[2] = new Point2D.Double(73.0, 68.0);
        puntos[3] = new Point2D.Double(89.0, 57.0);
        puntos[4] = new Point2D.Double(72.0, 47.0);
        puntos[5] = new Point2D.Double(85.0, 51.0);
        puntos[6] = new Point2D.Double(61.0, 57.0);
        puntos[7] = new Point2D.Double(25.0, 2.0);
        puntos[8] = new Point2D.Double(11.0, 36.0);
        puntos[9] = new Point2D.Double(64.0, 55.0);
        puntos[10] = new Point2D.Double(66.0, 92.0);
        puntos[11] = new Point2D.Double(14.0, 67.0);
        puntos[12] = new Point2D.Double(95.0, 93.0);
        */
        JFrame framePrincipal = new JFrame("Tarea 2 Analisis de Algoritmos");
        framePrincipal.setSize(800, 600);
        framePrincipal.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        JPanel panelTextBox = new JPanel();
        BoxLayout box = new BoxLayout(panelTextBox, BoxLayout.PAGE_AXIS);
        panelTextBox.setLayout(box);
        JButton btnGraham = new JButton("Graham");
        JButton btnJarvis = new JButton("Envolvente (Jarvis)");
        JButton btnQuickHull = new JButton("QuickHull");
        JTextField numCoordenadasTxtField = new JTextField();
        panelBotones.add(btnGraham);
        panelBotones.add(btnJarvis);
        panelBotones.add(btnQuickHull);
        JPanel numPuntosPanel = new JPanel(new BorderLayout());
        JLabel numCoordenadasLabel = new JLabel("Ingrese número de coordenadas (100 máximo)");
        numPuntosPanel.setBorder(new EmptyBorder(10,10,10,10));
        numPuntosPanel.add(numCoordenadasLabel, BorderLayout.NORTH);
        numPuntosPanel.add(numCoordenadasTxtField, BorderLayout.SOUTH);
        panelTextBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelTextBox.add(numPuntosPanel);

        framePrincipal.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        framePrincipal.getContentPane().add(panelTextBox, BorderLayout.CENTER);
        framePrincipal.getContentPane().add(numPuntosPanel, BorderLayout.NORTH);

        // Listener boton Graham
        btnGraham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                if(numPuntosInput >= 100) {
                    // si la cantidad excede el valor maximo permitido, se setea a ese valor
                    numPuntosInput = 100;
                }

                Point2D[] puntos = crearArregloCoordenadas(numPuntosInput);
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
                if(numPuntosInput >= 100) {
                    // si la cantidad excede el valor maximo permitido, se setea a ese valor
                    numPuntosInput = 100;
                }
                Point2D[] puntos = crearArregloCoordenadas(numPuntosInput);
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
                if(numPuntosInput >= 100) {
                    // si la cantidad excede el valor maximo permitido, se setea a ese valor
                    numPuntosInput = 100;
                }
                List<Point2D> puntos = crearListaCoordenadas(numPuntosInput);
                Point2D[] puntosArray = (puntos.toArray(new Point2D[numPuntosInput]));
                QuickHull quickHull = new QuickHull();
                ArrayList<Point2D> poligonoConvexoQuickhull = quickHull.envolventeQuickHull(puntos);
                // List<Point2D> poligonoConvexoQuickhull = quickHull.envolventeQuickHull(puntos);
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