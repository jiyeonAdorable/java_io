package ezen.io.bytestream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * BufferedInputStream을 이용해서..
 * @author 임지연
 *
 */
public class BufferedInputStreamExample {

	public static void main(String[] args) {
		
		String file = BufferedInputStreamExample.class.getResource("/assets/cat.jpg").getFile();
		InputStream in = null;
		
		try {
			in = new BufferedInputStream(new FileInputStream(file));
////			int byteData = in.read();
////			System.out.println(byteData); 이건 하나만 받는거야...
//			
//			byte[] array = new byte[512];
//			int count =in.read(array);
//			for (byte b : array) {
//				System.out.println(b); // 이건 512만 받은거야.. 다받으려면 for에 while써야해
//			}
		
		
//			in = System.in; // .in(표준 입력)은 BufferedInputStream다.
//			// 버퍼링.. 타자빠르면 화면이 못따라옴..
//			System.out.println(in);
		
//		System.out.println("이름: ");
//		byte[] array = new byte[512];
//		int count = System.in.read(array);
//		// 디코딩
//		String name = new String(array,0,count); //0부터 읽어들인 수만큼
//		System.out.println(name);
		
//		Scanner scanner = new Scanner(System.in); // 결론 스캐너 쓰자!!
//		String name = scanner.nextLine();
//		System.out.println(name);
			
		// BufferedInputStream 추가 기능
			in.mark(0);
			int data =in.read();
			System.out.println(data);
			in.read();
			in.read();
			in.read();
			in.skip(5);
			in.skip(5); //스킵
			in.skip(5);
			in.read();
			in.reset();
			data =in.read();
			System.out.println(data);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
