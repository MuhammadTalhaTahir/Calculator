import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;



public class Gui {
    JFrame frame;
    JPanel top;
    JPanel middle;
    JPanel bottom;
    JButton [] button;
    JTextField out;
    JTextField in;
    int btnNumber;
    Dimension btnSize;
    Dimension textFieldSize;
    handler hnd;
    public Gui(){
        btnNumber = 22;
        btnSize = new Dimension(40, 65);
        textFieldSize = new Dimension(300, 50);
        this.init();
    }
    public void init(){
        frame = new JFrame();
        frame.setTitle("Calculator");
        
        
        frame.setLayout(new BorderLayout());
        top = new JPanel();
        frame.add(top, BorderLayout.NORTH);
        middle = new JPanel();
        frame.add(middle, BorderLayout.CENTER);
        bottom = new JPanel();
        frame.add(bottom, BorderLayout.SOUTH);
        
        top.setLayout(new GridLayout(2, 1));
        
        //init textFields
        out = createCustomTextField(15);
        in = createCustomTextField(20);
        top.add(out);
        top.add(in);        
        this.initButton();
        
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private JTextField createCustomTextField(int font){
        JTextField temp = new JTextField(18);
        temp.setBorder(null);
        temp.setEditable(false);
        temp.setHorizontalAlignment(JTextField.RIGHT);
        temp.setPreferredSize(textFieldSize);
        temp.setBackground(new Color(87,118,224));
        temp.setFont(new Font("Monospace",Font.BOLD,font));
        temp.setForeground(Color.WHITE);
        return temp;
    }
    private void initButton(){
        button = new JButton[btnNumber];
        hnd = new handler(this);
        middle.setLayout(new GridLayout(4, 5));
        
        for(int i=0;i<btnNumber-2;i++){
            button[i] = new JButton();
            button[i].setPreferredSize(btnSize);
            button[i].setFont(new Font("Monospace",Font.PLAIN,18));
            button[i].setBorder(null);
            button[i].setBackground(Color.WHITE);
            button[i].addActionListener(hnd);
            middle.add(button[i]);
        }
        //setting text on button .. aint got more efficient way. :/
        Color btnColor = new Color(231, 236, 253);
        button[0].setText("CE");
        button[1].setText("0");
        button[2].setText("C");
        button[3].setText("^");
        button[3].setBackground(btnColor);
        button[4].setText("<<");
        button[4].setBackground(btnColor);
        button[5].setText("7");
        button[6].setText("8");
        button[7].setText("9");
        button[8].setText("+/-");
        button[8].setBackground(btnColor);
        button[9].setText("%");
        button[9].setBackground(btnColor);
        button[10].setText("4");
        button[11].setText("5");
        button[12].setText("6");
        button[13].setText("/");
        button[14].setText("*");
        button[13].setBackground(btnColor);
        button[14].setBackground(btnColor);
        button[15].setText("1");
        button[16].setText("2");
        button[17].setText("3");
        button[18].setText("-");
        button[19].setText("+");
        button[18].setBackground(btnColor);
        button[19].setBackground(btnColor);
        
        bottom.setLayout(new GridLayout(1, 2));
        for(int i=20; i<btnNumber ;i++){
            button[i] = new JButton();
            button[i].setPreferredSize(new Dimension(10, 65));
            button[i].setBorder(null);
            button[i].setFont(new Font("Monospace",Font.PLAIN,18));
            button[i].setBackground(new Color(207,217,250));
            button[i].addActionListener(hnd);
            
        }
        button[btnNumber-2].setText(".");
        button[btnNumber-2].setFont(new Font("Monospace",Font.BOLD,18));
        button[btnNumber-1].setText("=");
        bottom.add(button[btnNumber-2]);
        bottom.add(button[btnNumber-1]);
    }
}
