package ezen.io.bytestream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 네트워크 파일 읽기
 * @author 임지연
 *
 */
public class FileInputExample2 {

	public static void main(String[] args) {
		String urlString = "https://mml.pstatic.net/www/mobile/edit/20230523_1095/upload_1684853651858jmD48.png";
		URL url = null;
		try {
			url = new URL(urlString); // 주소 연결됨.
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		
		InputStream in = null;
		try { // 입력 빨대
			in = url.openStream(); // url에서 얻어와야댐
			
			
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
			
			//표준입력(키보드)
			System.out.println("입력해바");
//			int byteData = System.in.read();
//			System.out.println(byteData);
			in = System.in;
			byte[] buffer2 = new byte[10];
			while((byteCount=in.read(buffer2)) != -1) {
				for (int i = 0; i < byteCount; i++) {
					char c = (char)buffer2[i];  // 문자 디코딩
					System.out.print(c+"    ");
				}
			}
			
			
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
