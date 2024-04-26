package com.thieunm.grocerypayment.repository;

import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.enums.BillStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {

    List<Bill> findByCustomerId(String customerId, Pageable pageable);

    List<Bill> findByCustomerIdAndStatusIn(String customerId, List<BillStatus> billStatusList, Pageable pageable);

    List<Bill> findByStatusIn(List<BillStatus> billStatusList, Pageable pageable);

    @Query(
            value = "SELECT bi.product_id " +
                    "FROM bill b JOIN bill_item bi ON b.id = bi.bill_id " +
                    "WHERE b.created_date >= DATE_SUB(CURDATE(), INTERVAL ?1 DAY) " +
                    "GROUP BY bi.product_id " +
                    "ORDER BY COUNT(bi.product_id) DESC " +
                    "LIMIT ?2",
            nativeQuery = true
    )
    List<Integer> getBestSellingProductIdList(int recentDays, int size);
}
