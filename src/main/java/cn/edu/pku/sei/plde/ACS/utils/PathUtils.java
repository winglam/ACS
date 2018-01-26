package cn.edu.pku.sei.plde.ACS.utils;

import cn.edu.pku.sei.plde.ACS.agent.RunTestAgent;
import org.junit.runner.JUnitCore;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yanrunfa on 16/2/24.
 */
public class PathUtils {

    public static String getFileSeparator(){
        //"/"
        return System.getProperty("file.separator");
    }
    public static String getPathSeparator(){
        //":"
        return System.getProperty("path.separator");
    }

    public static String getLineSeparator(){
        //"\n"
        return System.getProperty("line.separator");
    }

    public static String getAgentPath(){
        return System.getProperty("user.dir")+"/lib/RunTestAgent.jar";
    }


    public static String getJunitPath(){
        return System.getProperty("user.dir")+"/lib/junit-4.10.jar";
    }

    public static String getPackageNameFromPath(String path){
        try {
            String className = path.substring(path.lastIndexOf(getFileSeparator())+1,path.lastIndexOf("."));
            String code = FileUtils.getCodeFromFile(path);
            for (String line: code.split("\n")){
                if (line.startsWith("package")){
                    return line.substring(line.indexOf(' '),line.indexOf(";"))+"."+className;
                }
            }
        } catch (Exception e){
            if (path.contains("/java/")){
                return path.split("/java/")[1].replace(getFileSeparator(),".");
            }
        }
        return path.replace(getFileSeparator(),".");
    }

    public static String projectName = null;
    public static int bugId = -1;
    public static String module = "";
    public static ArrayList<String> getSrcPath(String bugProject){
        ArrayList<String> path = new ArrayList<String>();
        String[] words = bugProject.split("_");
        projectName = words[0];
        bugId = Integer.parseInt(words[1]);
        if(projectName.equals("MATH")){
//            if(bugId < 85){
                path.add("/target/classes/");
                path.add("/target/test-classes/");
                path.add("/src/main/java/");
                path.add("/src/test/java/");
//            }else{
//                path.add("/target/classes/");
//                path.add("/target/test-classes/");
//                path.add("/src/java/");
//                path.add("/src/test/");
//            }
        } else if(projectName.equals("ACCUMULO")){
            if (bugId <= 414) {
                module = "src/core";
            } else if (bugId == 1544 || bugId == 3150){
                module = "minicluster";
            } else if (bugId == 3897){
                module = "server";
            } else if (bugId == 3945){
                module = "shell";
            } else {
                module = "core";
            }
            path.add("/"+module+"/target/classes/");
            path.add("/"+module+"/target/test-classes/");
            path.add("/"+module+"/src/main/java/");
            path.add("/"+module+"/src/test/java/");
        } else if(projectName.equals("CAMEL")){
            module = "camel-core";
            path.add("/"+module+"/target/classes/");
            path.add("/"+module+"/target/test-classes/");
            path.add("/"+module+"/src/main/java/");
            path.add("/"+module+"/src/test/java/");
        } else if(projectName.equals("MNG")){
            if (bugId == 2221) {
                module = "maven-project";
            } else if (bugId == 2281){
                module = "maven-artifact";
            } else if (bugId == 2408){
                module = "maven-artifact-manager";
            } else if (bugId == 3991 || bugId == 4837 || bugId == 5647){
                module = "maven-model-builder";
            } else {
                module = "maven-core";
            }
            path.add("/"+module+"/target/classes/");
            path.add("/"+module+"/target/test-classes/");
            path.add("/"+module+"/src/main/java/");
            path.add("/"+module+"/src/test/java/");
        } else if(projectName.equals("OAK")){
            if (bugId == 888) {
                module = "oak-commons";
            } else if (bugId == 479 || bugId == 510 || bugId == 738 || bugId == 1093 ||
                    bugId == 1244 || bugId == 3517 || bugId == 3930){
                module = "oak-jcr";
            } else if (bugId == 1208 || bugId == 3137 || bugId == 3367 || bugId == 3920 || bugId == 4351
                    || bugId == 4359){
                module = "oak-lucene";
            } else if (bugId == 539 || bugId == 1122){
                module = "oak-mk";
            } else if (bugId == 740){
                module = "oak-solr-core";
            } else if (bugId == 2355){
                module = "oak-tarmk-standby";
            } else if (bugId == 1111 || bugId == 2047){
                module = "oak-upgrade";
            } else {
                module = "oak-core";
            }
            path.add("/"+module+"/target/classes/");
            path.add("/"+module+"/target/test-classes/");
            path.add("/"+module+"/src/main/java/");
            path.add("/"+module+"/src/test/java/");
        }else if(projectName.equals("Time")){
            if(bugId < 12){
                path.add("/target/classes/");
                path.add("/target/test-classes/");
                path.add("/src/main/java/");
                path.add("/src/test/java/");
            }else{
                path.add("/build/classes/");
                path.add("/build/tests/");
                path.add("/src/main/java/");
                path.add("/src/test/java/");
            }
        }else if(projectName.equals("Lang")){
            if(bugId <= 20){
                path.add("/target/classes/");
                path.add("/target/tests/");
                path.add("/src/main/java/");
                path.add("/src/test/java/");
            }else if(bugId >= 21 && bugId <= 35){
                path.add("/target/classes/");
                path.add("/target/test-classes/");
                path.add("/src/main/java/");
                path.add("/src/test/java/");
            }else if(bugId >= 36 && bugId <= 41){
                path.add("/target/classes/");
                path.add("/target/test-classes/");
                path.add("/src/java/");
                path.add("/src/test/");
            }else if(bugId >= 42 && bugId <= 65){
                path.add("/target/classes/");
                path.add("/target/tests/");
                path.add("/src/java/");
                path.add("/src/test/");
            }
        }else if(projectName.equals("Chart")){
            path.add("/build/");
            path.add("/build-tests/");
            path.add("/source/");
            path.add("/tests/");

        }else if(projectName.equals("Closure")){
            path.add("/build/classes/");
            path.add("/build/test/");
            path.add("/src/");
            path.add("/test/");
        }
        return path;
    }

}
