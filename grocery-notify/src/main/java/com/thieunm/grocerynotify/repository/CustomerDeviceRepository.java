package com.thieunm.grocerynotify.repository;

import com.thieunm.grocerynotify.entity.CustomerDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDeviceRepository extends JpaRepository<CustomerDevice, Integer> {

    List<CustomerDevice> findByCustomerId(String customerId);
}
