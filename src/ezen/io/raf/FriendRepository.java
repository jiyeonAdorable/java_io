package ezen.io.raf;

import java.io.*;
import java.util.*;

/**
 * 친구정보 파일 저장 및 관리 관련 기능 명세
 * @author 김기정
 */
public interface FriendRepository {
	
	/** 저장된 친구 수 반환 */
	public int getRecordCount();
	
	/** 친구 정보 저장 */
	public void save(Friend friend) throws IOException;

	/** 친구 전체 목록 반환 */
	public List<Friend>  findAll() throws IOException;
	
	/** 리소스 해제 */
	public void close();
}


