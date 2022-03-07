package com.zzcheah.project_zzcheah;

import com.zzcheah.common_project.annotations.EnableEdiProject;
import com.zzcheah.system_mock1.annotations.EnableSystem1EdiProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSystem1EdiProcessor
@EnableEdiProject
public class ZZCheahApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZZCheahApplication.class,args);

    }
}
