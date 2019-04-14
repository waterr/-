package cal;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui {

	private JFrame myFrame = new JFrame("四则运算出题软件");


	private JPanel JPCtrl = new JPanel();
	private JPanel JPContext = new JPanel();

	private JLabel JLnum = new JLabel("题目数量");

	private JButton JBGene = new JButton("生成试题");
	private JButton JBAnswer = new JButton("生成答案");
	private JButton JBSave = new JButton("保存试题");
	
	private boolean fSave = false;

	private JTextField JTFnum = new JTextField(8);

	private JTextArea JTAProb = new JTextArea(30, 50);

	private List<Problem> myList = new ArrayList<Problem>();

	public Gui() {

		JFrame.setDefaultLookAndFeelDecorated(true);

		myFrame.setLocation(300, 300);
		myFrame.setSize(600, 600);
		myFrame.setResizable(false);
		myFrame.setVisible(true);

		JPCtrl.setSize(600, 150);
		// JPCtrl.setBackground(Color.blue);

		JPCtrl.add(JLnum);
		JPCtrl.add(JTFnum);
		JPCtrl.add(JBGene);
		JPCtrl.add(JBAnswer);
		JPCtrl.add(JBSave);

		JPContext.setSize(200, 400);
		// JPContext.setBackground(Color.cyan);
		JTAProb.setWrapStyleWord(true);
		
		JPContext.add(JTAProb);

		myFrame.add(JPCtrl, BorderLayout.NORTH);
		myFrame.add(JPContext, BorderLayout.SOUTH);

		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 为生成试题按钮设置监听
		JBGene.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JTAProb.setText("");
				myList.clear();
				String sNum = JTFnum.getText();
				int probNum = Integer.parseInt(sNum);

				for (int i = 0; i < probNum; i++) {
					Problem p = new Problem();
					myList.add(p);
					// System.out.println(p.getAnswer());
					// JTAProb.append(p.getAnswer()+"\n");
				}
				for (int i = 0; i + 1 < myList.size(); i += 2) {
					Problem t1 = myList.get(i);
					Problem t2 = myList.get(i + 1);
					JTAProb.append("\t");
					JTAProb.append(t1.getAnswer());
					JTAProb.append("\t\t");
					JTAProb.append(t2.getAnswer() + "\n");

				}

			}
		});

		// 为给出答案按钮设置监听
		JBAnswer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fSave = true;
				JTAProb.setText("");
				 
				for (int i = 0; i + 1 < myList.size(); i += 2) {
					Problem t1 = myList.get(i);
					Problem t2 = myList.get(i + 1);
					JTAProb.append("\t");
					JTAProb.append(t1.getAnswer());
					JTAProb.append(String.valueOf(t1.getResult()));
					JTAProb.append("\t\t");
					JTAProb.append(t2.getAnswer());
					JTAProb.append(String.valueOf(t2.getResult())+"\n"); 

				}

			}
		});
		// 为保存试题按钮设置监听
				JBSave.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
					
						
						try {
							String fileName = "F:/test.txt";
							File writeName = new File(fileName);
							writeName.createNewFile();
							FileWriter writer = new FileWriter(writeName);
							BufferedWriter out = new BufferedWriter(writer);
							for(int i=0; i+1<myList.size(); i+=2) {
								Problem p1 = myList.get(i);
								Problem p2 = myList.get(i+1);
								String str1 = String.format("%d %c %d %c %d = ",p1.getVar1(),p1.getOp1(),p1.getVar2(),p1.getOp2(),p1.getVar3());
								//String content1 = p1.getAnswer();
								out.write(str1);
								if(fSave==true) {
									String result = String.valueOf(p1.getResult());
									out.write(result);
								}
								out.write("\t\t");
								String str2 = String.format("%d %c %d %c %d = ",p2.getVar1(),p2.getOp1(),p2.getVar2(),p2.getOp2(),p2.getVar3());
								out.write(str2);
								if(fSave==true) {
									String result = String.valueOf(p2.getResult());
									out.write(result);
								}
								out.write("\r\n");
								
							}

						//	out.flush();
							out.close();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						fSave = false;
						
						
						
						
						 

					}
				});
		
		
		

	}

}
