package com.jcpuerto.entities;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ORDERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne()
	private User user;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);

	@Transient
	String cartTextDisplay;

	private double total;
	private int totalQty;

	public Order() {
		// Hibernate required
	}

	public Order(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public void addToOrderItems(OrderItem orderItem) {
		totalQty++;
		total += orderItem.getProduct().getPrice();
		orderItem.setOrder(this);
		this.orderItems.add(orderItem);
	}

	public void removeFromOrderItems(OrderItem orderItem) {
		totalQty--;
		String ss = "";
		total -= orderItem.getProduct().getPrice();
		orderItem.setOrder(this);
		this.orderItems.remove(orderItem);
	}

	public void updateOrder(double productPrice, int qty) {
		totalQty += qty;
		total += productPrice;
	}

	public String getCartTextDisplay() {
		return String.format("<span id=\"cart\">items %d | total: %s</span>", totalQty,
				DecimalFormat.getCurrencyInstance().format(total));
	}
}
