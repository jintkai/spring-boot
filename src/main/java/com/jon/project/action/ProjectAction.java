package com.jon.project.action;

import com.jon.project.domain.TbProject;
import com.jon.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
@SpringBootApplication
public class ProjectAction {

    @Autowired
    ProjectService projectService;

    @RequestMapping(method = RequestMethod.GET,value = "/list/{projectName}")
    public Map<String,Object> getProjectList(@PathVariable String projectName){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("status",1);
        List<TbProject> list = projectService.findProjectList(projectName);
        resultMap.put("lists",list);
        resultMap.put("count",list.size());
        return resultMap;
    }

    @RequestMapping(method = RequestMethod.GET,value="/{uid}")
    public Map<String,Object> getProject(@PathVariable String uid){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        TbProject project = (TbProject)projectService.findProject(uid);
        resultMap.put("project",project);
        resultMap.put("status",1);
        return resultMap;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
    public Map<String,Object> deleteProject(@PathVariable String uid){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("status",1);
        resultMap.put("message",projectService.deleteProject(uid));
        return resultMap;
    }

    @RequestMapping(method = RequestMethod.POST, value="/add")
    public Map<String,Object> addProject(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "desc", required = true) String desc){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        TbProject tbProject = new TbProject();
        tbProject.setProjectKey(key);
        tbProject.setProjectName(name);
        tbProject.setProjectDesc(desc);
        int i = projectService.addProject(tbProject);
        if ( 0 ==i){
            resultMap.put("status",0);
            resultMap.put("message","插入项目信息失败！");
        }else{
            resultMap.put("status",1);
            resultMap.put("message","添加成功！");
        }
        return resultMap;
    }
}
