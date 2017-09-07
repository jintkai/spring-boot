package com.jon.project;

import com.jon.project.action.ProjectAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ContextConfiguration(classes = com.jon.Config.class)
public class ProjectActionTest extends AbstractTestNGSpringContextTests{

    @Autowired
    ProjectAction projectAction;

    private MockMvc mockMvc;

    @Test
    public void getProjectTest() throws Exception {
        projectAction.getProject("4041");
        projectAction.getProject("40429");
        //mockMvc.perform(get(""));
    }
}
