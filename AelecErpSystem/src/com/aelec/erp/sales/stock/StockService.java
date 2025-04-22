package com.aelec.erp.sales.stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aelec.erp.common.DBcon;

public class StockService {
	private final Scanner sc;
	private final DBcon db;
	PreparedStatement pstmt;

	public StockService(Scanner sc, DBcon db) {
		this.sc = sc;
		this.db = db;
	}

	/* 상품재고조회 */
	public void checkAllStock() throws ClassNotFoundException, SQLException {
		System.out.println("상품재고 조회를 시작합니다...");

		List<StockVO> list = new ArrayList<>();
		String query = "SELECT * FROM stock";
		pstmt = db.connect().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			StockVO s = new StockVO();

			s.setP_code(rs.getString("p_code"));
			s.setS_qty(rs.getInt("s_qty"));

			list.add(s);
		}

		System.out.println("<< 상품재고 >>");
		for (StockVO stock : list) {
			System.out.println(stock);
		}
	}

	/* 특정상품재고조회 */
	public void checkStock() throws ClassNotFoundException, SQLException {
		System.out.println("상품재고 조회를 시작합니다...");
		System.out.println("찾는 상품의 상품코드를 입력하세요 >> ");
		String code = sc.next();
		code = "%" + code + "%";

		String query = "SELECT * FROM stock WHERE p_code LIKE ?";
		pstmt = db.connect().prepareStatement(query);
		pstmt.setString(1, code);
		ResultSet rs = pstmt.executeQuery();

		System.out.println("<< 상품재고 >>");
		int flag = 0;
		while (rs.next()) {
			StockVO s = new StockVO();

			s.setP_code(rs.getString("p_code"));
			s.setS_qty(rs.getInt("s_qty"));
			System.out.println(s);
			flag = 1;
		}
		if (flag == 0) {
			System.out.println("해당 상품코드가 존재하지 않습니다...");
		}
	}

}
