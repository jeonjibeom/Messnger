package MessengerUDP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JTextField id;
    private JPasswordField pw;
    public static void main(String[] args){
        //스레드 사용하기 위한 코드
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                try{
                    Login frame = new Login();
                    frame.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,240,160);

        JPanel panel = new JPanel();
        getContentPane().add(panel,BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel LID = new JLabel("ID:");
        LID.setBounds(24,29,27,15);
        panel.add(LID);

        id = new JTextField();
        id.setBounds(71,26,116,21);
        panel.add(id);
        id.setColumns(10);

        JLabel LPW = new JLabel("PW:");
        LPW.setBounds(25,60,26,15);
        panel.add(LPW);

        pw = new JPasswordField();
        pw.setBounds(71,57,116,21);
        panel.add(pw);
        pw.setColumns(10);

        //버튼 생성 + 로그인시 DB와 연결해 성공 여부를 알아온다.

        JButton login  = new JButton("login");
        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //로그인시 데베에 있는 회원정보와 비교하여 성공 or 실패
                /*
                try{
                    boolean loginOk;
                    loginOk = UserDao.getInstance().loginCheck(id.getText(),new String(pw.getPassword()));
                    if(loginOk){
                        UserDao.getInstance().loginSuccess(id.getText());
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"로그인 실패");
                    }
                }catch(Exception ee){
                    ee.printStackTrace();
                }
                */
            }
        });
        login.setBounds(12,88,96,23);
        panel.add(login);

        //비번쪽..
        JButton join = new JButton("join");
        join.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //join 클릭했을떄.. 액션..
                join frame = new join();
                frame.setVisible(true);
                //회원가입 창은 visible, 로그인창은 냅둘까
                //아니면 사리지게 하고 회원가입 후 다시 쓰게 할까...?
            }
        });
        join.setBounds(120,88,96,23);
        panel.add(join);

    }
}
