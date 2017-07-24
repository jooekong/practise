package com.ericsson.eliokog.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eliokog on 2017/6/12.
 */
public class PatternTest
{

    public static void main(String[] args) {


        Matcher matcher = Pattern.compile("\\$(.*?)\\}").matcher("10.0.0.18");
        while (matcher.find()) {
            String macro = matcher.group();

            String propertyKey = macro.substring(2, macro.length() - 1);
//            String propertyValue = ovfProcessingContext.getDescriptorPropertyForCurrentVm(propertyKey);
//
//            if (propertyValue == null) {
//                throw new UnrecoverableEcmException.Builder().add(
//                        MessageCode.ECMRS4xx_OVF_NO_SUCH_DESCRIPTOR_PROPERTY_DEFINED, propertyKey).build();
//            } else {
                String attributeValue = matcher.replaceFirst("1234");
                matcher.reset(attributeValue);
//            }
            System.out.println(propertyKey);
        }



    }
}
