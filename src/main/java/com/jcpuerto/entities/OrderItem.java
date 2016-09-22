package com.jcpuerto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ORDERITEMS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private int qty;

	@ManyToOne()
	private Order order;

	@ManyToOne()
	private Product product;

	public OrderItem() {
		// Hibernate required
	}

	public OrderItem(Product product) {
		this.product = product;
		qty++;
	}

	public long getId() {
		return id;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void addQty(Product product, int qty) {
		this.product = product;
		this.qty += qty;
	}

	@Override
	public String toString() {
		return (String.format("[order: %s | qty: %s]", order, qty));
	}
}
