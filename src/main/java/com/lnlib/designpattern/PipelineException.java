package com.lnlib.designpattern;


/**
 * Dedicated exeption for pipelines
 */
class PipelineException extends RuntimeException
{

    public PipelineException(Throwable e)
    {
        super(e);
    }
}
