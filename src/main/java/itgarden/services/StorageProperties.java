/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itgarden.services;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 *
 * @author Md Belayet Hossin
 */
@ConfigurationProperties
@Service
public class StorageProperties {

    String rootPath = System.getProperty("catalina.base");
    String pathurl = "/";

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String url() {
        try {
            URL filePath = Paths.get(rootPath + File.separator + "bims_repo").toUri().toURL();

            String pathurl = filePath.toString();
            return pathurl;
        } catch (Exception e) {
            return pathurl;
        }
    }

}
