package com.kharitonov.personnel.configs;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DropboxConfig {

    @Bean
    public DbxClientV2 dbxClientV2() {
        DbxRequestConfig dbxRequestConfig = DbxRequestConfig.newBuilder("personnel/1.0").build();
        DbxClientV2 dbxClientV2 = new DbxClientV2(dbxRequestConfig, System.getenv("ACCESS_TOKEN"));
        return dbxClientV2;
    }
}
