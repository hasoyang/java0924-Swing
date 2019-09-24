package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class LoginFrame extends JFrame {
	//아이디와 비밀번호라는 글자를 출력할 레이블
	JLabel lblId, lblPw;
	
	//상단에 문자열을 출력할 레이블
	JLabel lblTitle;
	
	//아이디와 비밀번호 입력할 텍스트 필드
	JTextField tfId;
	JPasswordField tfPw;
	
	//로그인 과 회원가입 버튼
	JButton btnLogin, btnRegister;
	
	public LoginFrame()
	{
		lblTitle = new JLabel("로그인 패널");
		EtchedBorder border = new EtchedBorder();
		//TitleBorder를 이용하면 경계선과 텍스트를 같이 출력
		lblTitle.setBorder(border);
		
		JPanel p1 = new JPanel();
		p1.add(lblTitle);
		add("North", p1);
		
		lblId = new JLabel("   아이디");
		tfId = new JTextField(20);
		//ToolTip은 마우스 커서가 위치 했을때 보여지는 보충설명
		tfId.setToolTipText("아이디를 입력하세요 !!!");
		JPanel idPanel = new JPanel();
		idPanel.add(lblId);
		idPanel.add(tfId);
		
		
		lblPw = new JLabel("비밀번호");
		tfPw = new JPasswordField(20);
		JPanel pwPanel = new JPanel();
		pwPanel.add(lblPw);
		pwPanel.add(tfPw);
		
		JPanel loginPanel = new JPanel();
		loginPanel.add(idPanel);
		loginPanel.add(pwPanel);
		
		add("Center", loginPanel);
		
		btnLogin = new JButton("로그인");
		btnRegister = new JButton("회원가입");
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(btnLogin);
		btnPanel.add(btnRegister);
		//btnLogin 버튼을 누르면 아이디와 비밀번호를 가져와서
		//아이디가 root 이고 비밀번호가 1234이면 로그인 성공이라고
		//그렇지 않으면 로그인 실패라고 lblTitle에 출력
		
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				char[] pw = tfPw.getPassword();
				String password = new String(pw);
				if(id.equals("root")  && password.equals("1234"))
				{
					lblTitle.setText("로그인 성공");
				}
				else
				{
					lblTitle.setText("로그인 실패");
				}
			}
		});
		//회원가입 버튼을 누르면 다른 프레임을 출력
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//다른 프레임 출력
				new LabelFrame();
			}
			
		});
		add("South", btnPanel);
		
		
		this.setBounds(100,100,300,200);
		this.setVisible(true);
		this.setTitle("로그인");
		
		//닫기 버튼을 누를 때 종료해주는 기능 추가
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
