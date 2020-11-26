/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cli.csw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Vishal
 */
public class App {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Invalid input. Please provide project path as arg");
            System.exit(0);
        }

        //RunDockerImage r = new RunDockerImage();
        //r.execute(DockerClientConfig.getClient());
        // have some issue with docker-java library for now running using cmd
        // step-1 pull the image based on scanner
        String finalArgs = "docker run -v " + args[0] + ":/workdir/scan findsecbugs";

        try {

            Process process = Runtime.getRuntime().exec("cmd.exe  /c " + finalArgs);

            // error
            StringBuilder outputErr = new StringBuilder();
            BufferedReader reader1 = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));   
            String line1;
            while ((line1 = reader1.readLine()) != null) {
                outputErr.append(line1 + "\n");
            }
            System.out.println(outputErr);
            // result
            StringBuilder output = new StringBuilder();
            int exitVal = process.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
            } else {
                System.out.println("Error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed!!!!!!");

    }

}
