package finaltest;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class MyFrame extends JFrame {
	MyFrame() {
		new MyDialog(this);

		setLayout(new FlowLayout());;
		setSize(180, 150);
		JFrame jf = new JFrame("Dialog Test"); //이건 뭐지

		JLabel msg = new JLabel("로그인창");
		JTextField id = new JTextField(JTextField.NEXT);
		JTextField pwd = new JTextField(JTextField.NEXT);
		JButton ok = new JButton("확인");

		add(msg);
		add(id);
		add(pwd);
		add(ok);
		//여기 셋 비져블 ㅇㄷ갓지? -> MyDialog에 있다!
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MyDialog.id_data.equals(id.getText()) && MyDialog.pwd_data.equals(pwd.getText())){
					//메신저 띄우기
					System.out.println("로그인 성공");
					setVisible(false);




					//setVisible(false);
				}else{ //다시 로그인창 띄우기
					System.out.println("로그인 실패~!!");
					new MyFrameRe();
				}

				setVisible(false);
			}
		});


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	setVisible(true);	// turn on by Dialog
	}


}
