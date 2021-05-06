package com.company.controller.document;


import com.company.controllers.document.DocumentController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/db-user/create-user-before.sql", "/db-document/document-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/db-document/document-after.sql", "/db-user/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithUserDetails("user")
    public void list() throws Exception{
        this.mockMvc.perform(get("/document"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(6));

    }

    @Test
    public void listNo() throws Exception{
        this.mockMvc.perform(get("/document"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithUserDetails("recorted")
    public void createDocument() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/document")
                .file("file", "123.pdf".getBytes())
                .param("number", "3/3/3")
                .param("coment", "document")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated());

    }

    @Test
    @WithUserDetails("admin")
    public void createDocumentAdmin() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/document")
                .file("file", "123.pdf".getBytes())
                .param("number", "3/3/3")
                .param("coment", "document")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated());

    }

    @Test
    @WithUserDetails("user")
    public void createDocumentUser() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/document")
                .file("file", "123.pdf".getBytes())
                .param("number", "3/3/3")
                .param("coment", "document")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isForbidden());

    }






}
