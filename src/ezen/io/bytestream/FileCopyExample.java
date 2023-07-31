package ezen.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyExample {
	public static long copy(String srcFile, String copyFile) throws IOException {
		long copyCount = 0;
		File file = new File(srcFile);
		if(!file.exists()) { // 사전 차단
			throw new IOException("복사하고자 하는 파일을 찾을 수 없습니다..");
		}
		
		InputStream in = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(copyFile);
				
		byte[] buffer = new byte[1024]; //1024byte는 1KB
		int byteCount = 0;
		while((byteCount=in.read(buffer)) !=-1) {
			copyCount += byteCount;
			out.write(buffer,0,byteCount); // 아웃에 출력 0부터 읽어들인 배열만큼
		};
				
		System.out.println("파일 복사 완료!");
		in.close();
		out.close();
		return copyCount;
	}
	

	public static void main(String[] args) throws IOException {
		
		// 복사할 파일 경로
		String srcFile = FileCopyExample.class.getResource("/assets/cat.jpg").getFile();
		// 복사한 파일 저장 경로 + 이름
		String copyFile = FileCopyExample.class.getResource("/assets/").getFile()+"cat3.jpg";
		
		
		
//		InputStream in = new FileInputStream(srcFile);
//		OutputStream out = new FileOutputStream(copyFile);
//		
//		byte[] buffer = new byte[1024]; //1024byte는 1KB
//		int byteCount = 0;
//		while((byteCount=in.read(buffer)) !=-1) {
//			out.write(buffer,0,byteCount); // 아웃에 출력 0부터 읽어들인 배열만큼
//		};
//		in.close();
//		out.close();
//		System.out.println("파일 복사 완료!");
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long count;
				try {
					count = copy(srcFile,copyFile);
					System.out.println(count/1024+"KB 복사완료!");
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}).start();
	}

}
