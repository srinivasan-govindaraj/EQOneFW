package dev.eq.utills;

import dev.eq.enums.Props;
import org.aeonbits.owner.Config;

import java.util.List;

@Config.Sources(value = "file:${user.dir}/src/main/resources/config.properties")

public interface config extends Config {
    String url();  // we can use enum
    String testdata();
    int retrycount();
    List<String> List();
    Boolean passonlyss();
    @Key("newreport")//  its should be match
    String newreport();
    @DefaultValue("EQ")
    String name();
}
