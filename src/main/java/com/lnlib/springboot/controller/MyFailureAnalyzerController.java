package com.lnlib.springboot.controller;

import com.lnlib.bestpractice.FunctionalIf;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is used to show how failure analyzer works.
 * To see it working, uncomment line with @Autowired, then restart the app.
 * <p>
 * In the logs, you will see the message from {@link com.lnlib.springboot.failureanalyzer.MyFailureAnalyzer}
 * because there is no bean names FunctionalIf
 */
@RestController
public class MyFailureAnalyzerController
{
    //    @Autowired
    private FunctionalIf functionalIf;
}
