package com.nathan.sat.repository;

import com.nathan.sat.model.Contato;
import com.nathan.sat.service.dto.ContatoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    boolean existsContatoByCelular(String celular);

    List<Contato> findContatoesByFavorito(String favorito);

    @Query("select new com.nathan.sat.service.dto.ContatoDTO(contato.id, contato.nome, contato.email, contato.celular, contato.telefone, contato.favorito, contato.ativo, contato.dataHoraCadastro) " +
            "from Contato contato " +
            "where LOWER(contato.nome) like LOWER(:searchTerm) or " +
            "LOWER(contato.celular) like LOWER(:searchTerm) or " +
            "LOWER(contato.telefone) like LOWER(:searchTerm) or " +
            "LOWER(contato.email) like LOWER(:searchTerm)  ")
    Page<ContatoDTO> search(@Param("searchTerm") String searchTerm, Pageable page);

    boolean existsContatoByCelularAndIdIsNot(String celular, Integer id);

}
