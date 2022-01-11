package javaBasic;

public class Topic_01_Data_Type {
	static int studenNumber;
	static boolean status;
	
	String studenName = "TienDan";
	
	public static void main(String[] args) {
		System.out.println(studenNumber);
		System.out.println(status);
	}
	// Getter
	public String getStudenName() {
		return this.studenName;
	}
	
	//Setter 
	public void setStudenName(String stdName) {
		this.studenName = stdName;
		if (3<5) {
			String homePageTitleString = "";
			System.out.println(homePageTitleString);
		}
	}
	
}
