package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by vnamboo on 15/1/2017.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserName(String username);
}
