package tech.abede.walletapi.applicationuser;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {
    
    @Query("select a from ApplicationUser a where a.username = ?1 or a.email = ?1")
    Optional<ApplicationUser> findByUsernameOrEmail(String usernameOrEmail);
}
