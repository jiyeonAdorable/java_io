package ezen.io.bytestream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 파일 읽기
 * @author 임지연
 *
 */
public class FileInputExample {

	public static void main(String[] args) {
		// 무식하게 c: 부터 해도되는데 리플렉션 쓰자 //.getFile() : .의 경로의 파일가져오기
		String file = FileInputExample.class.getResource("/assets/cat.jpg").getFile();
		InputStream in = null;
		try {
			// 입력 빨대
			in = new FileInputStream(file); // 빨대가 꽂아짐
			System.out.println(in.available());

//			int byteData=0;
//			while((byteData=in.read())!=-1) {
//				System.out.println(byteData);
//			}
//			System.out.println("파일 읽기 끝");
			
			// 효율적으로 파일 바이트 읽기
			byte[] buffer = new byte[1024];
			int byteCount = 0;
			int totalByte = 0;
//			int byteCount = in.read(buffer); // 배열 만들어진 개수
//			for (int i=0; i<byteCount; i++) {
//				System.out.println(buffer[i]); // 근데 왜 음수로 나올까
//				totalByte +=byteCount;
//			}
			while((byteCount=in.read(buffer))!=-1) {
				totalByte += byteCount;
				System.out.println(in.available());
			}
			System.out.println(totalByte+"바이트 파일 읽기 끝");
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { // 생성이 안될 수 있어서..
				if(in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
