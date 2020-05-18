package org.mistry.pms.service;

import org.mistry.pms.entity.Expense;
import org.mistry.pms.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	public Iterable<Expense> getAllExpenses() {
		
		return expenseRepository.findAll();
	}
	
	
}
