/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cli.csw;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Vishal
 */
public class DockerClientConfig {

    private static DockerClient dockerClient;

    public static DockerClient getClient() throws Exception {
        if (dockerClient == null) {
            dockerClient = DockerClientBuilder.getInstance(config()).build();
        }
        return dockerClient;
    }

    private static com.github.dockerjava.core.DockerClientConfig config() throws Exception {
        Properties p = getConfigProps();
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryEmail("vishalceg@gmail.com" )
                .withRegistryPassword("vishal123#")
                .withRegistryUsername("vishalceg")
                .withDockerHost("tcp://localhost:2375").build();
         
//        withDockerHost(p.getProperty("url")) //  String localDockerHost = SystemUtils.IS_OS_WINDOWS ? "http://localhost:2375" : "unix:///var/run/docker.sock";
//                .withUsername(p.getProperty("user"))
//                .withPassword(p.getProperty("pass"))
//                .withEmail(p.getProperty("email"))
//                .withServerAddress("https://index.docker.io/v1/")
//                .withDockerCertPath("/home/user/.docker")
//                .build();
        return config;
    }

    private static Properties getConfigProps() throws FileNotFoundException, IOException {
        FileReader reader = new FileReader("config.properties");
        Properties p = new Properties();
        p.load(reader);
        return p;
    }

}
