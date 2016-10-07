package org.hotel.common;

public interface AuthEnum {
	
	public enum USERENUM implements CommEnum{
		CUSTOMER("customer"),
		ADMIN("admin"),
		SERVICEUSER("serviceUser"),
		ORGUSER("orgUser"),
		SUPERADMIN("superAdmin"),
		CURRENT_USER("currentUser");
		
		private String value;
		USERENUM(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	}

}
