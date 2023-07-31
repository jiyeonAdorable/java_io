package ezen.io.bytestream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutStreamExample {

	public static void main(String[] args) throws IOException {
		String file = OutStreamExample.class.getResource("/assets/").getFile();
		file += "example.dat";
		//OutputStream out = new FileOutputStream(file);
		OutputStream out = new FileOutputStream(file,true); // 파일에 계속 내용을 추가
//		byte byteData = 97;
//		out.write(byteData);
//		out.close();
		
		byte[] buffer = {97,98,99,100,101};
		out.write(buffer,0,3);
		out.close();
		System.out.println("파일 출력 완료");
		
//		out = System.out;
//		out.write((char)97); //힝.. 안나와 다른데서는 나온대 나중에 확인해바..
		// 그래서 추가된 PrintStream을 쓰라..
	}

}
