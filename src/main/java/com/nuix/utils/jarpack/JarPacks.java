package com.nuix.utils.jarpack;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class JarPacks
{

    private static final ServiceLoader<JarPack> SERVICE_LOADER = ServiceLoader.load(JarPack.class);

    public static List<JarPack> getAll()
    {
        List<JarPack> res = new ArrayList<JarPack>();
        for (JarPack jre : SERVICE_LOADER)
        {
            res.add(jre);
        }
        return res;
    }

}
