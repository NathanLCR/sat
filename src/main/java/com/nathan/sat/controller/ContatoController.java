package com.nathan.sat.controller;

import com.nathan.sat.service.ContatoService;
import com.nathan.sat.service.dto.ContatoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/contato")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @GetMapping("")
    private ResponseEntity<Page<ContatoDTO>> search(@RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                     @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                     @RequestParam(value = "sort", defaultValue = "1", required = false) int sortOrder,
                                                     @RequestParam(value = "sortParam", defaultValue = "id", required = false) String sortParam,
                                                     @RequestParam(value = "searchTerm", defaultValue = "", required = false) String searchTerm) {
        try {
            Sort sort = Sort.by(sortParam);
            if (sortOrder == 1) sort = sort.ascending();
            if (sortOrder == -1) sort = sort.descending();
            return ResponseEntity.ok(contatoService.search(searchTerm, PageRequest.of(page, size, sort)));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Estamos com algum problema. Favor tentar mais tarde.");
        }

    }

    @GetMapping("/{id}")
    private ResponseEntity<ContatoDTO> findById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(contatoService.findById(id));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Estamos com algum problema. Favor tentar mais tarde.");
        }
    }

    @PostMapping
    private ResponseEntity<ContatoDTO> save(@RequestBody @Valid ContatoDTO contatoDTO) {
        try {
            return new ResponseEntity<>(contatoService.create(contatoDTO), HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Estamos com algum problema. Favor tentar mais tarde.");
        }
    }

    @PutMapping
    private ResponseEntity<ContatoDTO> edit(@RequestBody @Valid ContatoDTO contatoDTO) {
        try {
            return ResponseEntity.ok(contatoService.edit(contatoDTO));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Estamos com algum problema. Favor tentar mais tarde.");
        }
    }

    @PutMapping("/favoritar/{id}")
    private ResponseEntity<ContatoDTO> favorite(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(contatoService.favorite(id));
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Estamos com algum problema. Favor tentar mais tarde.");
        }
    }
}
