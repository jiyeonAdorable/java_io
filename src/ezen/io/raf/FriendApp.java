package ezen.io.raf;

import java.util.*;
import java.io.*;

/**
 * FileFriendRepository 테스트를 위한 애플리케이션 클래스
 * @author 김기정
 */
public class  FriendApp{
	public static void main(String[] args) {
		FriendRepository repository;
		
		try{
			repository = new FileFriendRepository();
			
			// 파일에 친구(레코드) 추가 테스트
			repository.save(new Friend("김기정", 10, 61.34, "011-1111-8707"));
			repository.save(new Friend("김재훈", 20, 62.34, "010-2222-8707"));
			repository.save(new Friend("이대한", 30, 63.34, "010-3333-8707"));
			repository.save(new Friend("김정석", 40, 64.34, "010-4444-8707"));
			repository.save(new Friend("박찬울", 50, 65.34, "010-5555-8707"));
			repository.save(new Friend("홍길동", 60, 66.34, "010-6666-8707"));
			System.out.println("친구정보 파일 저장 완료!");
			
			// 전체 리스트..
			System.out.println("***** 등록된 모든 친구 리스트(총 "+repository.getRecordCount()+"명) *****");
			ArrayList<Friend> list = (ArrayList<Friend>) repository.findAll();
			for (Friend friend : list) {
				System.out.println(friend.getName() + "\t" + friend.getAge() + "\t" + friend.getWeight() + "\t" + friend.getTelephone());
			}

			// 리소스 해제
			repository.close();
			
		}catch(IOException e){
			e.printStackTrace();
		} 
	}
}
