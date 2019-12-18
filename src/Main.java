import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.Random;


public class Main{
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
        JFrame framePrincipal = new JFrame("Tarea 2 Analisis de Algoritmos"); // Create our JFrame
        // Set the layout for main frame. This controls how things get arranged on the screen
        framePrincipal.setSize(800, 600);
        framePrincipal.setLayout(new BorderLayout());

        // panels are what you put everything else on
        JPanel panelBotones = new JPanel(); // another layout
        JPanel panelTextBox = new JPanel();
        BoxLayout box = new BoxLayout(panelTextBox, BoxLayout.PAGE_AXIS); // another layout
        panelTextBox.setLayout(box);
        // here are a couple of buttons
        JButton btnGraham = new JButton("Graham");
        JButton btnJarvis = new JButton("Envolvente (Jarvis)");
        JTextField numCoordenadasTxtField = new JTextField();
        // numCoordenadasTxtField.setFont()
        // add our buttons to panelBotones. It has a FlowLayout, so they will be centered left to right as we add them
        panelBotones.add(btnGraham);
        panelBotones.add(btnJarvis);
        // panelBotones.add(numCoordenadasTxtField);
        // Create a panel to hold First Name
        JPanel numPuntosPanel = new JPanel(new BorderLayout()); // also set its layout
        JLabel numCoordenadasLabel = new JLabel("Ingrese número de coordenadas (50 máximo)");
        numPuntosPanel.setBorder(new EmptyBorder(10,10,10,10));
        numPuntosPanel.add(numCoordenadasLabel, BorderLayout.NORTH);
        numPuntosPanel.add(numCoordenadasTxtField, BorderLayout.SOUTH);
        // here we add a JLabel.class they just display text, they don't allow input
        // here we put our text box onto the First name panel
        panelTextBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        // add all of our input panels to panel 2, according to BoxLayout (up above)
        panelTextBox.add(numPuntosPanel);

        // add panelBotones and panelTextBox to the Frame. You have to add to the .getContentPane(), or you might mess things up.
        framePrincipal.getContentPane().add(panelBotones, BorderLayout.SOUTH);
        framePrincipal.getContentPane().add(panelTextBox, BorderLayout.CENTER);
        framePrincipal.getContentPane().add(numPuntosPanel, BorderLayout.NORTH);
        /**
        Point2D[] puntos = new Point2D[13];

        Random random = new Random();

        for(int i = 0; i < puntos.length; i++){
            puntos[i] = new Point2D.Double(random.nextInt(100), random.nextInt(100));
        }*/

        btnGraham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                Graham graham = new Graham();
                Point2D[] puntos = new Point2D[numPuntosInput];
                // genera el arreglo de puntos
                Random random = new Random();

                for(int i = 0; i < puntos.length; i++) {
                    puntos[i] = new Point2D.Double(random.nextInt(100), random.nextInt(100));
                }
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

        btnJarvis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numPuntosInput = Integer.parseInt(numCoordenadasTxtField.getText());
                Graham graham = new Graham();
                Point2D[] puntos = new Point2D[numPuntosInput];
                // genera el arreglo de puntos
                Random random = new Random();

                for(int i = 0; i < puntos.length; i++) {
                    puntos[i] = new Point2D.Double(random.nextInt(100), random.nextInt(100));
                }
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

        framePrincipal.pack();
        framePrincipal.setVisible(true);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}