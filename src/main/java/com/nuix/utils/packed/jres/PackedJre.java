package com.nuix.utils.packed.jres;

import java.util.List;

public interface PackedJre
{

    String getVendor();

    String getPlatform();

    String getVersion();

    String getJarPath();

    List<PackedJreEntry> getEntries();

}
