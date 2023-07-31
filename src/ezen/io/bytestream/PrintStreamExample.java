package ezen.io.bytestream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

public class PrintStreamExample {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out1 = System.out; // 문자열로 변환해서 출력됨
		out1.println(5); 
		out1.printf("%+d", 1000000);
		
		
		// printstream은 문자열로 만들어지니까 txt로 만들어야한다.
		String file ="example2.txt"; 
		
		//PrintStream out = new PrintStream(new FileOutputStream(file));
		PrintStream out = new PrintStream(file); // 초 간단.... ㅠ
		out.print(true);
		out.print('김');
		out.print("기정");
		out.print(50);
		out.print(50.56);
		out.println();
		out.printf("%1$tF %1$tT", Calendar.getInstance());
		out.close();
		
		
		

	}

}
