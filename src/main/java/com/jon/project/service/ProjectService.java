package com.jon.project.service;

import com.jon.project.dao.ProjectDao;
import com.jon.project.domain.TbProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ProjectService {

    @Autowired
    ProjectDao projectDao;

    public String saveProject(){
        TbProject domain = new TbProject();
        domain.setUid(String.valueOf((int) (Math.random()*10000)));
        domain.setProjectKey(domain.getUid());
        domain.setProjectName("test");
        domain.setProjectDesc("Hello");
        System.out.println(projectDao.insertProject(domain));
        return "success";
    }

    public Object findProject(String uid){
        return projectDao.findProject(uid);
    }

    public Object findProjectByKey(String projectKey){
        return projectDao.findProjectByKey(projectKey);
    }

    public List<TbProject> findProjectList(String projectName){
        return projectDao.findProjectList(projectName);
    }

    public String deleteProject(String uid){
        int i = projectDao.deleteProject(uid);
        if ( i == 0){
            return "false";
        }
        return "true";
    }

    public int addProject(TbProject tbProject){
        Object isExist = findProjectByKey(tbProject.getProjectKey());
        if (null != isExist){
            return 0;
        }else{
            return projectDao.insertProject(tbProject);
        }
    }
}
