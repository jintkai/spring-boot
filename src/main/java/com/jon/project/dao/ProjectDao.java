package com.jon.project.dao;

import com.jon.project.domain.TbProject;

import java.util.List;

public interface ProjectDao {

    int insertProject(TbProject domain);

    int deleteProject(String uid);

    TbProject findProject(String uid);
    TbProject findProjectByKey(String key);

    List<TbProject> findProjectList(String projectName);

}
