package com.yi.domain;

import java.util.Arrays;
import java.util.Date;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int viewcnt;
	private int replycnt;
	private String[] files;//Ã·ºÎÆÄÀÏ
	
	public BoardVO() {

	}
	public BoardVO(int bno, String title, String content, Date regdate, int viewcnt) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.viewcnt = viewcnt;
	}
	
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", viewcnt=" + viewcnt + ", replycnt=" + replycnt + ", files=" + Arrays.toString(files)
				+ "]";
	}
	
	

}
