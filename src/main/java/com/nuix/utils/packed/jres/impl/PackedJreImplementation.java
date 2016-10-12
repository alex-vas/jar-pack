package com.nuix.utils.packed.jres.impl;

import com.nuix.utils.packed.jres.PackedJre;

public class PackedJreImplementation implements PackedJre
{

    @Override
    public String getVendor()
    {
        return "vendor";
    }

    @Override
    public String getPlatform()
    {
        return "platform";
    }

    @Override
    public String getVersion()
    {
        return "version";
    }

    @Override
    public String getPath()
    {
        return "path";
    }

}
