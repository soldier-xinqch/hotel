package org.hotel.common;

public interface CommEnum {
	
	public enum MENUENUM implements CommEnum{
		CONSOLE("CONSOLE"),//控制台
		STAFF("STAFF"),//人员管理
		MENU("MENU");
		private String value;
		
		MENUENUM(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	public enum RESULTFLAG implements CommEnum{
		SUCCESS("success"),
		ERROR("error");
		
		private String value;
		RESULTFLAG(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}

	
	public enum MENULEVEL implements CommEnum{
		MAIN("0"),
		FIRST("1"),
		SECOND("2"),
		THIRD("3");
		
		private String value;
		MENULEVEL(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	
	public enum MENUSTATUS implements CommEnum{
		DOING("0"),//正在使用
		NEW("1"),//新功能
		NEXT("2"),//子菜单
		ABOUT("3");//即将上线
		
		private String value;
		MENUSTATUS(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	public enum AUTHFLAG implements CommEnum{
		SEARCH("SEARCH"),//查询
		ADD("ADD"),//增加
		UPDATE("UPDATE"),//修改
		DELETE("DELETE"),//删除
		AUTH("AUTH");//授权
		private String value;
		
		AUTHFLAG(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	public enum STAFFSTATUS implements CommEnum{
		WORKING("WORKING"),//在职
		QUITED("QUITED"),//离职
		TEMP("TEMP");//临时
		private String value;
		
		STAFFSTATUS(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
	public enum ATTENDANCEEXCEPTION implements CommEnum{
		NOCARD("NOCARD"),//未打卡
		NOORDER("NOORDER"),//未排班
		LOSECARD("LOSECARD"),//缺卡
		LATER("LATER"),//迟到
		EARLYQUIT("EARLYQUIT");//早退
		private String value;
		
		ATTENDANCEEXCEPTION(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}
}
