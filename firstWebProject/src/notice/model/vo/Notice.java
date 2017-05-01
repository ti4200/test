package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable{
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private java.sql.Date noticeDate;
	private String noticeContent;
	private int hit;
	
	public Notice(){}	
	
	public Notice(String noticeTitle, String noticeWriter, String noticeContent) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
	}

	public Notice(int noticeNo, String noticeTitle, String noticeWriter, Date noticeDate, String noticeContent, int hit) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeDate = noticeDate;
		this.noticeContent = noticeContent;
		this.hit = hit;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}	
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	@Override
	public String toString(){
		return noticeNo + ", " + noticeTitle + ", " + noticeWriter + ", " + 
				noticeDate + ", " + noticeContent + ", " + hit; 
	}
}
