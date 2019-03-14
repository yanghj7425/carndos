package com.carndos.modules.activity;


import com.carndos.config.activity.ActivityConfig;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ActivityConfig.class)

public class ActivityTest {

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Test
    public void createTable() {
        processEngineConfiguration.buildProcessEngine();
    }
}
