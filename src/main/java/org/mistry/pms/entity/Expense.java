package org.mistry.pms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="expenseId")
	private int expenseId;
	
	@ManyToOne
	@JoinColumn(name="premiseId")
	private Premise premise;
	
	@Column(name="expenseType")
	private String expenseType;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="description")
	private String description;
	
	@Column(name="payee")
	private String payee;
	
	@Column(name="paymentMethod")
	private String paymentMethod;

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public Premise getPremise() {
		return premise;
	}

	public void setPremise(Premise premise) {
		this.premise = premise;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
