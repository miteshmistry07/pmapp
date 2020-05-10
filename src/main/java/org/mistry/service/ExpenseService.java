package org.mistry.service;

import org.mistry.entity.Expense;

public interface ExpenseService {
	
	public Iterable<Expense> getAllExpenses();
}
