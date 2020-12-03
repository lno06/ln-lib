package com.lnlib.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is protected with the Web Security Configuration
 */
@RestController
@RequestMapping("/requires-login")
public class RequiresLoginController
{

    @GetMapping
    public String securedPage()
    {
        return "If you can see this, the web security does not work";
    }
}
