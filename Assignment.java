import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Assignment extends JFrame{

    private Container container;
    private Font font;
    private JLabel label1,label2,label3;
    private JTextField textF1, textF2;
    private JComboBox cbox;
    private JButton button1, button2;
    private String[] currencyName = {"Choose Currency","RUPEE", "USD", "EURO", "CANADIAN DOLLAR", "AUSTRILIAN DOLLAR"};
    private double input;
    private String currency;
    private double output;
    private String answer;

    Assignment() {
        container = this.getContentPane();
        container.setLayout(null);
        container.setBackground(Color.gray);

        font = new Font("JetBrains Mono",Font.BOLD,18);

        label1 = new JLabel("Enter Amount (In Taka)");
        label1.setBounds(50, 40, 250, 50);
        label1.setFont(font);

        textF1 = new JTextField();
        textF1.setBounds(320, 52, 450, 30);
        textF1.setFont(font);

        label2 = new JLabel("Convert to");
        label2.setBounds(50, 100, 250, 50);
        label2.setFont(font);

        cbox = new JComboBox(currencyName);
        cbox.setBounds(320, 110, 450,30);
        cbox.setFont(font);

        label3 = new JLabel("Converted Value");
        label3.setBounds(320, 330, 250, 50);
        label3.setFont(font);

        textF2 = new JTextField();
        textF2.setBounds(145,400,520,30);
        textF2.setBackground(Color.lightGray);
        textF2.setFont(font);

        button1 = new JButton("Reset");
        button1.setBounds(30,500, 100,30);
        button1.setBackground(Color.white);
        button1.setFont(font);

        button2 = new JButton("Convert");
        button2.setBounds(650,500, 120,30);
        button2.setBackground(Color.white);
        button2.setFont(font);

        container.add(label1);
        container.add(textF1);
        container.add(label2);
        container.add(cbox);
        container.add(label3);
        container.add(textF2);
        container.add(button1);
        container.add(button2);

        Listner listner = new Listner();
        textF1.addActionListener(listner);
        cbox.addActionListener(listner);
        button1.addActionListener(listner);
        button2.addActionListener(listner);
    }

    class Listner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == textF1) {
                try {
                    input = Double.parseDouble(textF1.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(container,"Please Input valid value");
                }
            }
            else if (event.getSource() == cbox) {
                currency = cbox.getSelectedItem().toString();
            }
            else if (event.getSource() == button2) {
                try {
                    if (currency.equals("RUPEE")) {
                        output = input / 1.5;
                        answer = Double.toString(output);
                        textF2.setText(answer);
                    } else if (currency.equals("USD")) {
                        output = input / 85;
                        answer = Double.toString(output);
                        textF2.setText(answer);
                    } else if (currency.equals("EURO")) {
                        output = input / 104;
                        answer = Double.toString(output);
                        textF2.setText(answer);
                    } else if (currency.equals("CANADIAN DOLLAR")) {
                        output = input / 67;
                        answer = Double.toString(output);
                        textF2.setText(answer);
                    } else if (currency.equals("AUSTRILIAN DOLLAR")) {
                        output = input / 65;
                        answer = Double.toString(output);
                        textF2.setText(answer);
                    }
                } catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(container,"Please Choose a currency");
                }
            }
            else if (event.getSource() == button1) {
                textF1.setText(null);
                cbox.setSelectedIndex(0);
                textF2.setText(null);
            }
        }
    }

    public static void main(String[] args) {

        Assignment frame = new Assignment();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Currency Converter");
        frame.setSize(800, 600);
    }
}
