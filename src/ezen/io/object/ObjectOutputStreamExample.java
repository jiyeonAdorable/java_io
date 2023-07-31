package ezen.io.object;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 객체가 파일로 저장 가능하기 위해 반드시 Serializable 인터페이스를 구현한다.
 * Serializable 인터페이스는 대표적인 마커인터페이스이다..
 * @author 김기정
 * @Date   2023. 1. 31.
 */
class Student implements Serializable {

	private static final long serialVersionUID = -2851248179192574920L;
	
	private int no;
	private String name;
	// 객체 저장 대상에서 제외
	private int score;
	//private transient int passwd;
	private int xxxx;
	
	public Student(int no, String name, int score) {
		this.no = no;
		this.name = name;
		this.score = score;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [no=" + no + ", name=" + name + ", score=" + score + "]";
	}
}

public class ObjectOutputStreamExample {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 사용자 정의 클래스
		Student student = new Student(10, "김기정", 100);
		// JDK 기본 제공 표준 API 클래스
		String message = "이것이 객체 스트림이다..";
		// 기본 데이터타입
		int age = 30;
		
		List<Student> list = new ArrayList<>();
		list.add(new Student(10,"가씨",30));
		list.add(new Student(11,"나씨",30));
		list.add(new Student(12,"다씨",30));
		String fileName = "students.ser";
		
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(fileName));
		// Obejct -> 연속적인 바이트 흐름으로 변환하여 출력 (객체 직렬화)
		out.writeObject(student);
		out.writeObject(message);
		out.writeObject(age); // AutoBoxing 적용
		out.writeObject(list);
	
		
		out.close();
		System.out.println("메모리상의 객체를 연속적인 바이트 흐름으로 변환해서 파일에 저장 완료..");

	}

}



