# jre2jar

Small Maven project which packs given JRE into a JAR with a POM.
Result can be added to a private Maven repository and referenced by your builds.
This can be handy for those who check in the JRE into the source control system, and looking for the ways to reduce its size.

# Creating a JAR

 1. Copy your JRE to `/jre` directory. Note that it has a `/bin` sub directory with `java` or `java.exe` and `/lib` with `rt.jar` in it.
 2. Edit `<artifactId>` and `<version>` values in `pom.xml` file as appropriate. Make note of the comments as to the format.
 3. Run `mvn clean package`.
 4. Your `jar` and `pom` files would be in the `/target` directory.
 5. Upload (deploy) your `jar` and `pom` to your company's private Maven repository. 

# Using the JAR as a Java library

In rear situations you would need to access JRE binary from within a Java application as a resource. Resulting JAR offers the following interface for your convenience:

        ServiceLoader.load(PackedJre.class).forEach(jre ->
        {
            System.out.println("JRE found: "+ jre.getVendor() +" - "+ jre.getPlatform() +" - "+ jre.getVersion() +" - "+ jre.getPath());
        });

or try this to get individual file entries along with MD5s:

        PackedJres.getAll().forEach(jre ->
        {
            jre.getEntries().forEach(file -> {
                System.out.println(file.getName() +", MD5:"+ file.getMD5());
            });
        });
