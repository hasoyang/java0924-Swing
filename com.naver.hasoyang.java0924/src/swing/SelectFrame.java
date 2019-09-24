package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SelectFrame extends JFrame {
	JComboBox<String> oop;
	JList<String> language;
	
	//ComboBox와 JList의 데이터를 출력하기 위한 배열
	String [] ar;
	String [] br;
	
	//버튼
	JButton btn;
	
	JTable table;
	
	//입력한 번호를 저장할 변수
	int idx = 2;
	JTextField txtName;
	//이름과 전화번호를 입력할 텍스트 필드
	JTextField txtPhone;
	//나이를 선택할 콤보박스
	JComboBox cbAge;
	//삽입과 삭제를 위한 버튼
	JButton btnInsert, btnDelete;
	
	
	public SelectFrame()
	{
		ar= new String[3];
		ar[0] = "Encpsulation - 캡슐화";
		ar[1] = "Inheritance - 상속성";
		ar[2] = "Polymorphism - 다형성";
		
		br = new String[4];
		br[0] = "Java";
		br[1] = "JavaScript";
		br[2] = "SQL";
		br[3] = "Swift";
		
		oop = new JComboBox<>(ar);
		language = new JList<>(br);
		btn = new JButton("확인");
		
		JPanel panel = new JPanel();
		
		panel.add(oop);
		panel.add(language);
		panel.add(btn);
		
		add("North", panel);
		
		//btn을 누르면 oop에서 선택한 항목과 language에서 선택한 항목을 콘솔에 출력
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//콤보 박스에서 선택한 항목의 인덱스를 찾이오기
				int idx = oop.getSelectedIndex();
				
				int [] idxes = language.getSelectedIndices();
				
				System.out.printf("선택된 oop : %s\n", ar[idx]);
				
				for(int i = 0; i < idxes.length; i++)
				{
					System.out.printf("선택된 language : %s\n", br[idxes[i]]);
				}
			}
			
		});
		
		//테이블에 출력할 데이터 배열을 생성
		String [] headers = {"번호", "이름", "나이", "전화번호"};
		String[][] dates = {{"1", "지현섭", "28", "010-8998-0000"},
							{"2", "양원모", "28", "010-0000-0000"}};
		
		//데이터 배열을 가지고 테이블에 출력할 모델을 생성
		DefaultTableModel model = new DefaultTableModel(dates, headers);
		
		table = new JTable(model);
		//테이블은 스크롤 가능한 Pane 위에 출력해야 합니다.
		JScrollPane sc = new JScrollPane(table);
		add(sc);
		
		JLabel lblName = new JLabel("이름");
		txtName = new JTextField(10);
		
		JLabel lblAge = new JLabel("나이");
		String [] arAge = new String[100];
		
		for(int i = 0; i < 100; i ++)
		{
			arAge[i] = String.format("%d",i+1);
		}
		
		cbAge = new JComboBox(arAge);
		
		JLabel lblPhone = new JLabel("전화번호");
		txtPhone = new JTextField(15);
		
		btnInsert = new JButton("추가");
		btnDelete = new JButton("삭제");
		
		JPanel pan = new JPanel();
		
		pan.add(lblName);
		pan.add(txtName);
		pan.add(lblAge);
		pan.add(cbAge);
		pan.add(lblPhone);
		pan.add(txtPhone);
		pan.add(btnInsert);
		pan.add(btnDelete);
		
		add("South", pan);
		
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				int age = cbAge.getSelectedIndex() +1;
				String phone = txtPhone.getText();
				
				//name은 좌우 공백없이 3자 이상
				if(name.trim().length() <3)
				{
					JOptionPane.showMessageDialog(null, "이름은 3자이상 필수 입력");
					txtName.setText("");
					cbAge.setSelectedIndex(0);
					txtPhone.setText("");
					return ;
				}
				
				//전화번호 필수 입력
				if(phone.trim().length() < 1)
				{
					JOptionPane.showMessageDialog(null, "전화번호 필수 입력!");
					txtName.setText("");
					cbAge.setSelectedIndex(0);
					txtPhone.setText("");
					return ;
				}
				
				//JTable은 MVC패턴으로 구현
				//JTable은 화면에서 출력되는 부분만 제어
				//데이터 삽입과 삭제는 모델이 수행
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				String[] row = {(idx++)+"",name.trim(), age+"" , phone.trim()};
				//데이터 추가
				model.addRow(row);
				JOptionPane.showMessageDialog(null, "추가 성공!");
				
				txtName.setText("");
				cbAge.setSelectedIndex(0);
				txtPhone.setText("");
			}
			
		});
		
		//삭제 버튼 클릭 이벤트 구현
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//테이블에서 선택된 행 번호 가져오기
				int idx = table.getSelectedRow();
				//선택이 안된 경우는 음수
				if(idx < 0)
				{
					JOptionPane.showMessageDialog(null, "선택하고 삭제를 누르세요!!");
					return;
				}
				
				//정말로 삭제를 할 것인지 확인
				int r = JOptionPane.showConfirmDialog(null, "정말로 삭제" , "진짜 삭제" , JOptionPane.OK_CANCEL_OPTION);
				
				if(r == JOptionPane.OK_OPTION)
				{
					DefaultTableModel model = (DefaultTableModel)table.getModel();
				
					model.removeRow(idx);
				}
			}
			
		});
		
		this.setBounds(100,100,500,200);
		//배치되는 컴포넌트 크기들을 가지고 프레임의 사이즈를 조정
		pack();
		this.setVisible(true);
		this.setTitle("항목 선택");
		
		//닫기 버튼을 누를 때 종료해주는 기능 추가
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
