package com.thieunm.grocerypayment.config;

import com.thieunm.grocerypayment.entity.Bill;
import com.thieunm.grocerypayment.enums.BillStatus;
import com.thieunm.grocerypayment.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CronJobConfig {

    private final BillRepository billRepository;

    /**
     * Cancel expired bill at 23:00 every day
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void performCancelBillTaskAtMidnight() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<Bill> billList = billRepository.findByStatusNotAndPickUpTimeBefore(BillStatus.COMPLETED.name(), currentTime);
        List<Bill> cancelledBillList = billList.stream()
                .peek(bill -> bill.setStatus(BillStatus.CANCELLED))
                .toList();
        cancelledBillList = billRepository.saveAll(cancelledBillList);
        log.info("Cancelled {} expired bills", cancelledBillList.size());
    }

}
