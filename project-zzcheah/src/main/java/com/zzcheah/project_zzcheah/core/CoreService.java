package com.zzcheah.project_zzcheah.core;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan({
        "com.zzcheah.common_project.services",
        "com.zzcheah.common_project.configurations"
})
@AllArgsConstructor
public class CoreService {

}
