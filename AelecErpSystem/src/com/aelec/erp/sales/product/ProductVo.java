package com.aelec.erp.sales.product;

import lombok.Data;

@Data
public class ProductVo {
	private String p_code;
	private String p_name;
	private int p_cost;
	private String p_group;
}
