package kr.ac.kopo.ctc.kopo18.domain;

public class ScoreItem {
	private int id;
	private String name;			
	private int studentid;		// 용도 : 사용자에게 보여주는 구분
	private int kor;
	private int eng;
	private int mat;
	private int sum;
	private float average;
	private int rank;
	
	public ScoreItem() {};
	
	public ScoreItem(String name, int kor, int eng, int mat) {
		super();
		this.name = name;
		this.studentid = 0;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}
	
	// Generate Getters and Setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMat() {
		return mat;
	}
	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
