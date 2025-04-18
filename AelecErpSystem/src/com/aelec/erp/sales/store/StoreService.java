package com.aelec.erp.sales.store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.aelec.erp.common.DBcon;
import com.aelec.erp.sales.product.ProductService;
import com.aelec.erp.sales.product.ProductVO;



public class StoreService {
	private final Scanner sc;
	private final DBcon db;
	PreparedStatement pstmt;

	StoreService(Scanner sc, DBcon db) {
		this.sc = sc;
		this.db = db;
	}

	// 중복확인 함수
	private int checkCode(String s) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM product WHERE p_code = ?";
		pstmt = db.connect().prepareStatement(query);
		pstmt.setString(1, s);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return 1;
		} else {
			return 0;
		}

	}


	public void storeInfo() throws ClassNotFoundException, SQLException {
		StoreVO s = new StoreVO();
		ProductVO p = new ProductVO();
		ProductService ps = new ProductService(sc,db);
		int choice;
		while (true) {
			System.out.print("상품코드를 입력해주세요>> ");
			s.setP_code(sc.nextLine());

			if (checkCode(s.getP_code()) != 1) {
				System.out.println("존재하지 않는 상품번호 입니다!");
				System.out.println("새로운 상품을 추가하시겠습니까? \n 1.예 2.아니오");
				choice = sc.nextInt();
				if (choice == 1) {
					System.out.println("상품 추가를 시작합니다...");
					ps.productInfo();
					break;
		
				} else if (choice == 2) {
					System.out.println("다시 입력해주세요...");
					continue;
				}

			}else {
				System.out.print("입고 수량을 입력해주세요>> ");
				s.setS_sto(sc.nextInt());
				// 기존에 재고 수량을 불러와서 입력한 수와 합하여 다시 쿼리를 쏴야함
				String query = "SELECT s_qty FROM stock WHERE p_code = ?";
				pstmt = db.connect().prepareStatement(query);
				pstmt.setString(1, s.getP_code());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					int sum = rs.getInt("s_qty");
					sum = sum+s.getS_sto();
					s.setS_sto(sum);
				}
				
				
				

				query = "UPDATE stock SET s_qty = ? WHERE p_code = ?";// "+"'"+s.getP_code()+"'";
				pstmt = db.connect().prepareStatement(query);
				pstmt.setInt(1, s.getS_sto());
				pstmt.setString(2, s.getP_code());
				
				

				int result = pstmt.executeUpdate();
				if (result == 1) {
					System.out.println("입고 작업이 정상 처리되었습니다!");
					System.out.println("입고 작업 내역서▼");
					System.out.println(s.toString());
					break;
				}

			}
			
		}

	}

}
