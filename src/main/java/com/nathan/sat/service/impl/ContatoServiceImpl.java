package com.nathan.sat.service.impl;

import com.nathan.sat.model.Contato;
import com.nathan.sat.repository.ContatoRepository;
import com.nathan.sat.service.ContatoService;
import com.nathan.sat.service.dto.ContatoDTO;
import com.nathan.sat.service.mapper.ContatoMapper;
import com.nathan.sat.util.ConstantsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;

    private final ContatoMapper contatoMapper;

    public Page<ContatoDTO> search(String searchTerm, Pageable page){
        return contatoRepository.search('%'+searchTerm.trim()+'%', page);
    }

    private Contato findByIdOrThrow(Integer id) {
        return contatoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato não existe"));
    }

    @Override
    public ContatoDTO findById(Integer id) {
        return contatoMapper.toDto(findByIdOrThrow(id));
    }

    public ContatoDTO create(ContatoDTO dto) {
        if (contatoRepository.existsContatoByCelular(dto.getCelular())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Celular ja cadastrado");
        }
        dto.setAtivo(ConstantsUtil.SINAL_SIM);
        dto.setFavorito(ConstantsUtil.SINAL_NAO);
        dto.setDataHoraCadastro(LocalDateTime.now());
        return contatoMapper.toDto(contatoRepository.save(contatoMapper.toEntity(dto)));
    }

    public ContatoDTO edit(ContatoDTO dto) {

        if (dto.getId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contato sem ID");

        findByIdOrThrow(dto.getId());

        if (contatoRepository.existsContatoByCelularAndIdIsNot(dto.getCelular(), dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Celular ja cadastrado");
        }

        return contatoMapper.toDto(contatoRepository.save(contatoMapper.toEntity(dto)));
    }

    @Override
    public ContatoDTO favorite(Integer id) {

        List<Contato> favoritos = contatoRepository.findContatoesByFavorito(ConstantsUtil.SINAL_SIM);

        favoritos.forEach(contato -> {
            contato.setFavorito(ConstantsUtil.SINAL_NAO);
            contatoRepository.save(contato);
        });

        Contato contato = findByIdOrThrow(id);

        contato.setFavorito(ConstantsUtil.SINAL_SIM);

        return contatoMapper.toDto(contatoRepository.save(contato));
    }
}
