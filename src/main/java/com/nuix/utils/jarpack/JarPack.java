package com.nuix.utils.jarpack;

import java.util.List;

public interface JarPack
{

    String getProduct();

    String getVendor();

    String getPlatform();

    String getVersion();

    String getPath();

    List<JarPackEntry> getEntries();

}
