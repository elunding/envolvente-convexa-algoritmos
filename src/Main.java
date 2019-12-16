import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


        JFrame frmMain = new JFrame(); // Create our JFrame
        // Set the layout for main frame. This controls how things get arranged on the screen
        frmMain.setLayout(new BorderLayout());

        // panels are what you put everything else on
        JPanel panel1 = new JPanel(new FlowLayout()); // another layout
        JPanel panel2 = new JPanel();
        BoxLayout box = new BoxLayout(panel2, BoxLayout.PAGE_AXIS); // another layout
        panel2.setLayout(box);

        // here are a couple of buttons
        JButton btnGraham = new JButton("Graham");
        JButton btnJarvis = new JButton("Envolvente (Jarvis)");

        // here are a couple of textboxes. They accept typed in information
        // JTextField txtFirstName = new JTextField();
        // JTextField txtMiddleInitial = new JTextField();
        JTextField numPuntosTxtField = new JTextField();

        // add our buttons to panel1. It has a FlowLayout, so they will be centered left to right as we add them
        panel1.add(btnGraham);
        panel1.add(btnJarvis);

        // Create a panel to hold First Name
        JPanel numPuntosPanel = new JPanel(new BorderLayout()); // also set its layout
        // here we add a JLabel.class they just display text, they don't allow input
        numPuntosPanel.add(new JLabel("Ingrese cantidad de puntos"), BorderLayout.WEST);
        // here we put our text box onto the First name panel
        numPuntosPanel.add(numPuntosTxtField, BorderLayout.CENTER);
        /*
        // repeat for middle initial panel
        JPanel pnlMiddleInitial = new JPanel(new BorderLayout());
        pnlMiddleInitial.add(new JLabel("M.I."), BorderLayout.WEST);
        pnlMiddleInitial.add(txtMiddleInitial, BorderLayout.CENTER);

        // repeat for last name panel
        JPanel pnlLastName = new JPanel(new BorderLayout());
        pnlLastName.add(new JLabel("last name"), BorderLayout.WEST);
        pnlLastName.add(txtLastName, BorderLayout.CENTER);
        */
        // put a 3 pixel border arounnd panel 2 to keep things away from the edge
        panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
        // add all of our input panels to panel 2, according to BoxLayout (up above)
        panel2.add(numPuntosPanel);
        // panel2.add(pnlMiddleInitial);
        // panel2.add(pnlLastName);

        // add panel1 and panel2 to the Frame. You have to add to the .getContentPane(), or you might mess things up.
        frmMain.getContentPane().add(panel1, BorderLayout.NORTH);
        frmMain.getContentPane().add(panel2, BorderLayout.CENTER);
        
        // This is how we tell the program what to do when the user presses the "Add" button.

        btnGraham.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graham graham = new Graham();
                Stack<Point2D> poligonoConvexoGraham = graham.crearPoligonoConvexo(coordenadas);
            }
        });

        // pack just makes everything take up it's proper space on the screen in as tight of a package as possible
        frmMain.pack();
        // if you don't set visible to true, you won't see your Frame
        frmMain.setVisible(true);
        // what to do when the user clicks the "X" to close or used "Close" from the context menu
        frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        /*


        System.out.println("Algoritmo Graham: ");
        // imprime los resultados
        assert poligonoConvexoGraham != null;
        while (!poligonoConvexoGraham.isEmpty()) System.out.println(poligonoConvexoGraham.pop());

        System.out.println("Algoritmo envolvente (gift-wrapping): ");
        Envolvente envolvente = new Envolvente();
        ArrayList<Point2D> poligonoConvexoEnvolvente = envolvente.encontrarEnvolvente(coordenadas);
        if (poligonoConvexoEnvolvente.size() != 0)
            for(int i = 0; i < poligonoConvexoEnvolvente.size(); i++){ System.out.println(poligonoConvexoEnvolvente.get(i)); }

        */
    }
}