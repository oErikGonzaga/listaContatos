package com.gonzaga.contatos.repositories;

import com.gonzaga.contatos.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositories extends JpaRepository<Endereco, Long> {
}
