package com.ecommerce.model;

public class StockDto {
	private String name;
	private Integer availablequantity;
	private Integer cost;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAvailablequantity() {
		return availablequantity;
	}
	public void setAvailablequantity(Integer availablequantity) {
		this.availablequantity = availablequantity;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public StockDto(String name, Integer availablequantity, Integer cost) {
		super();
		this.name = name;
		this.availablequantity = availablequantity;
		this.cost = cost;
	}
	public StockDto() {
		super();
	}
	@Override
	public String toString() {
		return "StockDto [name=" + name + ", availablequantity=" + availablequantity + ", cost=" + cost + "]";
	}
	
	
	
}
