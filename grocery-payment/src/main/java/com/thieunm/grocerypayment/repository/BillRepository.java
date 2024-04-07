package com.thieunm.grocerypayment.repository;

import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.enums.BillStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByCustomerId(String customerId, Sort sort);

    List<Bill> findByCustomerIdAndStatusIn(String customerId, List<BillStatus> billStatusList, Sort sort);

    List<Bill> findByStatusIn(List<BillStatus> billStatusList, Pageable pageable);
}
