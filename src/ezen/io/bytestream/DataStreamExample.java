package ezen.io.bytestream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 여러 종류의 데이터를 담으려면 (byte말고) DataStream을 사용해야한다.
 * @author 임지연
 *
 */
public class DataStreamExample {
	static String file = "example.dat";
	public static void writeData() throws IOException {
		
		boolean flag = true;
		char lastName = '김';
		String firstName = "기정";
		int age = 50;
		double weight = 78.56;
		
		//DataOutput out = new DataOutputStream(new FileOutputStream(file));
		// .close() 쓰려고..ㅠ
		DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		out.writeBoolean(flag);
		out.writeChar(lastName);
		out.writeUTF(firstName);
		out.writeInt(age);
		out.writeDouble(weight);
		out.close();
		
		System.out.println("정보 파일 출력 완료");
	}
	
	public static void readData() throws IOException {
		boolean flag = false;
		char lastName = 0;
		String firstName = null;
		int age = 0;
		double weight = 0.0;
		
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		
		flag = in.readBoolean();
		lastName = in.readChar();
		firstName = in.readUTF();
		age = in.readInt();
		weight = in.readDouble();
		
		System.out.println(flag);
		System.out.println(lastName);
		System.out.println(firstName);
		System.out.println(age);
		System.out.println(weight);
		
		in.close();
	}
	

	public static void main(String[] args) throws IOException {
		//writeData();
		readData();

	}

}
