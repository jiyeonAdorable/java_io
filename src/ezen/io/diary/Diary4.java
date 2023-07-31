package ezen.io.diary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;

public class Diary4 extends Frame{
	
	MenuBar menuBar;
	Menu fileMenu;
	MenuItem newMI, openMI, saveMI, exitMI;
	Label todayL;
	TextArea contentsTA;
	
	
	public Diary4() {
		this("제목없음");
	}
	
	public Diary4(String title) {
		super(title);
		menuBar = new MenuBar();
		fileMenu = new Menu("파일");
		newMI = new MenuItem("새로 만들기");
		openMI = new MenuItem("열기");
		saveMI = new MenuItem("저장");
		exitMI = new MenuItem("끝내기");
		
		todayL = new Label("", Label.RIGHT);
		contentsTA = new TextArea();
	}
	
	public void init() {
		setMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(newMI);
		fileMenu.add(openMI);
		fileMenu.add(saveMI);
		fileMenu.addSeparator();
		fileMenu.add(exitMI);
		newMI.setShortcut(new MenuShortcut(KeyEvent.VK_N)); // 단축키
		openMI.setShortcut(new MenuShortcut(KeyEvent.VK_O));
		saveMI.setShortcut(new MenuShortcut(KeyEvent.VK_S));
		exitMI.setShortcut(new MenuShortcut(KeyEvent.VK_X));
		
		add(todayL, BorderLayout.NORTH);
		add(contentsTA, BorderLayout.CENTER);
	}
	
	private void setToday() { // 시간 자동으로 업데이트 스레드 추가
		todayL.setBackground(Color.pink);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					String format = String.format("%1$tF %1$tT (%1$tA)", Calendar.getInstance());
					todayL.setText(format);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	} 
	
	private void setNew() {
		contentsTA.setText(""); //빈문자열이 지우는거였다
	}
	
	private void openFile() {
		// 파일탐색기 열어주기
		// dialog라 부모있어야함, 제목, 모드(상수)
		FileDialog fd = new FileDialog(this,"일기 열기",FileDialog.LOAD);
		fd.setVisible(true);
		
		String baseDir = "./";
		File dir1 = new File(baseDir+"files");
		if(!dir1.exists()) {
			dir1.mkdir();
		}
		
		// 선택한 일기 가져와야함 (파일 읽어오기)
		String openFile = null;
		openFile = fd.getFile(); // 경로 문자열로 가져옴...
		File file = new File(dir1+"/"+openFile);
	
		DataInputStream in;
		try {
			in = new DataInputStream(new FileInputStream(file));
			byte[] array = new byte[(int)file.length()];
			in.read(array);
			String text = new String(array, "MS949");
			contentsTA.setText(text);
			setTitle(openFile);

			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void saveFile() {
		
		// dialog라 부모있어야함, 제목, 모드(상수)
		FileDialog fd = new FileDialog(this,"일기 저장",FileDialog.SAVE);
		fd.setVisible(true);
		
		String saveFile = null;
		
		saveFile = fd.getFile(); // 파일 제목 가져오기(취소 누를수 있음)
		
		if(saveFile ==null) {
			return;
		}
		
		// 저장 위치는 현재 프로젝트아래 폴더(files) 만들고 이 안에 저장.
		String baseDir = "./";
		File dir1 = new File(baseDir+"files");
		if(!dir1.exists()) {
			dir1.mkdir();
		}
		
		String diary = contentsTA.getText();
		
		DataOutputStream out;
		String format = String.format("%1$tF", Calendar.getInstance());
		String format1 = String.format("%1$tF (%1$tA)\n", Calendar.getInstance())+"\n";
		byte[] formatByte = format1.getBytes(Charset.forName("MS949"));
		try {
			out = new DataOutputStream(new FileOutputStream(dir1+"/"+format+".dat"));
			byte[] euckrStringBuffer = diary.getBytes(Charset.forName("MS949"));
			
			out.write(formatByte);
			out.write(euckrStringBuffer);
			setTitle(saveFile);
			out.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}
	
	public void addEventListener() {
		class ActionHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Object eventSource = e.getSource();
				if(eventSource == newMI) {
					setNew();
				}else if(eventSource == openMI) {
					openFile();
				}else if(eventSource == saveMI) {
					saveFile();
				}else if(eventSource == exitMI) {
					exit();
				}
			}
		}
		ActionListener actionListener = new ActionHandler();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				setToday();
			}
		});
		
		newMI.addActionListener(actionListener);
		openMI.addActionListener(actionListener);
		saveMI.addActionListener(actionListener);
		exitMI.addActionListener(actionListener);
	}
	
	public static void main(String[] args) {
		Diary diary = new Diary();
		diary.init();
		diary.setSize(700, 500);
		diary.addEventListener();
		diary.setVisible(true);
	}
}
