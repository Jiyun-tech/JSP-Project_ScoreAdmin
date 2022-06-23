package kr.ac.kopo.ctc.kopo18.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.ctc.kopo18.domain.ScoreItem;
import kr.ac.kopo.ctc.kopo18.service.ScoreItemServiceImpl;

public class ScoreItemDaoImpl implements ScoreItemDao {
	
	
	// Singleton Design Pattern 구현 (생성 관련 패턴)
	// 하나의 객체를 생성 후 인스턴스를 재사용하는 싱글턴 패턴
	// 1. 객체 1회 생성
	private static ScoreItemDaoImpl instance = new ScoreItemDaoImpl();
	// 2. 생성자 외부에서 접근하지 못하게 private으로 지정
	private ScoreItemDaoImpl() {
	}
	// 3. 생성된 객체 접근할 수 있는 메서드 (return 값 ==> instance)
	public static ScoreItemDaoImpl getInstance() {
		return instance;
	}

// CREATE
	@Override
	public ScoreItem create(ScoreItem scoreItem) {
//		int id = scoreItem.getId();
		String name = scoreItem.getName();
//		int studentid;
		int kor = scoreItem.getKor();
		int eng = scoreItem.getEng();
		int mat = scoreItem.getMat();
		
		ScoreItem retScoreItem = new ScoreItem(); // 결과 저장 위한 객체 생성
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
			// 192.168.23.15 : 나의 Linux_ubunt 접속 ip (PC를 통해 Server로 접속하므로 localhost가 아닌 ip로 접속);
			// kopoctc : DB name;
			// 3306 : 3306을 포워딩한 주소 (Linux_ubuntu 가상머신의 Port Forwarding 확인);
			// root : database user id, kopo18: database user password;

			// 1. 학번 지정
			Statement stmt1 = conn.createStatement();
			ResultSet rset1 = stmt1.executeQuery("select studentid from examtable order by studentid asc;");
			
			int studentid_min = 209901;
			while (rset1.next()) {
				if (rset1.getInt(1) == studentid_min) {
					studentid_min = studentid_min + 1;
				} else {
					break;
				}	
			}
			int studentid = studentid_min;
			
			retScoreItem.setStudentid(studentid);
			
			// 2. 데이터 추가
			Statement stmt2 = conn.createStatement();
			stmt2.execute("insert into examtable (name, studentid, kor, eng, mat) values('"+name+"',"+studentid
														+","+kor+","+eng+","+mat+");");
			
			// 3. 추가한 데이터 확인
//			Statement stmt3 = conn.createStatement();
//			ResultSet rset3 = stmt3.executeQuery("select * from examtable where studentid="+studentid+";");
//			
//			while (rset3.next()) {
//				retScoreItem.setId(rset3.getInt(1));
//				retScoreItem.setName(rset3.getString(2));
//				retScoreItem.setStudentid(rset3.getInt(3));
//				retScoreItem.setKor(rset3.getInt(4));
//				retScoreItem.setEng(rset3.getInt(5));
//				retScoreItem.setMat(rset3.getInt(6));
//			}
			
			rset1.close();
//			rset3.close();
			stmt1.close();
			stmt2.close();
//			stmt3.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("class error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql error");
		}
		
		return retScoreItem;
		}
	
// READ
	@Override
	public ScoreItem selectOne(int studentid) {
		ScoreItem retScoreItem = new ScoreItem();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// JDBC 드라이버 로드

			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			ResultSet rset1 = stmt1.executeQuery("select *, (kor+eng+mat), (kor+eng+mat)/3, (select count(*)+1 from examtable as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat)) from examtable as b where studentid="+studentid+";");					
															// sql 쿼리 실행하여 나온 결과(ResultSet) 저장;
			while (rset1.next()) {
				retScoreItem.setId(rset1.getInt(1));
				retScoreItem.setName(rset1.getString(2));
				retScoreItem.setStudentid(rset1.getInt(3));
				retScoreItem.setKor(rset1.getInt(4));
				retScoreItem.setEng(rset1.getInt(5));
				retScoreItem.setMat(rset1.getInt(6));	
				retScoreItem.setSum(rset1.getInt(7));
				retScoreItem.setAverage(rset1.getInt(8));
				retScoreItem.setRank(rset1.getInt(9));
			}
			// 해당 데이터가 없거나, 일치하는 studentid가 없는 경우, 모든 값 null로 반환.
			
			rset1.close();
			stmt1.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retScoreItem;
	}

	@Override
	public List<ScoreItem> selectAll(int page, int countPerPage) {
		List<ScoreItem> retScoreItemList = new ArrayList<ScoreItem>();
		retScoreItemList.clear();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// JDBC 드라이버 로드

			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			ResultSet rset1 = stmt1.executeQuery("select count(*) from examtable;");
		
			// 총 item 개수
			int totalItem = 0;
			while (rset1.next()) {
				totalItem = rset1.getInt(1);				// 3
			}
			
			// 마지막 페이지
			int lastPage = 0;
			if (totalItem >= 1 && countPerPage >= 1) {
				if ( totalItem%countPerPage == 0) {
					lastPage = totalItem/countPerPage;		// 1
				} else {
					lastPage = totalItem/countPerPage + 1;
				}
			} else {
				lastPage = 1;
			}
			
			// 선택 페이지
			int selectedPage = 0;
			if (page < 1) {
				selectedPage = 1;
			} else if (page > lastPage) {
				selectedPage = lastPage;
			} else {
				selectedPage = page;								// 1
			}
			
			// 출력 첫 번째 데이터
			int printFirst = 0;
			if (countPerPage > 0) {
				printFirst = (selectedPage-1)*countPerPage;	// 0
			} else {
				printFirst = 0;
			}		
					
			// 해당하는 데이터 출력
			Statement stmt2 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			ResultSet rset2 = stmt2.executeQuery("select *, (kor+eng+mat), (kor+eng+mat)/3, (select count(*)+1 from examtable as a where (a.kor+a.eng+a.mat) > (b.kor+b.eng+b.mat)) from examtable as b order by studentid limit "+printFirst+","+countPerPage+";");					
															// sql 쿼리 실행하여 나온 결과(ResultSet) 저장;
			
			// ResultSet 돌려서 출력 데이터를 List에 저장
			while (rset2.next()) {
				ScoreItem retScoreItem = new ScoreItem();
				retScoreItem.setId(rset2.getInt(1));
				retScoreItem.setName(rset2.getString(2));
				retScoreItem.setStudentid(rset2.getInt(3));
				retScoreItem.setKor(rset2.getInt(4));
				retScoreItem.setEng(rset2.getInt(5));
				retScoreItem.setMat(rset2.getInt(6));
				retScoreItem.setSum(rset2.getInt(7));
				retScoreItem.setAverage(rset2.getInt(8));
				retScoreItem.setRank(rset2.getInt(9));
				retScoreItemList.add(retScoreItem);
			}
			
			rset1.close();
			rset2.close();
			stmt1.close();
			stmt2.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
															
		return retScoreItemList;
	}
	
	@Override	
	public int countAll() {
		int count = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// JDBC 드라이버 로드

			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			ResultSet rset1 = stmt1.executeQuery("select count(*) from examtable;");					
															// sql 쿼리 실행하여 나온 결과(ResultSet) 저장;
			while (rset1.next()) {
				count = rset1.getInt(1);
			}
			// 해당 데이터가 없거나, 일치하는 studentid가 없는 경우, 모든 값 null로 반환.
			
			rset1.close();
			stmt1.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
		return count;
	}

// UPDATE
	@Override
	public ScoreItem updateOne(String name, int studentid, int kor, int eng, int mat) {
		ScoreItem retScoreItem = new ScoreItem();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// JDBC 드라이버 로드

			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			stmt1.execute("UPDATE examtable SET name='"+name+"', kor="+kor+", eng="+eng+", mat="+mat+" where studentid="+studentid+";");					

			stmt1.close();
			conn.close();
			
			retScoreItem.setStudentid(studentid);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retScoreItem;
	}

// DELETE
	@Override
	public void deleteOne(int studentid) {
		ScoreItem retScoreItem = new ScoreItem();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	// JDBC 드라이버 로드

			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");	
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			stmt1.execute("delete from examtable where studentid="+studentid+";");					

			stmt1.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

	@Override
	public void deleteAll() {
		
		ScoreItem scoreItem = new ScoreItem();
		int finalItemCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			stmt1.execute("delete from examtable;");	// 테이블 내 데이터 전체 삭제
			
			stmt1.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	@Override
	public int findPage(int studentid, int countPerPage) {
		ScoreItem scoreItem = new ScoreItem();
		int dataNum = 0;
		int pageFound = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");
															//127.0.0.1 = localhost
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			ResultSet rset1 = stmt1.executeQuery("select * from examtable order by studentid asc;");	// 테이블 내 데이터 전체 삭제
			
			while (rset1.next()) {
				if (rset1.getInt(3) == studentid) {
					break;
				} else {
					dataNum++;
				}
			}
			
			pageFound = (dataNum)/countPerPage + 1;
			// 참고 : 첫 번재 데이터의 dataNum == 0;
			
			rset1.close();
			stmt1.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return pageFound;
	}

	@Override
	public int countVisitor() {
		int visitor = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://192.168.23.15:3306/kopoctc", "root", "kopo18");
															//127.0.0.1 = localhost
			// 기존 방문자 수 세기
			Statement stmt1 = conn.createStatement();		// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			String QueryTxt1 = String.format("select count(*) from examtable_spring_countvisitor;"); 
			ResultSet rset1 = stmt1.executeQuery(QueryTxt1);	
			// sql 쿼리 실행 --> 방문자수 세기 위한 테이블의 데이터셋 모두 세기
			
			if (rset1.next()) {			// count(*) 결과 -> 해당 숫자 (count)를 cnt에 넣기 (데이터 없어도 0으로 나옴)
				visitor = rset1.getInt(1);
			} else {					// 데이터가 없는 경우, cnt = 0; 으로 설정 (기존 방문자 X)
				visitor = 0;
			}
			visitor++; 				// 방문자 수 + 1;
			
			// 데이터 추가하기
			Statement stmt2 = conn.createStatement();	// Statement(sql 쿼리 전달) 변수에 database연결-명령어 저장;
			String QueryTxt2 = String.format("insert into examtable_spring_countvisitor values("+visitor+");");
			stmt2.execute(QueryTxt2);					// 데이터 추가 명령 전달;
			
			// 데이터베이스 연결 종료.
			rset1.close();
			stmt1.close();
			stmt2.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return visitor;
	}
}
