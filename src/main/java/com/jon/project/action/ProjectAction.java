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

    private static final String STATUS = "status";
    private static final String MESSAGE = "message";

    @Autowired
    ProjectService projectService;


    /**
     * 根据项目名称，查询多个项目信息
     * @param projectName：项目名称
     * @return 项目列表
     */
    @RequestMapping(method = RequestMethod.GET,value = "/list/{projectName}")
    public Map<String,Object> getProjectList(@PathVariable String projectName){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put(STATUS,1);
        List<TbProject> list = projectService.findProjectList(projectName);
        resultMap.put("lists",list);
        resultMap.put("count",list.size());
        return resultMap;
    }

    /**
     * 返还项目的详细信息
     * @param uid：项目id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value="/{uid}")
    public Map<String,Object> getProject(@PathVariable String uid){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        TbProject project = (TbProject)projectService.findProject(uid);
        resultMap.put("project",project);
        resultMap.put(STATUS,1);
        return resultMap;
    }

    /**
     * 删除项目信息；
     * @param uid：项目id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{uid}")
    public Map<String,Object> deleteProject(@PathVariable String uid){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put(STATUS,1);
        resultMap.put(MESSAGE,projectService.deleteProject(uid));
        return resultMap;
    }

    /**
     * 增加项目
     * @param key：项目关键字
     * @param name：项目名称
     * @param desc：项目描述
     * @return status：0，表示key已经存在，无法插入；1表示成功。
     */
    @RequestMapping(method = RequestMethod.POST, value="/add")
    public Map<String,Object> addProject(@RequestParam(value = "key", required = true) String key, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "desc", required = true) String desc){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        TbProject tbProject = new TbProject();
        tbProject.setProjectKey(key);
        tbProject.setProjectName(name);
        tbProject.setProjectDesc(desc);
        int i = projectService.addProject(tbProject);
        if ( 0 ==i){
            resultMap.put(STATUS,0);
            resultMap.put(MESSAGE,"插入项目信息失败，项目key已经存在！");
        }else{
            resultMap.put(STATUS,1);
            resultMap.put(MESSAGE,"添加成功！");
        }
        return resultMap;
    }
}
