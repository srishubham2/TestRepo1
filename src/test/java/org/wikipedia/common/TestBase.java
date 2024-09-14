package org.wikipedia.common;

import java.util.Properties;


public class TestBase {

    private static Properties userDetailsProps;

    public static void setUserDetails(String fileName, String folderName){
        userDetailsProps = WikiUtils.getProperties(fileName, folderName);
    }

    public static Properties getUserDetails(){
        return userDetailsProps;
    }
}
