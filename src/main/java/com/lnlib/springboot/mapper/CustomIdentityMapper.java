package com.lnlib.springboot.mapper;

import com.lnlib.mapping.Identity;
import org.springframework.stereotype.Component;

/**
 * Transform String to Identity (assumnig the given string contains a space)
 */
@Component
public class CustomIdentityMapper
{
    /**
     * Assuming there is a white space in the name
     */
    public Identity stringAsIdentity(String name)
    {
        var names = name.split(" ");
        var identity = new Identity();
        identity.setFirstname(names[0]);
        identity.setLastname(names[1]);
        return identity;
    }
}
