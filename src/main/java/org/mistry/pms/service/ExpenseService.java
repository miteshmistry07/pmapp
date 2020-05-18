package org.mistry.pms.service;

import org.mistry.pms.entity.Expense;

public interface ExpenseService {
	
	public Iterable<Expense> getAllExpenses();
}
