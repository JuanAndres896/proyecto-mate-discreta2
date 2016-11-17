import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alejanddro on 16/11/2016.
 */
public class Coordenadas extends JFrame{
    private JPanel coordenadaRoot;
    private JLabel lbl1;
    private JLabel lbl5;
    private JLabel lbl4;
    private JTextField txtfFinalY;
    private JTextField txtfFinalX;
    private JTextField txtfOrigenX;
    private JLabel lbl2;
    private JLabel lbl3;
    private JTextField txtfOrigenY;
    private JButton btnGenerar;
    private JButton btnClose;

    public Coordenadas() {
        setContentPane(coordenadaRoot);
        setTitle("Proyecto Discreta 2");
        txtfOrigenX.setText("0");
        txtfOrigenY.setText("0");
        txtfFinalX.setText("1");
        txtfFinalY.setText("1");
        setSize(300,300);
        setVisible(true);
        pack();

        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x_cero = Integer.parseInt(txtfOrigenX.getText());
                int y_cero = Integer.parseInt(txtfOrigenY.getText());
                int x_final = Integer.parseInt(txtfFinalX.getText());
                int y_final = Integer.parseInt(txtfFinalY.getText());
                JFrame ventana = new JFrame();
                ventana.setLayout(new BorderLayout());
                setTitle("Parte 2");
                ventana.setLocationRelativeTo(null);
                ventana.add(new Rejilla(".\\laberintoFinal.txt",x_cero,y_cero,x_final,y_final), BorderLayout.CENTER);
                ventana.setSize(600,600);
                ventana.setVisible(true);
            }
        });
    }

}
