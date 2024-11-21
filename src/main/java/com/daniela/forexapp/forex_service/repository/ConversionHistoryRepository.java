package com.daniela.forexapp.forex_service.repository;

import com.daniela.forexapp.forex_service.model.ConversionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

 @Query(("SELECT h FROM ConversionHistory h " +
   "WHERE (:fromCurrency IS NULL OR h.fromCurrency = :fromCurrency) " +
   "AND (:toCurrency IS NULL OR h.toCurrency = :toCurrency) " +
   "AND (:startDate IS NULL OR h.timestamp >= :startDate) " +
   "AND (:endDate IS NULL OR h.timestamp <= :endDate)"))

 Page<ConversionHistory> findFilteredHistory(
   @Param("fromCurrency") String fromCurrency,
   @Param("toCurrency") String toCurrency,
   @Param("startDate") LocalDateTime startDate,
   @Param("endDate") LocalDateTime endDate,
   Pageable pageable);

}
