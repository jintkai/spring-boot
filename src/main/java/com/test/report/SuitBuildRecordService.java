package com.test.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuitBuildRecordService {
    @Autowired
    SuitReportDao suitReportDao;

    public List<SuitBuildRecord>getSuitBuildRecordList(String suitKey){
        return suitReportDao.getSuitBuildRecordList(suitKey);
    }
}
