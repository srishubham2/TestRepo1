package org.wikipedia.common;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;

public class WikiUtils {

    public static final String LOCAL_PATH_FOR_PROPERTIES;

    static {
        LOCAL_PATH_FOR_PROPERTIES = File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "properties" + File.separator ;
    }

    /**
     * Method used for logging messages in console.
     * @param msg
     */
    public static void log(String msg){
        // ToDo : Implement Log4J here -
        System.out.println(now() + " *** " + msg);
    }

    /**
     * Method to print all key-value pairs
     * @param response
     */
    public static void log(Response response){
        // ToDo : Implement Log4J here -
        System.out.print("Response data => \n");
        Map<String, Object> responseMap = response.jsonPath().getMap("");

        // Print all the keys and values
        for (Map.Entry<String, Object> entry : responseMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    /**
     * Method to Get the current date and time, Format and print the current date and time.
     * Format : "yyyy.MM.dd Hour.Minute.Second.Milli-Seconds"
     * @return
     */
    public static String now(){
        // Get the current date and time, Format and print the current date and time
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss.SSS"));
    }

    /**
     * Method to get Properties object after fetching key:value details from given fileName under given folderName.
     * @param fileName
     * @param folderName
     * @return
     */
    public static Properties getProperties(String fileName, String folderName) {
        WikiUtils.log("getProperties() :: Fetching Properties for : " + folderName + File.separator + fileName );
        Properties props = new Properties();
        File file = new File( System.getProperty("user.dir") + LOCAL_PATH_FOR_PROPERTIES + folderName + File.separator + fileName );
        try {
            FileInputStream fileInput = new FileInputStream(file);

            try {
                props.load(fileInput);
                WikiUtils.log("Properties file load succeeded. " + props.size());
            } catch (Throwable e1) {
                try {
                    fileInput.close();
                } catch (Throwable e2) {
                    e2.printStackTrace();
                }
                throw e1;
            }

            fileInput.close();
        } catch (FileNotFoundException e) {
            WikiUtils.log("Properties file load failed.");
            e.printStackTrace();
        } catch (IOException e) {
            WikiUtils.log("Some other error occurred when loading properties file.\n"+e.getMessage());
            e.printStackTrace();
        }
        return props;
    }


}
