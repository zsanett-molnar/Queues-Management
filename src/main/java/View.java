import javax.swing.*;
import java.awt.*;


import static javax.swing.Box.createVerticalBox;

public class View extends JFrame {

    private JFrame frame = new JFrame("Queue Management System");
    private JTextField nrClients = new JTextField("",10);
    private JTextField nrQueues = new JTextField("",10);
    private JTextField minArrival = new JTextField("",10);
    private JTextField maxArrival = new JTextField("",10);
    private JTextField minServiceTime = new JTextField("",10);
    private JTextField maxServiceTime = new JTextField("",10);
    private JTextArea result = new JTextArea();
    private JTextField timelimit = new JTextField(10);
    private JLabel msg1 = new JLabel("Introduce the number of clients:");
    private JLabel msg2 = new JLabel("Introduce the number of queues:");
    private JLabel msg3 = new JLabel("Simulation:");
    private JLabel msg4 = new JLabel("Min Arrival time:");
    private JLabel msg5 = new JLabel("Max Arrival time:");
    private JLabel msg6 = new JLabel("Min service time:");
    private JLabel msg7 = new JLabel("Max service time:");
    public JButton start = new JButton("Start");
    public JButton exit = new JButton("Exit");
    public JButton reset = new JButton("Reset");

    Controller controller = new Controller(this);

    public View() {

        start.addActionListener(controller);
        exit.addActionListener(controller);
        reset.addActionListener(controller);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 900);
        JPanel p = new JPanel();

        JPanel panel0 = new JPanel();
        JLabel timeLimit = new JLabel("Time limit:");
        panel0.add(timeLimit);
        panel0.add(timelimit);
        panel0.setBackground(Color.decode("#d6cdd4"));

        JPanel panel1 = new JPanel();
        panel1.add(msg1);
        panel1.add(nrClients);
        panel1.setBackground(Color.decode("#d6cdd4"));

        JPanel panel2 = new JPanel();
        panel2.add(msg2);
        panel2.add(nrQueues);
        panel2.setBackground(Color.decode("#d6cdd4"));

        JPanel panel3 = new JPanel();
        panel3.add(msg4);
        panel3.add(minArrival);
        panel3.add(msg5);
        panel3.add(maxArrival);
        panel3.setBackground(Color.decode("#d6cdd4"));

        JPanel panel4 = new JPanel();
        panel4.add(msg6);
        panel4.add(minServiceTime);
        panel4.add(msg7);
        panel4.add(maxServiceTime);
        panel4.setBackground(Color.decode("#d6cdd4"));

        JPanel panel5 = new JPanel();
        panel5.add(msg3);
        result.setPreferredSize(new Dimension(500,280));
        panel5.setLayout(new FlowLayout());
        panel5.setBackground(Color.decode("#d6cdd4"));
        result.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(result);
        scrollPane.setPreferredSize(new Dimension(480, 400));
        //panel5.add(scrollPane);
        panel5.add(result);
        //scrollPane.setVisible(true);


        JPanel panel6 = new JPanel();
        panel6.add(start);
        start.setBackground(Color.green);
        panel6.add(exit);
        exit.setBackground(Color.PINK);
        panel6.add(reset);
        reset.setBackground(Color.GRAY);
        panel6.setBackground(Color.decode("#d6cdd4"));

        p.add(panel0);
        p.add(panel1);
        p.add(panel2);
        p.add(panel3);
        p.add(panel4);
        p.add(panel6);
        p.add(panel5);

        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        frame.add(scrollPane);
        frame.setVisible(true);
        frame.setContentPane(p);
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getTimeLimit() {
        return Integer.parseInt(timelimit.getText());
    }

    public int getNrClients() {
        return Integer.parseInt(nrClients.getText());
    }

    public int getNrQueues() {
        return Integer.parseInt(nrQueues.getText());
    }

    public int getMinArrTime() {
        return Integer.parseInt(minArrival.getText());
    }

    public int getMaxArrTime() {
        return Integer.parseInt(maxArrival.getText());
    }

    public int getMinServTime() {
        return Integer.parseInt(minServiceTime.getText());
    }

    public int getMaxServiceTime() {
        return Integer.parseInt(maxServiceTime.getText());
    }

    public void setResult(String s) {
        result.setText(s);
    }

    public void clear() {
        nrClients.setText("");
        nrQueues.setText("");
        minArrival.setText("");
        maxArrival.setText("");
        minServiceTime.setText("");
        maxServiceTime.setText("");
        result.setText("");
        timelimit.setText("");

    }





}
