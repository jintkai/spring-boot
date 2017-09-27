package com.jon.project.dao;

import com.jon.common.BaseDao;
import com.jon.project.domain.TbProject;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProjectDaoImp  extends BaseDao implements ProjectDao{
    @Override
    public int insertProject(TbProject domain) {
        System.out.println(domain.toString());

        return jdbcTemplate.update("insert into project(PROJECTKEY,PROJECTNAME,PROJECTDESC) VALUES (?,?,?) ",
                domain.getProjectKey(),domain.getProjectName(),domain.getProjectDesc());
    }

    @Override
    public int deleteProject(String  uid) {
        return jdbcTemplate.update("delete from project where uid = ?",uid);
    }

    @Override
    public TbProject findProject(String uid) {
        List<TbProject> lists = jdbcTemplate.query("select * from project where uid = ?", new RowMapper<TbProject>() {
            @Override
            public TbProject mapRow(ResultSet rs, int rowNum) throws SQLException {
                return setProject(rs,rowNum);
            }
        },new String[]{uid});

        if (lists.size() == 1){
            return lists.get(0);
        }else{
            return null;
        }

    }

    @Override
    public TbProject findProjectByKey(String key) {
        List<TbProject> lists = jdbcTemplate.query("select * from project where projectkey = ?", new RowMapper<TbProject>() {
            @Override
            public TbProject mapRow(ResultSet rs, int rowNum) throws SQLException {
                return setProject(rs,rowNum);
            }
        },new String[]{key});

        if (lists.size() == 1){
            return lists.get(0);
        }else{
            return null;
        }
    }

    public TbProject setProject(ResultSet rs, int rowNum) throws SQLException {
        TbProject project = new TbProject();
        project.setUid(rs.getString("UID"));
        project.setProjectKey(rs.getString("PROJECTKEY"));
        project.setProjectName(rs.getString("PROJECTNAME"));
        project.setProjectDesc(rs.getString("PROJECTDESC"));
        return project;
    }

    @Override
    public List<TbProject> findProjectList(String projectName) {
        List<TbProject> list = jdbcTemplate.query("select * from project where projectname like concat('%',?,'%')",new Object[]{projectName},new RowMapper<TbProject>() {
            @Override
            public TbProject mapRow(ResultSet rs, int rowNum) throws SQLException {
                return setProject(rs,rowNum);
            }
        });
        return list;
    }


}
