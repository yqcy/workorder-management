package com.yq.service;

import com.yq.dao.WorkerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by wb-yq264139 on 2017/11/10
 */
@Service
public class WorkorderService {

    @Autowired
    private WorkerOrderMapper workerOrderMapper;

}
