package kr.ac.kopo.ctc.kopo18.dto;

public class Pagination {
	private int ppPage;		// previousprevious
	private int pPage;		// previous
	private int nPage;		// next
	private int nnPage;		// nextnext
	private int cPage;		// current
	private int listFirst; 
	private int listLast;
	
	// Generate Getters & Setters
	public int getPpPage() {
		return ppPage;
	}
	public void setPpPage(int ppPage) {
		this.ppPage = ppPage;
	}
	public int getpPage() {
		return pPage;
	}
	public void setpPage(int pPage) {
		this.pPage = pPage;
	}
	public int getnPage() {
		return nPage;
	}
	public void setnPage(int nPage) {
		this.nPage = nPage;
	}
	public int getNnPage() {
		return nnPage;
	}
	public void setNnPage(int nnPage) {
		this.nnPage = nnPage;
	}
	public int getcPage() {
		return cPage;
	}
	public void setcPage(int cPage) {
		this.cPage = cPage;
	}
	public int getListFirst() {
		return listFirst;
	}
	public void setListFirst(int listFirst) {
		this.listFirst = listFirst;
	}
	public int getListLast() {
		return listLast;
	}
	public void setListLast(int listLast) {
		this.listLast = listLast;
	}

	
}
