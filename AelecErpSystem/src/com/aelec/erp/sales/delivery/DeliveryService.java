package com.aelec.erp.sales.delivery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aelec.erp.common.DBcon;
import com.aelec.erp.sales.product.ProductService;
import com.aelec.erp.sales.product.ProductVO;

public class DeliveryService {
	private final DBcon db;
	private final Scanner sc;
	PreparedStatement pstmt;

	public DeliveryService(Scanner sc, DBcon db) {
		this.sc = sc;
		this.db = db;
	}

	public void deliveryInfo() throws ClassNotFoundException, SQLException {
		DeliveryVO d = new DeliveryVO();
		ProductVO p = new ProductVO();
		ProductService ps = new ProductService(sc, db);
		int choice;
		while (true) {
			System.out.print("상품코드를 입력해주세요>> ");
			d.setP_code(sc.nextLine());

			System.out.print("출고 수량을 입력해주세요>> ");
			d.setS_sto(sc.nextInt());
			// 기존에 재고 수량을 불러와서 입력한 수와 합하여 다시 쿼리를 쏴야함
			String query = "SELECT s_qty FROM stock WHERE p_code = ?";
			pstmt = db.connect().prepareStatement(query);
			pstmt.setString(1, d.getP_code());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int minus = rs.getInt("s_qty");
				minus = minus- d.getS_sto();
				d.setS_sto(minus);

				query = "UPDATE stock SET s_qty = ? WHERE p_code = ?";// "+"'"+s.getP_code()+"'";
				pstmt = db.connect().prepareStatement(query);
				pstmt.setInt(1, d.getS_sto());
				pstmt.setString(2, d.getP_code());

				int result = pstmt.executeUpdate();
				if (result == 1) {
					System.out.println("출고 작업이 정상 처리되었습니다!");
					System.out.println("출고 작업 내역서▼");
					System.out.println(d.toString());
					break;

				}

			}

		}

	}

}
