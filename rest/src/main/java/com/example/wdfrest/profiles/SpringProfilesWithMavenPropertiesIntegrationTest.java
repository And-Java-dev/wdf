package com.example.wdfrest.profiles;

import com.example.wdfrest.profiles.config.DatasourceConfig;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringProfilesWithMavenPropertiesIntegrationTest {

    @Autowired
    DatasourceConfig datasourceConfig;

    public void setupDatasource() {
        datasourceConfig.setup();
    }
}
