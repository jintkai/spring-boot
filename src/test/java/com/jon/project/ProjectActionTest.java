package com.jon.project;

import com.jon.httpclient.HttpClientService;
import com.jon.project.action.ProjectAction;
import org.apache.commons.httpclient.URIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;



@ContextConfiguration(classes = com.jon.Config.class)
public class ProjectActionTest extends AbstractTestNGSpringContextTests{

    @Autowired
    ProjectAction projectAction;

    private MockMvc mockMvc;

    //@Test
    public void getProjectTest() throws Exception {
        System.out.println(projectAction.getProject("4041"));
        projectAction.getProject("40429");
        //mockMvc.perform(get(""));
    }

    @Autowired
    HttpClientService httpClientService;

    @Test
    public void testHttp() throws ClassNotFoundException, InstantiationException, URIException, IllegalAccessException {
        //httpClientService.sentRequest(0,"https://www.baidu.com","","");
    }
}
