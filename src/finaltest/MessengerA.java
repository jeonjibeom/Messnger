package finaltest;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MessengerA {
    JTextField textField;
    JTextArea textArea;
    DatagramSocket socket;
    DatagramPacket packet;
    InetAddress address = null;
    final int myPort = 5000;
    final int otherPort = 6000;
    //데이터 베이스 부분
    public static Connection makeConnection(){
        String url = "jdbc:mariadb://localhost:3308/msg_db";
        String id = "root";
        String password = "2267";
        Connection con = null;

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 적재 성공");
            con = DriverManager.getConnection(url,id,password);
            System.out.println("데이터 베이스 연결 성공");
        }catch (ClassNotFoundException e){
            System.out.println("드라이버를 찾을 수 없습니다.");
        }catch (SQLException e){
            System.out.println("연결에 실패했습니다.");
        }
        return con;
    }
    private static void addmsg(String msg){
        Connection con = makeConnection();
        try{
            Statement stmt = con.createStatement();
            String s = "INSERT INTO msg_table(msg) VALUES";
            s += "('" + msg + "')";
            System.out.println(s);
            int i = stmt.executeUpdate(s); // 레코드 수정할때 사용
            if(i == 1)
                System.out.println("레코드 추가 성공");
            else
                System.out.println("레코드 추가 실패");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    //메신저 부분
    public MessengerA() throws IOException{
        MyFrame f = new MyFrame(); //이 마이 프레임은 메신저A의 마이 프레임이다.
        address = InetAddress.getByName("127.0.0.1");
        socket = new DatagramSocket(myPort);
    }
    public void process(){
        while(true){
            try
            {
                byte[] buf = new byte[256];
                packet = new DatagramPacket(buf,buf.length);
                socket.receive(packet);
                textArea.append("RECIEVED: "+new String(buf)+"\n");
                addmsg(new String(buf).trim());
            }
            catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    class MyFrame extends JFrame implements ActionListener{
        public MyFrame(){
            super("MessengerA");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            textField = new JTextField(30);
            textField.addActionListener(this);

            textArea = new JTextArea(10,30);
            textArea.setEditable(false);

            add(textField, BorderLayout.PAGE_END);
            add(textArea,BorderLayout.CENTER);
            pack();
            setVisible(true);
        }


        public void actionPerformed(ActionEvent evt) {
            String s = textField.getText();
            byte[] buffer = s.getBytes();
            DatagramPacket packet;

            packet = new DatagramPacket(buffer,buffer.length,address,otherPort);
            try{
                socket.send(packet);
            }catch (IOException e){
                e.printStackTrace();
            }
            textArea.append("SENT : "+s+"\n");
            textField.selectAll();
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }



}
