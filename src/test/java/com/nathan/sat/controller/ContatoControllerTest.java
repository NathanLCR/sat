package com.nathan.sat.controller;

import com.nathan.sat.SatApplication;
import com.nathan.sat.model.Contato;
import com.nathan.sat.repository.ContatoRepository;
import com.nathan.sat.service.dto.ContatoDTO;
import com.nathan.sat.util.ConstantsUtil;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = SatApplication.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ContatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContatoRepository contatoRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Contato buildAndPersist() {
        Contato contato = new Contato();
        contato.setFavorito("N");
        contato.setAtivo("S");
        contato.setEmail("teste@teste.com");
        contato.setCelular("83968563211");
        contato.setTelefone("8396865989");
        contato.setNome("teste");

        return contatoRepository.save(contato);
    }

    @Test
    public void should_create_contato() throws Exception {
        ContatoDTO contato = new ContatoDTO();
        contato.setFavorito("N");
        contato.setAtivo("S");
        contato.setEmail("teste@teste.com");
        contato.setCelular("83968563211");
        contato.setTelefone("8396865989");
        contato.setNome("teste");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/contato").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(contato)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.celular").value(contato.getCelular()));
    }

    @Test
    public void should_not_create_contato_with_repeated_celular() throws Exception {
        buildAndPersist();

        ContatoDTO contatoWithRepeatedCelular = new ContatoDTO();
        contatoWithRepeatedCelular.setFavorito("N");
        contatoWithRepeatedCelular.setAtivo("S");
        contatoWithRepeatedCelular.setEmail("teste2@teste.com");
        contatoWithRepeatedCelular.setCelular("83968563211");
        contatoWithRepeatedCelular.setTelefone("8398763568");
        contatoWithRepeatedCelular.setNome("teste2");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/contato").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(contatoWithRepeatedCelular)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void should_update_contato() throws Exception {
        Contato contato = buildAndPersist();

        contato.setNome("nome");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/contato").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(contato)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(contato.getNome()));
    }

    @Test
    public void should_listar() throws Exception {
        Contato contato = buildAndPersist();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contato")
                        .param("size", "10")
                        .param("page", "0")
                        .param("sort", "1")
                        .param("sortParam", "id")
                        .param("searchTerm", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(contato.getId()));

    }

    @Test
    public void should_get_by_id() throws Exception {
        Contato contato = buildAndPersist();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/contato/"+contato.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(contato.getId()));

    }

    @Test
    public void should_favoritar() throws Exception {
        Contato contato = buildAndPersist();
        mockMvc.perform(MockMvcRequestBuilders.put("/api/contato/favoritar/"+contato.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.favorito").value(ConstantsUtil.SINAL_SIM));
    }
}
