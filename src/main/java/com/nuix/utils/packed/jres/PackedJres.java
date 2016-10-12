package com.nuix.utils.packed.jres;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PackedJres
{

    private static final ServiceLoader<PackedJre> SERVICE_LOADER = ServiceLoader.load(PackedJre.class);

    public static List<PackedJre> getAll()
    {
        List<PackedJre> res = new ArrayList<PackedJre>();
        for (PackedJre jre : SERVICE_LOADER)
        {
            res.add(jre);
        }
        return res;
    }

}
