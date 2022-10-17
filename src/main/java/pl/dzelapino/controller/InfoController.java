package pl.dzelapino.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    private DataSourceProperties dataSource;
    @Value("${my.prop}")
    private String devProp;

    @GetMapping("/info/url")
    String url() {
        return dataSource.getUrl();
    }
    @GetMapping("/info/dev")
    String devProp() {
        return devProp;
    }
}
