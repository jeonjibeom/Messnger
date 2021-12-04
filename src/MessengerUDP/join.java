package MessengerUDP;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class join extends JFrame {
    private JPanel contentPane; //내용
    private JTextField JoinId; //아이디
    private JTextField JoinName; //이름
    private JPasswordField JoinPw; //패스워드

    public join(){
        setTitle("Join");
        setBounds(100,100,300,205);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0,0));

        JPanel panel = new JPanel();
        contentPane.add(panel,BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel LID = new JLabel("ID:");
        LID.setBounds(39,28,27,15);
        panel.add(LID);

        JoinId = new JTextField();
        JoinId.setBounds(96,25,116,21);
        panel.add(JoinId);
        JoinId.setColumns(10);

        JLabel LPW = new JLabel("PW:");
        LPW.setBounds(39,59,26,15);
        panel.add(LPW);

        JoinPw = new JPasswordField();
        JoinPw.setBounds(96,55,116,21);
        panel.add(JoinPw);
        JoinPw.setColumns(10);

        JLabel LNAME = new JLabel("NAME:");
        LNAME.setBounds(39,90,45,15);
        panel.add(LNAME);


        JoinName = new JTextField();
        JoinName.setBounds(96,87,116,21);
        panel.add(JoinName);
        JoinName.setColumns(10);
        
        //버튼부분
        JButton joinok = new JButton("완료");
        joinok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //UserDao 구현후 밑에 주석 처리 풀기
                /* 
                int loginok;
                loginok = UserDao.getInstance().insert(JoinId.getText(), new String(JoinPw.getPassword()),JoinName.getText());
                if(loginok!=0){
                    JOptionPane.showMessageDialog(null,"회원가입 성공");
                    dispose(); // setvisible(false)랑 같음
                }else{
                    JOptionPane.showMessageDialog(null,"이미 존재하는 아이디");
                
                }
                */
            }
        });
        joinok.setBounds(42,118,97,23);
        panel.add(joinok);

        JButton joincancel = new JButton("취소");
        joincancel.setBounds(148,118,97,23);
        panel.add(joincancel);
        joincancel.addActionListener(new ActionListener() { //취소 눌렀을떄 액션..
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
    static public void main(String[] args){
        new join();
    }

}
