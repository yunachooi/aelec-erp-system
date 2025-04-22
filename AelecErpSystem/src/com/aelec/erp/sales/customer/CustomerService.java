package com.aelec.erp.sales.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.aelec.erp.common.DBcon;

public class CustomerService {
	private final Scanner sc;
	private final com.aelec.erp.common.DBcon db;
	PreparedStatement pstmt;

	public CustomerService(Scanner sc, DBcon db) {
		this.sc = sc;
		this.db = db;
	}

	public void customerInfo() throws ClassNotFoundException, SQLException {
		CustomerVO c = new CustomerVO();
		System.out.print("거래처 코드를 입력해주세요>> ");
		c.setC_code(sc.nextLine());
		System.out.print("거래처 명을 입력해주세요>> ");
		c.setC_name(sc.nextLine());
		System.out.print("거래처 경영자 이름을 입력해주세요>> ");
		c.setC_ceo(sc.nextLine());
		System.out.print("거래처 주소를 입력해주세요>> ");
		c.setC_addre(sc.nextLine());
		System.out.print("거래처 연락처를 입력해주세요>> ");
		c.setC_phone(sc.nextLine());
		System.out.println("입력된 정보 " + c.toString());

		db.connect();
		String query = "INSERT INTO customer VALUES(?,?,?,?,?)";
		pstmt = db.connect().prepareStatement(query);
		pstmt.setString(1, c.getC_code());
		pstmt.setString(2, c.getC_name());
		pstmt.setString(3, c.getC_ceo());
		pstmt.setString(4, c.getC_addre());
		pstmt.setString(5, c.getC_phone());

		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println("거래처 정보 입력이 정상적으로 처리되었습니다!");
			System.out.println("입력된 내용 -> " + c.toString());
		}

	}

	public void checkAllCustomer() throws ClassNotFoundException, SQLException {
		CustomerVO c = new CustomerVO();
		System.out.println("거래처 정보를 조회합니다...");

		String query = "SELECT * FROM customer";
		pstmt = db.connect().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		ArrayList<CustomerVO> list = new ArrayList<>();
		while (rs.next()) {
			c.setC_code(rs.getString("c_code"));
			c.setC_name(rs.getString("c_name"));
			c.setC_ceo(rs.getString("c_ceo"));
			c.setC_addre(rs.getString("c_addr"));
			c.setC_phone(rs.getString("c_phone"));
			
			list.add(c);

		}
		System.out.println("거래처 정보▼");
		for(CustomerVO cvo : list) {
			System.out.println(cvo);
		}
	}

	public void customerUpdateInfo() throws ClassNotFoundException, SQLException {
		CustomerVO c = new CustomerVO();
		int ivalue = 0;
		String svalue = null;

		System.out.println("거래처 정보 수정을 시작합니다...");
		System.out.print("변경을 원하는 거래처 코드를 입력하세요 >> ");
		c.setC_code(sc.nextLine());
		String code = c.getC_code();

		System.out.print("""
				1.거래처 코드 2.거래처명 3.경영자 이름 4.거래처 주소 5.거래처 연락처
				수정할 항목을 선택해주세요 >>
				""");
		int choice = sc.nextInt();

		String items = switch (choice) {
		case 1 -> "c_code";
		case 2 -> "c_name";
		case 3 -> "c_ceo";
		case 4 -> "c_addr";
		case 5 -> "c_phone";
		default -> null;
		};

		System.out.print("수정할 내용을 입력하세요 >> ");
		svalue = sc.next();

		String query = "UPDATE customer SET " + items + " = ? WHERE c_code = ?";
		pstmt = db.connect().prepareStatement(query);

		pstmt.setString(1, svalue);

		pstmt.setString(2, code);

		int result = pstmt.executeUpdate();
		if (result == 1) {
			System.out.println("거래처 정보가 갱신되었습니다...");
		}
	}

}
