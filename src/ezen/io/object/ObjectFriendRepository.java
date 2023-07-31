package ezen.io.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import ezen.io.raf.Friend;
import ezen.io.raf.FriendRepository;

/**
 * 친구정보 파일 저장 및 관리 구현체 ObjectStream 활용
 * 
 * @author 김기정
 */
public class ObjectFriendRepository implements FriendRepository {

	/** 파일 저장 경로 */
	private static final String FILE_PATH = "friends.ser";

	private List<Friend> list;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	public ObjectFriendRepository() throws IOException, ClassNotFoundException {
		File file = new File(FILE_PATH);
		// 기존에 파일이 없는 경우..
		if (!file.exists()) {
			list = new ArrayList<Friend>();
		} else {
			// 기존 파일이 존재할 경우..
			in = new ObjectInputStream(new FileInputStream(file));
			list = (List<Friend>) in.readObject();
			in.close();
		}
	}

	@Override
	public int getRecordCount() {
		return list.size();
	}

	@Override
	public void save(Friend friend) throws IOException {
		
		// 기존에 등록된 친구인지 검증 필요..
		
		// 리스트에 친구 저장
		list.add(friend);
		saveFile();
	}

	public void saveFile() throws IOException  {
		// 리스트를 파일에 저장
		out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
		out.writeObject(list);
		out.flush();
	}

	@Override
	public List<Friend> findAll() throws IOException {
		return list;
	}

	@Override
	public void close() {
		try {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
