package com.lnlib.springboot.mapper;

import com.lnlib.mapping.Source;
import com.lnlib.mapping.Target;
import fr.xebia.extras.selma.*;

/**
 * Mapping from Source to Target.
 * <p>
 * It is case insensitive
 */
@Mapper(
        withCustomFields = {
                // flatten address fields : both available for sourceToTarget and targetToSource
                @Field({"address.street", "addressStreet"}),
                @Field({"address.postCode", "addressPostCode"}),
                @Field({"address.city", "addressCity"}),
                // custom mapper with field "identity" of type Identity becomes a field "name" of type String
                @Field(value = {"identity", "name"}, withCustom = CustomNameMapper.class)
        },
        // ignore ( source.id ) : this field is not in Target class
        withIgnoreMissing = IgnoreMissing.DESTINATION,
        // can be used as a Spring bean (is generated as a Service)
        withIoC = IoC.SPRING)
public interface SourceToTargetMapper
{
    /**
     * fresh new Object
     */
    Target toTarget(Source in);

    /**
     * update given object
     */
    Target update(Source in, Target out);

    // override default @Mapper

    /**
     * Custom mapper for targetToSource
     */
    @Maps(withCustomFields = {
            // transform name to identity, using CustomIdentityMapper
            @Field(value = {"name", "identity"}, withCustom = CustomIdentityMapper.class)
    },
            // ignore source.id
            withIgnoreMissing = IgnoreMissing.SOURCE)
    Source toSource(Target in);


}
