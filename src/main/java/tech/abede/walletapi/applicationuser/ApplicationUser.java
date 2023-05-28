package tech.abede.walletapi.applicationuser;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.abede.walletapi.customers.Customer;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {

    @Id
    @Column(name = "customer_id")
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
 
    private String email;

    private String username;

    private String password;

}
