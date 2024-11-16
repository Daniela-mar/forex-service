package com.daniela.forexapp.forex_service.repository;

import com.daniela.forexapp.forex_service.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionHistoryRepository  extends JpaRepository<TransactionHistory, Long> {
}
