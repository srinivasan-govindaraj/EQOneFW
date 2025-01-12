package dev.eq.props;

import dev.eq.enums.Props;
import dev.eq.props.converter.StringtoenumConverter;
import org.aeonbits.owner.Config;

import java.util.List;
/*@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system.properties",
        "system:env",
        "file:${user.dir}/src/main/resources/config.properties"})*/

@Config.Sources(value = "file:${user.dir}/src/main/resources/config.properties")

// Pleaase excel.java for implementation
public interface Configs extends Config {
    //@DefaultValue("https://www.google.com")
    //@Key("url")
   // @Config.ConverterClass(StringtoenumConverter.class)
    @Key("${Props}")
    String getValue(String propName);
    // we can use enum
    //String url();
    String testdata();
    int retrycount();
    List<String> List();
    Boolean passonlyss();
    @Key("newreport")//  its should be match
    String newreport();
    @DefaultValue("EQ")
    String name();
}
