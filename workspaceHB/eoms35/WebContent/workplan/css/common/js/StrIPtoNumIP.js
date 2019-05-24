/*
========================================================================
Copyright (c) 2002,亿阳信通网络事业部IP网管
All rights reserved.
Filename ：StrIPtoNumIP.js
Abstract ：标准IP转换为数字IP
Version  ：1.0
Author   ：Liu Guoyuan
finished date ：2002-09-30
Last Modify ：2002-09-30
========================================================================
*/

function hex2dec(i_hex)
{
//十六进制转换十进制
	i_dec=parseInt("0x"+i_hex);
	return i_dec;
}
function dec2hex(i_dec)
{
//十进制转换十六进制
	i_hex=parseInt(i_dec);
	i_hex=i_hex.toString(16);
	if (i_hex.length==1){i_hex="0"+i_hex}
	return i_hex;
}

function StrIPtoNumIP(ip)
{
//IP地址转换为数字
	var result="";
	var ip=ip;
	if (ip=="")
	{
		result=0;
	}
	else
	{
		if(ip=="255.255.255.255")
		{
			result=-1;
		}
		else
		{
			strIP=new Array(4);
			strIP=ip.split(".");
			for (i=0;i<strIP.length;i++)
			{
				strIP[i]=dec2hex(strIP[i]);
				result=result+strIP[i]+"";
			}
			result=hex2dec(result);
		}
	}
	return result;
}