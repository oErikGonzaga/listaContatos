package com.gonzaga.contatos.repositories;

import com.gonzaga.contatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Esta Interface extende a Interface JpaRepository.
// Ela responsavel por montar nossas querys no Banco.
// Em JPA Repository devemos apontar a nossa classe Model e o TIPO de nossa chave ID.
// Devemos anota-la como um Bean do Spring, para que ela instancie esta classe com @Repository

@Repository
public interface ContatosRepository extends JpaRepository<Contato, String> {

    Contato findFirstByDocumento(Long documento);

}
