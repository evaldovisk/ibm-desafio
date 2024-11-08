package io.github.evaldo.ibm.repository;

import io.github.evaldo.ibm.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c JOIN c.conta co WHERE c.email = :email AND co.numeroConta = :numeroConta")
    Optional<Cliente> findByEmailAndNumeroConta(@Param("email") String email, @Param("numeroConta") String numeroConta);
}
