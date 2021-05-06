package com.company.controller.document;


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

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/db-user/create-user-before.sql", "/db-document/document-before.sql", "/db-resolution/resolution-create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/db-resolution/resolution-create-after.sql", "/db-document/document-after.sql", "/db-user/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DocumentFilterWriteTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void filterNoDataWrite() throws Exception{
        this.mockMvc.perform(get("/document")
                .param("n", "1")
                .param("a", "Rec")
                .param("s", "wr")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(5));
    }

    @Test
    public void filterOnlyOneDataStartWrite() throws Exception{
        this.mockMvc.perform(get("/document")
                .param("ds", "2021-03-05")
                .param("s", "wr")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(1));
    }

    @Test
    public void filterOnlyOneDataFinishWrite() throws Exception{
        this.mockMvc.perform(get("/document")
                .param("df", "2021-03-05")
                .param("s", "wr")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(1));
    }

    @Test
    public void filterOnlyDataWrite() throws Exception{
        this.mockMvc.perform(get("/document")
                .param("ds", "2021-02-02")
                .param("df", "2021-04-17")
                .param("s", "wr")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(3));
    }


    @Test
    public void filterWrite() throws Exception{
        this.mockMvc.perform(get("/document")
                .param("ds", "2021-02-02")
                .param("df", "2021-04-16")
                .param("n", "1")
                .param("a", "Rec")
                .param("s", "wr")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(2));
    }


}
