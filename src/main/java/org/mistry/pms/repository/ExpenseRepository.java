package org.mistry.pms.repository;

import org.mistry.pms.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{

}
