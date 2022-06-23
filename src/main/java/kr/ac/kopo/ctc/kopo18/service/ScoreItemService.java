package kr.ac.kopo.ctc.kopo18.service;

import java.util.List;

import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao;
import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;
import kr.ac.kopo.ctc.kopo18.dto.Pagination;

public interface ScoreItemService {
	
	Pagination getPagination (int currentPage, int countPerPage, int pageSize, int totalCount);	// from dto
	// from dto
	// currentPage: 현재 페이지 , countPerPage: 한 페이지당 글 수, pageSize: 페이지 목록 수, totalCount: 총 글 수
	
	// create
	ScoreItem create(ScoreItem scoreItem);		// from Dao
	
	// read
	List<ScoreItem> selectAll(int page, int countPerPage); 	// from Dao
	ScoreItem selectOne(int studentid);						// from Dao
	int countAll();
	
	// update
	ScoreItem updateOne(String name, int studentid, int kor, int eng, int mat);	// from Dao
	
	// delete	
	void deleteOne(int studentit);	// from Dao
	void deleteAll();				// from Dao
	
	// 그 외
	int findPage(int studentid, int countPerPage);	// from Dao
	int countVisitor();
	
	//////////2022.06.17 : test 준비 ==> 함수 추가
	ScoreItemDao getScoreItemDao();

	void setScoreItemDao(ScoreItemDao scoreItemDao);
	////////////////////////////////////////////////////////////////////////////////
	
}

