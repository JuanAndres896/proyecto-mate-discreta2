import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alejanddro on 16/11/2016.
 */
public class Principal extends  JFrame{
    private JLabel lbl1;
    private JButton btn1AStar;
    private JButton btn1bstar;
    private JButton btn1cStar;
    private JButton btn1dStar;
    private JButton btn2Star;
    private JButton btn1BFloyd;
    private JButton btn1CFloyd;
    private JButton btn1DFloyd;
    private JButton btn1AFloyd;
    private JPanel rootPanel;
    public Principal(){
        setContentPane(rootPanel);
        setTitle("Proyecto Discreta 2");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        btn1AStar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventana = new JFrame();
                ventana.setLayout(new BorderLayout());
                ventana.setLocationRelativeTo(null);
                ventana.add(new Rejilla(".\\blank.txt",1,25,25,1), BorderLayout.CENTER);
                ventana.setSize(600,600);
                ventana.setVisible(true);
            }
        });
    }
}
