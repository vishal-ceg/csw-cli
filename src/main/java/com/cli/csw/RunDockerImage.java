/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cli.csw;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;

/**
 *
 * @author Vishal
 */
public class RunDockerImage extends DockerCmdExecutor<RunDockerImage>{

    @Override
    public RunDockerImage execute(DockerClient client) {
        CreateContainerResponse res = client.createContainerCmd("findsecbugs")
                .withBinds(Bind.parse("C:\\Users\\Vishal\\Documents\\NetBeansProjects\\DependencyTree:/workdir/scan"))
               // .withVolumes(new Volume("C:\\Users\\Vishal\\Documents\\NetBeansProjects\\DependencyTree:/workdir/scan"))
                .exec();
        System.out.println(res.getId());
        
        client.startContainerCmd(res.getId())
                .exec();
        System.out.println( res.getId());
       return this;
    }
    
    
    
}
