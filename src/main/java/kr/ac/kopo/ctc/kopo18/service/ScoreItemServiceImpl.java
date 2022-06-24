package kr.ac.kopo.ctc.kopo18.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDao;
import kr.ac.kopo.ctc.kopo18.dao.ScoreItemDaoImpl;
import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;
import kr.ac.kopo.ctc.kopo18.dto.Pagination;

public class ScoreItemServiceImpl implements ScoreItemService {
	
	// Singleton Design Pattern 구현 (생성 관련 패턴) : 하나의 객체를 생성 후 인스턴스를 재사용하는 패턴
	// (1) 객체 1회 생성
	private static ScoreItemServiceImpl instance = new ScoreItemServiceImpl();
	// (2) 생성자 외부에서 접근하지 못하게 private으로 지정
	private ScoreItemServiceImpl() {
	}
	// (3) 생성된 객체 접근할 수 있는 메서드 (return 값 ==> instance)
	public static ScoreItemServiceImpl getInstance() {
		return instance;
	}
	
	// ScoreItemDaoImpl 객체 받아옴 (싱글톤 패턴 --> new 객체 생성하지 않고, 생성된 객체 받아옴)
	// private ScoreItemDao scoreItemDao = ScoreItemDaoImpl.getInstance();
	
	////////// 2022.06.17 : 연결한 new ScoreItemDaoImpl 인스턴스 삭제하고 독립함. (인터페이스 주체)
	private ScoreItemDao scoreItemDao; 
		
	////////// 2022.06.17 : test 준비 ==> ScoreItemDao에 대한 Getter / Setter 만들기 
	public ScoreItemDao getScoreItemDao() {
		return scoreItemDao;
	}

	public void setScoreItemDao(ScoreItemDao scoreItemDao) {
		this.scoreItemDao = scoreItemDao;
	}
	////////////////////////////////////////////////////////////////////////////////

	@Override
	public ScoreItem create(ScoreItem scoreItem) {
		// TODO Auto-generated method stub
		return scoreItemDao.create(scoreItem);
	}

	@Override
	public ScoreItem selectOne(int studentid) {
		// TODO Auto-generated method stub
		return scoreItemDao.selectOne(studentid);
	}

	@Override
	public List<ScoreItem> selectAll(int page, int countPerPage) {
		// TODO Auto-generated method stub
		return scoreItemDao.selectAll(page, countPerPage);
	}

	@Override
	public ScoreItem updateOne(String name, int studentid, int kor, int eng, int mat) {
		// TODO Auto-generated method stub
		return scoreItemDao.updateOne(name, studentid, kor, eng, mat);
	}

	@Override
	public void deleteOne(int studentid) {
		scoreItemDao.deleteOne(studentid);
	}

	@Override
	public void deleteAll() {
		scoreItemDao.deleteAll();
	}

	@Override
	public int findPage(int studentid, int countPerPage) {
		// TODO Auto-generated method stub
		return scoreItemDao.findPage(studentid, countPerPage);
	}	
	
	@Override
	public Pagination getPagination(int currentPage, int countPerPage, int pageSize, int totalCount) {
		//ScoreItem scoreItem = scoreItemDao.selectOne(1); 	// 테스트 --> ScoreItemServiceTest
		Pagination p = new Pagination();
		
		// 1. ppPage 결정
		int ppPage = 1;
		p.setPpPage(ppPage);
		
		// 2. nnPage 결정
		int nnPage = 0;
		if (totalCount > 0 && countPerPage > 0) {
			if (totalCount%countPerPage == 0) {
				nnPage = totalCount/countPerPage;
			} else {
				nnPage = totalCount/countPerPage + 1;
			}
		} else { // TotalCount, countPerPage가 0보다 작거나 같은 경우, nnPage 1로 설정.
			nnPage = 1;
		}
		p.setNnPage(nnPage);

		// 3. 현재 페이지 결정
		int cPage = 0;
		if (currentPage < 1) {
			cPage = 1;
		} else if (currentPage > nnPage) {
			cPage = nnPage;
		} else {
			cPage = currentPage;
		}
		p.setcPage(cPage);
		
		// 4. pPage 결정
		int pPage = 0;
		if (pageSize > 0) {
			if (cPage <= pageSize) {
				pPage = 1;
			} else {
				pPage = (cPage/pageSize)*pageSize - (pageSize-1);
			}
		} else { // pageSize가 0보다 작거나 같은 경우, pPage 1로 설정.
			pPage = 1;
		}
		p.setpPage(pPage);
		
		// 5. nPage 결정
		int nPage = 0;
		if (pageSize > 0) {
			if ( cPage >= (((nnPage- 1)/pageSize) * pageSize + 1 )) {
				nPage = nnPage;
			} else {
				nPage = ((cPage - 1)/pageSize + 1) * pageSize + 1;
			}
		} else { // pageSize가 0보다 작거나 같은 경우, nPage 1로 설정.
			nPage = 1;
		}
		p.setnPage(nPage);
		
		int fList = ((cPage-1)/pageSize)*pageSize + 1;
		p.setListFirst(fList);
		
		int lList = fList + pageSize -1;
		if (lList > nnPage) {
			lList = nnPage;
		}
		p.setListLast(lList);
		
		return p;		// 테스트 --> ScoreItemServiceTest
	}

	@Override
	public int countVisitor() {
		// TODO Auto-generated method stub
		return scoreItemDao.countVisitor();
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return scoreItemDao.countAll();
	}

}
