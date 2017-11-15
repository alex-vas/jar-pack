package com.nuix.utils.jarpack;

import java.io.InputStream;

public interface JarPackEntry
{

    String getName();

    JarPack getPack();

    String getMD5();

    String getSHA1();

    InputStream getContent();

}
