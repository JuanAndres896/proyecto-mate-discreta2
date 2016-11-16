import javax.swing.*;

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
    }
}
