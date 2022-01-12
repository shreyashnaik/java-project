
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JRadioButton;
//*************
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;

class Test extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;

    JLabel label;
    JRadioButton radioButton[] = new JRadioButton[5];
    JButton btnNext, btnBookmark;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];

    // create jFrame with radioButton and JButton
    Test(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            radioButton[i] = new JRadioButton();
            add(radioButton[i]);
            bg.add(radioButton[i]);
        }
        btnNext = new JButton("Next");
        btnBookmark = new JButton("Bookmark");
        btnNext.addActionListener(this);
        btnBookmark.addActionListener(this);
        add(btnNext);
        add(btnBookmark);
        set();
        label.setBounds(30, 40, 450, 20);
        //radioButton[0].setBounds(50, 80, 200, 20);
        radioButton[0].setBounds(50, 80, 450, 20);
        radioButton[1].setBounds(50, 110, 200, 20);
        radioButton[2].setBounds(50, 140, 200, 20);
        radioButton[3].setBounds(50, 170, 200, 20);
        btnNext.setBounds(100, 240, 100, 30);
        btnBookmark.setBounds(270, 240, 100, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);
    }

    // handle all actions based on event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                btnNext.setEnabled(false);
                btnBookmark.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Bookmark")) {
            JButton bk = new JButton("Bookmark" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                btnBookmark.setText("Result");
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Bookmark" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }

        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "correct answers= " + count);
            System.exit(0);
        }
    }

    // SET Questions with options
    void set() {
        radioButton[4].setSelected(true);
        if (current == 0) {
            label.setText("Que1:  Which of the following is not introduced with Java 8?");
            radioButton[0].setText("Stream API");
            radioButton[1].setText("Serialization");
            radioButton[2].setText("Spliterator");
            radioButton[3].setText("Lambda Expression");
        }
        if (current == 1) {
            label.setText("Que2:  Which feature of java 7 allows to not explicitly close IO resource?");
            radioButton[0].setText("try catch finally");
            radioButton[1].setText("IOException");
            radioButton[2].setText("AutoCloseable");
            radioButton[3].setText("Streams");
        }
        if (current == 2) {
            label.setText("Que3: SessionFactory is a thread-safe object.");
            radioButton[0].setText("true");
            radioButton[1].setText("false");
            radioButton[2].setText("don't know");
            radioButton[3].setText("false");
        }
        if (current == 3) {
            label.setText("Que4: Which is the new method introduced in java 8 to iterate over a collection?");
            radioButton[0].setText("for (String i : StringList)");
            radioButton[1].setText("foreach (String i : StringList)");
            radioButton[2].setText("StringList.forEach()");
            radioButton[3].setText("List.for()");
        }
        if (current == 4) {
            label.setText("Que5:  What is the substitute of Rhino javascript engine in Java 8?");
            radioButton[0].setText(" Nashorn");
            radioButton[1].setText("V8");
            radioButton[2].setText("Inscript");
            radioButton[3].setText("Narcissus");
        }
        if (current == 5) {
            label.setText("Que6: How to read entire file in one line using java 8?");
            radioButton[0].setText("Files.readAllLines()");
            radioButton[1].setText("Files.read()");
            radioButton[2].setText("Files.readFile()");
            radioButton[3].setText("Files.lines()");
        }
        if (current == 6) {
            label.setText("Que7:  Which feature of java 7 allows to not explicitly close IO resource?");
            radioButton[0].setText("try catch finally");
            radioButton[1].setText("IOException");
            radioButton[2].setText("AutoCloseable");
            radioButton[3].setText("Streams");
        }
        if (current == 7) {
            label.setText("Que8:  Which of the following is not a core interface of Hibernate?");
            radioButton[0].setText("Configuration");
            radioButton[1].setText("Criteria");
            radioButton[2].setText("SessionManagement");
            radioButton[3].setText("Session");
        }
        if (current == 8) {
            label.setText("Que9: SessionFactory is a thread-safe object.");
            radioButton[0].setText("true");
            radioButton[1].setText("false");
            radioButton[2].setText("don't know");
            radioButton[3].setText("false");
        }
        if (current == 9) {
            label.setText("Que10: Which of the following is not a state of object in Hibernate?");
            radioButton[0].setText("Attached()");
            radioButton[1].setText("Detached()");
            radioButton[2].setText("Persistent()");
            radioButton[3].setText("Transient()");
        }
        label.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            radioButton[j].setBounds(50, 80 + i, 200, 20);
    }

    // declare right answers.
    boolean check() {
        if (current == 0)
            return (radioButton[1].isSelected());
        if (current == 1)
            return (radioButton[1].isSelected());
        if (current == 2)
            return (radioButton[0].isSelected());
        if (current == 3)
            return (radioButton[2].isSelected());
        if (current == 4)
            return (radioButton[0].isSelected());
        if (current == 5)
            return (radioButton[0].isSelected());
        if (current == 6)
            return (radioButton[1].isSelected());
        if (current == 7)
            return (radioButton[2].isSelected());
        if (current == 8)
            return (radioButton[0].isSelected());
        if (current == 9)
            return (radioButton[0].isSelected());
        return false;
    }

    public static void main(String s[]) {
        new Test("Online Test App");
    }

}
class BE extends JFrame implements ActionListener
{
    BE(String s)
    {
        super(s);
        setVisible(true);
        setLocation(400,400);
        setSize(500,500);
        setLayout(null);
        getContentPane().setBackground(java.awt.Color.pink);
        JLabel head = new JLabel();
        add(head);
        head.setText("Welcome to Java Test app");
        head.setBounds(120,100,170,100);
        head.setForeground(Color.blue);
        JButton btnstart=new JButton();
        add(btnstart);
        btnstart.setText("Start Test");
        btnstart.addActionListener(this);
        btnstart.setBounds(150,190,150,50);

    }
   /* public static void main(String a[])
    {
        BE obj = new BE("welcome");
    }*/
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        Test.main(null);
    }
}

//****************************
class Demo extends JFrame implements ActionListener/*KeyListener*/
{
   JLabel l_username = null;
   JLabel l_pass = null;
   JTextField l_text = null;
   JPasswordField l_ptext = null;
   JButton btnlogin = null;
   JButton btncancel = null;
   Scanner sc = null;
   Connection con = null;

   Demo()
   {
    int i =connection();
    if(i==1)
   { 
    setTitle("Login Page");
    setVisible(true);
    getContentPane().setBackground(java.awt.Color.pink);
     setSize(500,300);
     setLocation(400,100);
     setLayout(null);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     comp();
   }
   }
   int connection()
   {

     
     try
     {Class.forName("com.mysql.cj.jdbc.Driver");
      con =DriverManager.getConnection("jdbc:mysql://localhost:3306/Torbit","root","shreyash@123");
      return 1;
     }
     catch(Exception e)
     {

      System.out.println("connection forbidden "+ e.toString());
      return 0;
     }

   }
    
   void comp()
   {
      l_username = new JLabel();
    l_username.setText("UserName");
    add(l_username);
    l_username.setBounds(100,20,100,20);
    
      l_text = new JTextField();
      add(l_text);
      l_text.setBounds(220,20,100,20);

      l_pass = new JLabel();
      l_pass.setText("Password");
      add(l_pass);
      l_pass.setBounds(100,60,100,20);
      
      l_ptext = new JPasswordField();
      add(l_ptext);
      l_ptext.setBounds(220,60,100,20);
      l_ptext.addKeyListener(new Enter());


      btnlogin = new JButton();
      btnlogin.setText("Login");
      add(btnlogin);
      btnlogin.setBounds(120,100,100,20); 
      btnlogin.addActionListener(this);

      btncancel = new JButton();
      btncancel.setText("Cancel");
      add(btncancel);
      btncancel.setBounds(260,100,100,20);
      btncancel.addActionListener(this);
   }

   class Enter extends KeyAdapter
   {
      public void keyReleased(KeyEvent ke)
      {
         if(ke.getSource() == l_ptext)
         {
            if(ke.getKeyText(ke.getKeyCode())=="Enter")
            {
               System.out.println("checking for login details");
               checkLogin();
            }
         }
      } 
   }
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()==btnlogin)
      { 
        checkLogin();
      
    }
      else if(e.getSource()==btncancel)
      {
         System.out.println("Cancel");
      }
    }
    
    void checkLogin()
    {
       try
       {Statement st= con.createStatement();
       String query="select count(*)from gui.logins where username='"+l_text.getText()+"'and password = '"+l_ptext.getText()+"'";
       ResultSet rs = st.executeQuery(query);
       rs.next();
       if(rs.getInt(1)==1)
       {
         System.out.println("Verified :) welcome "+l_text.getText());
         BE obj = new BE("welcome");
       }else{
         //System.out.println("Invalid Credentials !!!!!");
         JOptionPane.showMessageDialog(this, "Invalid login");
            System.exit(0);
       }
    } catch(Exception ae)
      {
         System.out.println(ae.toString());
         return;
      }
    }
   

 }

class Check
{
public static void main(String a[])
    {
    
     Demo frame = new Demo();

    }
}
