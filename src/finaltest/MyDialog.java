package finaltest;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

class MyDialog extends JDialog {
	static String id_data;
	static String pwd_data;
	public MyDialog(JFrame mf) {
		setLayout(new FlowLayout());;
		setSize(180, 150);
		
		JLabel msg = new JLabel("회원가입창", JLabel.CENTER);
		JTextField id = new JTextField(JTextField.NEXT);
		JTextField pwd = new JTextField(JTextField.NEXT);

		JButton ok = new JButton("OK");
		add(msg);
		add(id);
		add(pwd);
		add(ok);

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				id_data = id.getText();
				pwd_data = pwd.getText();
				adduser(id.getText(),pwd.getText());
				mf.setVisible(true); //뒤에 마이 프레임을 보여줘서 마이 프레임 부분에 셋 비져블이 없다.
			}
		});	
		setVisible(true);
	}

	//회원가입할때... 디비..
	public static Connection makeConnection(){
		String url = "jdbc:mariadb://localhost:3308/user_db";
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
	public void adduser(String uid,String pwd){
		Connection con = makeConnection();
		try{
			Statement stmt = con.createStatement();
			String s = "INSERT INTO user_table(uid,pwd) VALUES";
			s+= "('" + uid +"','"+pwd+"')";
			System.out.println(s);
			stmt.executeUpdate(s); //레코드 수정
		}catch (SQLException e){
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
