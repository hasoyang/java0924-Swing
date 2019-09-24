package swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LabelFrame extends JFrame {
	JLabel lbl;
	Thread th1;
	ImageIcon [] images;
	ImageIcon [] su;
	int x;
	public LabelFrame() {
		setLayout(new FlowLayout());
		
		images = new ImageIcon[3];
		images[0] = new ImageIcon("./1.png");
		images[1] = new ImageIcon("./2.png");
		images[2] = new ImageIcon("./3.png");
		
		su = new ImageIcon[10];
		//현재 디렉토리 경로를 확인
		String curDir = System.getProperty("user.dir");
		System.out.printf("%s\n", curDir);
		//절대 경로를 이용해서 ImageIcon을 생성
		//Windows의 절대 경로는 디렉토리호가\\
		//ImageIcon icon = new ImageIcon("/Users/jihyunsub/Documents/아이유.jpg");
		
		//상대 디랙토리 이용
		//ImageIcon icon = new ImageIcon("./아이유.jpg");
		
		//이미지 아이콘을 이용해서 레이블을 생성
		lbl = new JLabel(images[0]);
		add(lbl);
		/*
		Thread th = new Thread() {
			int idx = 0;
			public void run()
			{
				while(true)
				{
					try {
						idx = idx + 1;
						Thread.sleep(100);
						lbl.setIcon(images[idx%images.length]);
						
					} catch (InterruptedException e) {}
				}
			}
		};
		th.start();
		*/
		
		JButton start = new JButton("시작");
		add(start);
		JButton end = new JButton("멈춤");
		add(end);
		
		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				th1.interrupt();
				System.out.println(x);
			}
			
		});
		for(int i = 0 ; i < 10; i ++)
		{
			su[i] = new ImageIcon("./"+i+".png");
		}
		start.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				th1 = new Thread()
				{
					public void run()
					{
						while(true)
						{
							try {
								int idx;
								Random r = new Random();
								idx = r.nextInt(10);
								Thread.sleep(10);
								lbl.setIcon(su[idx]);
								x = idx;
								
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								return;
							}
						}
					}
				};
				th1.start();
				
			}
			
		});
		//닫기 버튼을 누를 때 종료 기능을 수행하도록 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setBounds(100,100,1000,1000);
		this.setTitle("레이블 애니메이션");
		this.setVisible(true);
	}
}
