package com.anghel.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParentRepositoryTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ParentRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        initData();
    }

    @Test
    public void put_newChild_parentUpdated() throws Exception {
        Child bebe = new Child();
        bebe.setName("Bebe");

        Parent parent = repository.findAll().get(0);
        parent.getChildren().add(bebe); // add a new child

        String json = objectMapper.writeValueAsString(parent);

        /*
         * Fails with:
         * 
         * Multiple representations of the same entity
         * [com.anghel.example.Child#1] are being merged. Detached:
         * [com.anghel.example.Child@7582a16b]; Managed:
         * [com.anghel.example.Child@5ea0a7a9]
         */
        mockMvc.perform(put("http://localhost:8080/parents/" + parent.getId())
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.parents").exists());
    }

    private void initData() {
        Parent parent = new Parent();
        parent.setName("Daddy");

        Child boy = new Child();
        boy.setName("Boy");

        Child girl = new Child();
        girl.setName("Girl");

        parent.setChildren(Sets.newSet(boy, girl));

        repository.saveAndFlush(parent);
    }
}
