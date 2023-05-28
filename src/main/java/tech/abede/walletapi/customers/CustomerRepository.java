package tech.abede.walletapi.customers;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{

    @Query("select c from Customer c where c.NIK = ?1")
    Customer findByNik(String nik);

}
