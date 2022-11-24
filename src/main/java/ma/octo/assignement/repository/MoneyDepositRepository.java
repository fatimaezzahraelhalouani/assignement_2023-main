package ma.octo.assignement.repository;

import ma.octo.assignement.domain.MoneyDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyDepositRepository extends JpaRepository<MoneyDeposit,Long>{
}