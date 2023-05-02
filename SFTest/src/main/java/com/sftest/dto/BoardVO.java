package com.sftest.dto;

	public class BoardVO {
		
		private int seqno;
		private String writer;
		private String userid;
		private String password;
		private String title;
		private String regdate;
		private int hitno;
		private String content;
		public int getSeqno() {
			return seqno;
		}
		public void setSeqno(int seqno) {
			this.seqno = seqno;
		}
		public String getWriter() {
			return writer;
		}
		public void setWriter(String writer) {
			this.writer = writer;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getRegdate() {
			return regdate;
		}
		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}
		public int getHitno() {
			return hitno;
		}
		public void setHitno(int hitno) {
			this.hitno = hitno;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
}
