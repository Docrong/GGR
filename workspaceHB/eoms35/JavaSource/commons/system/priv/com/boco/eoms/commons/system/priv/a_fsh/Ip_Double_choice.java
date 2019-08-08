package com.boco.eoms.commons.system.priv.a_fsh;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.ui.listitem.TawCommonsUIListItem;

/**
 * 代码生成task测试用例
 *
 * @author qiuzi
 */
public class Ip_Double_choice {
    private static Log log = LogFactory.getLog(Ip_Double_choice.class);

    public static List ip2DoubleIpFromUI(List tawCommonsUIListItem,
                                         Ipconfig ipconfig2, String type) {
        if (type != null) {
            String url = "";
            for (int i = 0; i < tawCommonsUIListItem.size(); i++) {
                url = ((TawCommonsUIListItem) tawCommonsUIListItem.get(i))
                        .getUrl();
                if (url.startsWith("http")) {
                    url = getUrl(ipconfig2, url, type);
                    ((TawCommonsUIListItem) tawCommonsUIListItem.get(i))
                            .setUrl(url);
                }
            }
        } else {
            log
                    .debug("请正确配置menu_ip.xml文件的default字段，它是判断内外网的ip配置,由于配置错误没有执行ip转换");
            // System.out.println("请正确配置menu_ip.xml文件的default字段,由于配置错误没有执行ip转换");
        }
        return tawCommonsUIListItem;
    }

    public static List ip2DoubleIpFromOperation(List Operations,
                                                Ipconfig ipconfig2, String type) {
        if (type != null) {
            String url = "";
            for (int i = 0; i < Operations.size(); i++) {
                url = ((TawSystemPrivOperation) Operations.get(i)).getUrl();
                if (url.startsWith("http")) {
                    url = getUrl(ipconfig2, url, type);
                    ((TawSystemPrivOperation) Operations.get(i)).setUrl(url);
                }
            }
        } else {
            log
                    .debug("请正确配置menu_ip.xml文件的default字段，它是判断内外网的ip配置,由于配置错误没有执行ip转换");
            // System.out.println("请正确配置menu_ip.xml文件的default字段,由于配置错误没有执行ip转换");
        }
        return Operations;
    }

    public static String checkIp(String ip2) {

        String iptrue = ip2;
        Ipconfig ipconfig = Ipconfig_creat.getIpconfig();
        Ip_pair[] ip_pairs = ipconfig.getIp_pair();
        for (int i = 0; i < ip_pairs.length; i++) {
            if (ip_pairs[i].getName().equals("default")) {
                Ip[] ipArr = ip_pairs[i].getIp();
                for (int j = 0; j < ipArr.length; j++) {
                    String m_url = ipArr[j].getUrl();
                    String m_dnsName = ipArr[j].getDnsname();
                    if (iptrue.equals(m_url)) {
                        return ipArr[j].getType();
                    } else if (iptrue.equals(m_dnsName)) {
                        return ipArr[j].getType();
                    }
                }
            }
        }
        log.debug("请正确配置menu_ip.xml文件的default字段，它是判断内外网的ip配置,由于配置错误没有执行ip转换");
        // System.out.println("请正确配置menu_ip.xml文件的default字段,由于配置错误没有执行ip转换");
        return null;
    }

    private static String getUrl(Ipconfig ipconfig2, String menuItem,
                                 String type) {
        Ipconfig ipconfig = ipconfig2;
        String[] page = menuItem.split("/");
        String page2 = page[2];
        String iptrue = page2;
        String result = getRealIp(iptrue, type, ipconfig);
        String finallyIp = "";
        if (!"".equals(result)) {
            finallyIp = page[0] + "//" + result + "/";
            for (int i = 3; i < page.length; i++) {
                finallyIp += page[i] + "/";
            }
            finallyIp = finallyIp.substring(0, finallyIp.length() - 1);
        } else {
            finallyIp = menuItem;
        }
        return finallyIp;
    }

    private static String getRealIp(String iptrue, String type,
                                    Ipconfig ipconfig) {
        Ip_pair[] ip_pairs = ipconfig.getIp_pair();
        String m_ip = "";
        String m_dns = "";
        for (int i = 0; i < ip_pairs.length; i++) {
            Ip[] ip = ip_pairs[i].getIp();
            for (int j = 0; j < ip.length; j++) {
                m_ip = ip[j].getUrl();
                m_dns = ip[j].getDnsname();
                if (m_ip.equals(iptrue) || m_dns.equals(iptrue)) {
                    for (int k = 0; k < ip.length; k++) {
                        if (ip[k].getType().equals(type)) {
                            if (!"".equals(ip[k].getDnsname())) {
                                return ip[k].getDnsname();
                            } else if (!"".equals(ip[k].getUrl())) {
                                return ip[k].getUrl();
                            } else {
                                return "";
                            }
                        }
                    }
                    return "";
                }
            }
        }
        return "";
    }

    /**
     * 提供对url的多ip转码方式
     *
     * @param url
     * @param ipconfig2
     * @param type
     * @return
     */
    public static String ip2DoubleIpFromUrl(String url,
                                            Ipconfig ipconfig2, String type) {
        if (type != null) {

            if (url.startsWith("http")) {
                url = getUrl(ipconfig2, url, type);
            }

        } else {
            log
                    .debug("请正确配置menu_ip.xml文件的default字段，它是判断内外网的ip配置,由于配置错误没有执行ip转换");
            // System.out.println("请正确配置menu_ip.xml文件的default字段,由于配置错误没有执行ip转换");
        }
        return url;
    }
}
