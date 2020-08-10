package com.TestCases;

import com.Keyword.Keywords;


public class OrangeHRMAddEmployee extends Keywords {
	
	String userName=new String("obj_UserName");
	String password=new String("obj_Password");
	String logInBtn=new String("obj_LoginBtn");
	String clickPIM=new String("obj_clickPIM");
	String clickAddBtn=new String("obj_clickAddBtn");
	String firstName=new String("obj_firstName");
	String middleName=new String("obj_MiddleName");
	String lastName=new String("obj_lastName");
	
	String checkBox=new String("obj_checkBox");
	String userfild=new String("obj_userfild");
	String passwordfild=new String("obj_passwordfild");
	String confirmPass=new String("obj_confirmPass");
	String saveBtn=new String("obj_saveBtn");
	
	String userNameTxt=readPropertiesData.getObjectRepoData("txt_UserName");
	String passwordTxt=readPropertiesData.getObjectRepoData("txt_Password");
	
	String firstNameTxt=readPropertiesData.getObjectRepoData("txt_firstName");
	String middleTxt=readPropertiesData.getObjectRepoData("txt_MiddleName");	
	String lasrNameTxt=readPropertiesData.getObjectRepoData("txt_lastName");
	
	String userfildTxt=readPropertiesData.getObjectRepoData("txt_userfild");
	String passwordfildTxt=readPropertiesData.getObjectRepoData("txt_passwordfild");
	String confirmPassTxt=readPropertiesData.getObjectRepoData("txt_confirmPass");
	
	
	
	public void openChromeBrowser(){
		startInstance();
		startReportTest("Open Chrome browser");
		
	}

	public void AddEmployee(){
		
		startReportTest("Add Employee Details");
		getUrl();
		sendkeys(userName,userNameTxt);
		sendkeys(password,passwordTxt);
		clickonWebElement(logInBtn);
		actionclassClickOnElement(clickPIM);
		clickonWebElement(clickAddBtn);
		sendkeys(firstName,firstNameTxt);
		sendkeys(middleName,middleTxt);
		sendkeys(lastName,lasrNameTxt);
		clickonWebElement(checkBox);
		sendkeys(userfild,userfildTxt);
		sendkeys(passwordfild,passwordfildTxt);
		sendkeys(confirmPass,confirmPassTxt);
		clickonWebElement(saveBtn);
	}
	
	public void closeBrowser(){
		closeInstance();
	}
	public static void main(String[] args) {
		OrangeHRMAddEmployee  obj=new OrangeHRMAddEmployee();
		obj.openChromeBrowser();
	    obj.AddEmployee();
	    obj.closeBrowser();
	}

}
