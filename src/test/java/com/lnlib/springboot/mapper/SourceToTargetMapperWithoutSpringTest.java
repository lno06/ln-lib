package com.lnlib.springboot.mapper;

import com.lnlib.mapping.Address;
import com.lnlib.mapping.Identity;
import com.lnlib.mapping.Source;
import com.lnlib.mapping.Target;
import fr.xebia.extras.selma.Selma;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceToTargetMapperWithoutSpringTest
{
    private final SourceToTargetMapper mapper = Selma.mapper(SourceToTargetMapper.class);

    @Test
    void sourceToTarget()
    {
        var source = new Source();
        source.setId(1);
        source.setAge(2);
        var identity = new Identity();
        identity.setFirstname("first");
        identity.setLastname("last");
        source.setIdentity(identity);
        var address = new Address();
        address.setStreet("street");
        address.setPostCode("post");
        address.setCity("city");
        source.setAddress(address);
        var target = mapper.toTarget(source);

        assertAll(
                () -> assertEquals(2, target.getAge()),
                () -> assertEquals("first last", target.getName()),
                () -> assertEquals("street", target.getAddressStreet()),
                () -> assertEquals("post", target.getAddressPostCode()),
                () -> assertEquals("city", target.getAddressCity())
        );
    }

    @Test
    void targetToSource()
    {
        var target = new Target();
        target.setAge(2);
        target.setName("first last");
        target.setAddressStreet("street");
        target.setAddressCity("city");
        target.setAddressPostCode("post");
        var source = mapper.toSource(target);

        assertAll(
                () -> assertNull(source.getId()),
                () -> assertEquals(2, source.getAge()),
                () -> assertEquals("first", source.getIdentity().getFirstname()),
                () -> assertEquals("last", source.getIdentity().getLastname()),
                () -> assertEquals("street", source.getAddress().getStreet()),
                () -> assertEquals("post", source.getAddress().getPostCode()),
                () -> assertEquals("city", source.getAddress().getCity())
        );
    }
}