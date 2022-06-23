package kr.ac.kopo.ctc.kopo18.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;

class ScoreItemDaoTest {
	ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();

	@Test
	void testCreate() {
		ScoreItem scoreItem = new ScoreItem();
//		scoreItem.setId(1);		
		scoreItem.setName("오수현");
//		scoreItem.setStudentid(100);
		scoreItem.setKor(99);
		scoreItem.setEng(99);
		scoreItem.setMat(99);
		
		ScoreItem scoreItemOutput = scoreItemDao.create(scoreItem);	
		
		ScoreItem scoreItem2 = scoreItemDao.selectOne(scoreItemOutput.getStudentid());
		
		assertEquals("오수현", scoreItem2.getName());
		//assertEquals("", scoreItemOutput.getId());
	}
	
	@Test
	void testSelectOne() {
		ScoreItem scoreItem = new ScoreItem();
		ScoreItem scoreItemOutput = scoreItemDao.selectOne(209901);	
		
		assertEquals(209901, scoreItemOutput.getStudentid());
		//assertEquals("", scoreItemOutput.getId());

	}
	
	@Test
	void testSelectAll() {
		List <ScoreItem> scoreItemOutput = new ArrayList<ScoreItem>();
		scoreItemOutput = scoreItemDao.selectAll(2, 10);
		
		for (int i = 0; i < scoreItemOutput.size(); i++) {
			assertEquals(209911+i, scoreItemOutput.get(i).getStudentid());
		}
	}
	
	@Test
	void testupdateOne() {
		ScoreItem scoreItem = new ScoreItem();
		String test = "updateOneOK3reff";
		scoreItem = scoreItemDao.updateOne(test, 209901, 88, 88, 88);
		
		ScoreItem scoreItem2 = scoreItemDao.selectOne(scoreItem.getStudentid());
		assertEquals(test, scoreItem2.getName());
	}
	
//	@Test
//	void testDeleteOne() {
//		scoreItemDao.deleteOne(209901);
//		ScoreItem scoreItem = scoreItemDao.selectOne(209901);
//		
//		assertEquals(0, scoreItem.getStudentid());
//		assertEquals(null, scoreItem.getName());
//	}
	
//	@Test
//	void testDeleteAll() {
//		scoreItemDao.deleteAll();
//		List <ScoreItem> scoreItemOutput = new ArrayList<ScoreItem>();
//		scoreItemOutput = scoreItemDao.selectAll(2, 10);
//		
//		assertEquals(0, scoreItemOutput.size());
//	}
}
