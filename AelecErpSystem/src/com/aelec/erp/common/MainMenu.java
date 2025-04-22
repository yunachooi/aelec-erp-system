package com.aelec.erp.common;

import java.sql.SQLException;
import java.util.Scanner;

import com.aelec.erp.hr.employee.EmployeeService;
import com.aelec.erp.sales.customer.CustomerService;
import com.aelec.erp.sales.product.ProductService;
import com.aelec.erp.sales.stock.StockService;

public class MainMenu {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		DBcon jdbc = new DBcon();
		jdbc.connect();
		
		CustomerService cs = new CustomerService(sc, jdbc);
		ProductService ps = new ProductService(sc, jdbc);
		StockService ss = new StockService(sc, jdbc);
		EmployeeService es = new EmployeeService(sc, jdbc);
		
		/* 콘솔에서 메뉴를 보여주고 선택 처리 */
		boolean flag = true;
		while(flag) {
			System.out.println("""
				자재관리시스템 ERP 메뉴
				1. 거래처 정보 확인
				2. 거래처 정보 변경
				3. 상품 정보 확인
				4. 상품 정보 수정
				5. 상품 재고 확인
				6. 특정 상품 재고 확인
				7. 상품 입고 관리
				8. 상품 출고 관리
				9. 직원 정보 확인
				10. 직원 정보 수정
				11. 종료
				""");
			System.out.print("원하는 메뉴를 선택하세요 >> ");
			int menu = sc.nextInt();sc.nextLine();
			
			switch(menu) {
			case 1 -> cs.customerInfo();
			case 2 -> cs.customerUpdateInfo();
			case 3 -> ps.productInfo();
			case 4 -> ps.updateProductInfo();
			case 5 -> ss.getStock();
			case 6 -> ss.getProductStock();
			case 9 -> es.employeeInfo();
			case 10 -> es.updateEmployeeInfo();
			case 11 -> {
				System.out.println("자재관리시스템을 종료합니다.");
				flag = false;
			}
			default -> {
				System.out.println("잘못된 번호를 입력하였습니다.");
				flag = false;
			}
			}
			
		}
	}

}
