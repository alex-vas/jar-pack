package com.nuix.utils.packed.jres;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PackedJres
{

    private static final ServiceLoader<PackedJre> SERVICE_LOADER = ServiceLoader.load(PackedJre.class);

    public static List<PackedJre> getAll()
    {
        List<PackedJre> res = new ArrayList<>();
        SERVICE_LOADER.forEach(res::add);
        return res;
    }

    public static void main(String[] args)
    {
        getAll().forEach(jre ->
        {
            System.out.println(jre.getVersion() +" - "+ jre.getVendor());
        });
        System.out.println("Done.");
    }

}
