package com.lnlib.springboot.mapper;

import com.lnlib.mapping.Identity;
import org.springframework.stereotype.Component;

/**
 * Transform Identity to String
 */
@Component
public class CustomNameMapper
{
    public String identityAsString(Identity identity)
    {
        return identity.getFirstname() + " " + identity.getLastname();
    }
}
