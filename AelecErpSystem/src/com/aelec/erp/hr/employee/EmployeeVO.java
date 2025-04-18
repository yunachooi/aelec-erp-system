package com.aelec.erp.hr.employee;

import lombok.Data;

@Data
public class EmployeeVO {
	private int e_no;
	private String e_pw;
	private String e_name;
	private String e_pos;
	private String e_dept;
	private String e_date;
}
