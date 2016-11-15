package com.nuix.utils.jarpack.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.nuix.utils.jarpack.JarPack;
import com.nuix.utils.jarpack.JarPackEntry;

public class JarPackImplementationTemplate implements JarPack
{

    @Override
    public String getProduct()
    {
        return "product"; // will be substituted by maven's regexp plugin
    }

    @Override
    public String getVendor()
    {
        return "vendor"; // will be substituted by maven's regexp plugin
    }

    @Override
    public String getPlatform()
    {
        return "platform"; // will be substituted by maven's regexp plugin
    }

    @Override
    public String getVersion()
    {
        return "version"; // will be substituted by maven's regexp plugin
    }

    @Override
    public String getPath()
    {
        return "path"; // will be substituted by maven's regexp plugin
    }

    @Override
    public List<JarPackEntry> getEntries()
    {
        final List<JarPackEntry> res = new ArrayList<JarPackEntry>();

        InputStream is = JarPackImplementationTemplate.class.getClassLoader().getResourceAsStream("META-INF/"+ getPath() +"/file-list.xml");
        try
        {
            SAXParserFactory.newInstance().newSAXParser().parse(is, new DefaultHandler()
            {
                boolean inMd5 = false;
                boolean inSha1 = false;

                String fileName;
                String md5;
                String sha1;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
                {
                    if (qName.equals("file"))
                    {
                        fileName = attributes.getValue("name").replace('\\', '/');
                        md5 = null;
                        sha1 = null;
                    }
                    if (qName.equals("hashcode"))
                    {
                        String algorithm = attributes.getValue("algorithm");
                        inMd5 = algorithm.equals("MD5");
                        inSha1 = algorithm.equals("SHA-1");
                    }
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException
                {
                    inMd5 = false;
                    inSha1 = false;

                    if (qName.equals("file"))
                    {
                        res.add(new JarPackEntry()
                        {
                            final String finalFileName = fileName;
                            final String finalMd5 = md5;
                            final String finalSha1 = sha1;

                            @Override
                            public String getName()
                            {
                                return finalFileName;
                            }
                            @Override
                            public String getMD5()
                            {
                                return finalMd5;
                            }
                            @Override
                            public String getSHA1()
                            {
                                return finalSha1;
                            }
                            @Override
                            public InputStream getContent()
                            {
                                return JarPackImplementationTemplate.class.getClassLoader().getResourceAsStream(getPath() +"/"+ getName());
                            }
                            @Override
                            public String toString()
                            {
                                return finalFileName +" (MD5:"+ finalMd5 +", SHA-1:"+ finalSha1 +")";
                            }
                        });
                    }
                }
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException
                {
                    if (inMd5)
                    {
                        md5 = new String(ch, start, length);
                    }
                    else if (inSha1)
                    {
                        sha1 = new String(ch, start, length);
                    }
                };
            });
        }
        catch (Exception e)
        {
            throw new IllegalStateException(e);
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                throw new IllegalStateException(e);
            }
        }

        return res;
    }

}
