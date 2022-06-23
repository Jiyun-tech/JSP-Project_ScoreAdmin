package kr.ac.kopo.ctc.kopo18.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao;
import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl;
import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoMock;
import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;
import kr.ac.kopo.ctc.kopo18.dto.Pagination;

class ScoreItemServiceTest {

	////////// Service에 있는 getPagination() 테스트
	private ScoreItemDao scoreItemDao = new ScoreItemDaoMock(); // 호출
	private ScoreItemService scoreItemService = ScoreItemServiceImpl.getInstance(); // 호출

	@BeforeEach
	void setUp() throws Exception {
		scoreItemService.setScoreItemDao(scoreItemDao);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testPagination0() {
		Pagination p = scoreItemService.getPagination(0, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 16); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 1); // 현재 페이지
	}

	@Test
	void testGetPagination() {
		Pagination pagination = scoreItemService.getPagination(1, 50, 15, 1025);
		// countPerPage : 50
		// 1 : 출력 페이지, 15 : 페이저 크기, 50 : 페이지당 데이터, 1025 : 총 데이터 수
		assertEquals(pagination.getPpPage(), 1);
		assertEquals(pagination.getpPage(), 1);
		assertEquals(pagination.getnPage(), 16);
		assertEquals(pagination.getNnPage(), 21);
		assertEquals(pagination.getcPage(), 1);
	}

	@Test
	void testPagination1() {
		Pagination p = scoreItemService.getPagination(5, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 16); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 5); // 현재 페이지
	}
	
	@Test
	void testPagination2() {
		Pagination p = scoreItemService.getPagination(-200000, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 16); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 1); // 현재 페이지
	}
	
	@Test
	void testPagination3() {
		Pagination p = scoreItemService.getPagination(21, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 21); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 21); // 현재 페이지
	}

	@Test
	void testPagination4() {
		Pagination p = scoreItemService.getPagination(100, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 21); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 21); // 현재 페이지
	}
	
	@Test
	void testPagination5() {
		Pagination p = scoreItemService.getPagination(-100, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 16); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 1); // 현재 페이지
	}
	
	@Test
	void testPagination6() {
		Pagination p = scoreItemService.getPagination(-10000, 50, 15, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 16); // 오른쪽
		assertEquals(p.getNnPage(), 21); // 가장 오른쪽
		assertEquals(p.getcPage(), 1); // 현재 페이지
	}
	
	
	@Test 
	void testGetPagination1() {
		Pagination pagination = scoreItemService.getPagination(101, 50, 15, 10001);
		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기, 1025 : 총 데이터 수
		assertEquals(1, pagination.getPpPage());
		assertEquals(76, pagination.getpPage());
		assertEquals(106, pagination.getnPage());
		assertEquals(201, pagination.getNnPage());
		assertEquals(101, pagination.getcPage());
	}

	
	@Test
	void testGetPagination2() {
		Pagination pagination = scoreItemService.getPagination(30000, 50, 15, 10001);
		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기, 1025 : 총 데이터 수
		assertEquals(1, pagination.getPpPage());
		assertEquals(181, pagination.getpPage());
		assertEquals(201, pagination.getnPage());
		assertEquals(201, pagination.getNnPage());
		assertEquals(201, pagination.getcPage());
	}
	
	@Test
	void testGetPagination3() {
		Pagination pagination = scoreItemService.getPagination(-20, 50, 15, 10001);
		// 1 : 출력 페이지, 50 : 페이지당 데이터, 15 : 페이저 크기, 1025 : 총 데이터 수
		assertEquals(1, pagination.getPpPage());
		assertEquals(1, pagination.getpPage());
		assertEquals(16, pagination.getnPage());
		assertEquals(201, pagination.getNnPage());
		assertEquals(1, pagination.getcPage());
	}

	@Test
	void testGetPagination4() {
		Pagination p = scoreItemService.getPagination(-200000, 0, 0, 1025);
		assertEquals(p.getPpPage(), 1); // 가장왼쪽
		assertEquals(p.getpPage(), 1); // 왼쪽
		assertEquals(p.getnPage(), 1); // 오른쪽
		assertEquals(p.getNnPage(), 1); // 가장 오른쪽
		assertEquals(p.getcPage(), 1); // 현재 페이지
	}
	
	@Test
	   void testPagination9() {
	      Pagination p = scoreItemService.getPagination(1 ,50, 15, 500);
	      assertEquals(1, p.getPpPage());
	      assertEquals(1, p.getpPage());
	      assertEquals(10, p.getnPage());
	      assertEquals(10, p.getNnPage());
	      assertEquals(1, p.getcPage());
	   }
	
	
}
