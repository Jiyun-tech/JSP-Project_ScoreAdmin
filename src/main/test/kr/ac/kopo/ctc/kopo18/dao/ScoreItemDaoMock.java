package kr.ac.kopo.ctc.kopo18.dao;

import java.util.List;

import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;

public class ScoreItemDaoMock implements ScoreItemDao {

	@Override
	public ScoreItem create(ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreItem selectOne(int studentid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreItem> selectAll(int page, int countPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreItem updateOne(String name, int studentid, int kor, int eng, int mat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOne(int studentit) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findPage(int studentid, int countPerPage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countVisitor() {
		// TODO Auto-generated method stub
		return 0;
	}

}
