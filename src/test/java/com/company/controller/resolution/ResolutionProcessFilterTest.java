package com.company.controller.resolution;


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
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {
        "/db-user/create-user-before.sql",
        "/db-document/document-before.sql",
        "/db-resolution/resolution-create-before.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {
        "/db-resolution/resolution-create-after.sql",
        "/db-document/document-after.sql",
        "/db-user/create-user-after.sql"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ResolutionProcessFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void list() throws Exception{
        this.mockMvc.perform(get("/resolution/process"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(6));
    }

    @Test
    public void filterNoData() throws Exception{
        this.mockMvc.perform(get("/resolution/process")
                .param("n", "1")
                .param("a", "Tab")
                .param("c", "Conf")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(5));
    }

    @Test
    public void filterOnlyOneDataStart() throws Exception{
        this.mockMvc.perform(get("/resolution/process")
                .param("ds", "2021-03-05")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(1));
    }

    @Test
    @WithUserDetails("user")
    public void filterOnlyOneDataFinish() throws Exception{
        this.mockMvc.perform(get("/resolution/process")
                .param("df", "2021-03-05")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(1));
    }

    @Test
    public void filterOnlyData() throws Exception{
        this.mockMvc.perform(get("/resolution/process")
                .param("ds", "2021-02-02")
                .param("df", "2021-04-17")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(3));
    }


    @Test
    @WithUserDetails("user")
    public void filter() throws Exception{
        this.mockMvc.perform(get("/resolution/process")
                .param("ds", "2021-02-02")
                .param("df", "2021-04-16")
                .param("n", "1")
                .param("a", "Tab")
                .param("c", "Conf")
        )
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//tbody[@id='tablebody']/tr").nodeCount(2));
    }


}
