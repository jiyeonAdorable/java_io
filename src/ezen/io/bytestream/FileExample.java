package ezen.io.bytestream;

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

public class FileExample {
	
	public static void fileInfo() {
//		String path = "C:/ezen-fullstack/tools/eclipse/eclipsec.exe";
//		String path = "C:/ezen-fullstack/tools/eclipse/";
		String path = "src/assets/student.jpg";
		File file = new File(path);
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(file.length());
		System.out.println(file.exists());
		System.out.println(file.lastModified());
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(file.lastModified());
		System.out.printf("%1$tF %1$tT", date);
		System.out.println();
		if(file.isDirectory()) {
			System.out.println("디렉터리");
			
		}else if(file.isFile()) {
			System.out.println("파일");
		}
		
		System.out.println(file.isAbsolute());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsoluteFile().length());
	}
	
	public static void dir() {
		// 사용자 홈디렉토리의 파일 목록 출력
//		System.out.println(System.getProperty("os.name"));
//		System.out.println(System.getProperty("os.version"));
//		System.out.println(System.getProperty("user.name"));
//		System.out.println(System.getProperty("user.home"));
//		System.out.println(System.getProperty("user.dir"));
//		
//		Properties prop = System.getProperties();
//		Enumeration<Object> keys = prop.keys();
//		while(keys.hasMoreElements()) {
//			String key = (String)keys.nextElement();
//			System.out.println(key + "=" + prop.getProperty(key));
//		}
		
		String homeDirectory = System.getProperty("user.home");
		File directory = new File(homeDirectory);
		File[] fileList = directory.listFiles();
		for (File file : fileList) {
			if(file.isDirectory()) {
				System.out.println("<DIR>  " + file.getName());
			}else if(file.isFile()) {
				System.out.println("<FILE>  " + file.getName() + " " +file.length());
			}
		}
	}
	
	// 사용자 홈디렉토리/Downloads/myApplication 디렉토리를 생성하고,
	// mytest.txt 파일을 생성하시요...
	public  static void createDirectory() {
		String downloadDirectory = System.getProperty("user.home") + "/Downloads";
		File directory = new File(downloadDirectory + "/myApplication");
		if(directory.mkdir()) {
			File newFile = new File(directory.getAbsolutePath() + "/mytest.txt");
			try {
				boolean isCreated = newFile.createNewFile();
				if(isCreated) {
					System.out.println("파일 생성 완료");
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	} 
	
	

	public static void main(String[] args) {
		fileInfo();
//		dir();
		//createDirectory();

	}

}

