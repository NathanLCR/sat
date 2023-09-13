package com.nathan.sat.service;

import com.nathan.sat.model.Contato;
import com.nathan.sat.service.dto.ContatoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContatoService {

    Page<ContatoDTO> search(String searchTerm, Pageable page);

    ContatoDTO findById(Integer id);

    ContatoDTO create(ContatoDTO contato);

    ContatoDTO edit(ContatoDTO contato);

    ContatoDTO favorite(Integer id);
}
