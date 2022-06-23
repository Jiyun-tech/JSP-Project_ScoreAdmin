package kr.ac.kopo.ctc.kopo18.dao;

import java.util.List;
import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;

//DAO 역할 : CRUD + 페이지 처리 + 검색
public interface ScoreItemDao {
// create
		ScoreItem create(ScoreItem scoreItem);
// read
		ScoreItem selectOne(int studentid);
		List<ScoreItem> selectAll(int page, int countPerPage);
		int countAll();	// for Pagination method
// update
		ScoreItem updateOne(String name, int studentid, int kor, int eng, int mat);
// delete	
		void deleteOne(int studentit);
		void deleteAll();
// 그 외
		int findPage(int studentid, int countPerPage);
		int countVisitor();
}
