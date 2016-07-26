<%@ page import="cn.judian.dao.Home" %>
<%@ page import="cn.judian.dao.Common" %>
<%--
  Created by IntelliJ IDEA.
  User: zhangruimin
  Date: 16/7/13
  Time: 下午4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>饮食健康社区</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="http://jd.360cig.com/Public/Home/public/common.css" rel="stylesheet" type="text/css" />
    <link href="http://jd.360cig.com/Public/Home/public/commonstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="wrap">
    <div class="navbox_home">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td align="left" valign="top"><div class="nav c50">
                    <dl>
                        <dt><a href="<%=Common.getHref()%>" target="_blank">新闻</a> </dt>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">事件</a></dd>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">卫生</a></dd>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">评论</a></dd>
                    </dl>
                    <dl>
                        <dt><a href="<%=Common.getHref()%>" target="_blank">动画</a> </dt>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">经济</a></dd>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">培训</a></dd>
                    </dl>
                    <dl>
                        <dt><a href="<%=Common.getHref()%>" target="_blank">中国一点不能少</a></dt>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">寸土必争</a></dd>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">南海观音</a></dd>
                        <dd><a href="<%=Common.getHref()%>" target="_blank">齐天大圣</a></dd>
                    </dl>
                </div></td>
            </tr>
        </table>
        <div style="display: none">
            <ul>
                <%=Home.getProjectHrefList()%>
            </ul>
        </div>
    </div>
    <div class="main-left">
        <div class="main-col-3 fl pdr10 br1">
            <div class="tit2"><span><a href="<%=Common.getHref()%>">心情</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/xinqing.jpg" width="160" height="120">
                    </td>

                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/xinqing/", "xinqing", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/xinqing/", "xinqing", 5)%>
                </ul>
            </div>
            <div class="tit2"><span><a href="<%=Common.getHref()%>">饮食</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/yinshi.jpg" width="160" height="120">

                    </td>
                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/yinshi/", "yinshi", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/yinshi/", "yinshi", 5)%>
                </ul>
            </div>
            <div class="tit2"><span><a href="<%=Common.getHref()%>">运动</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/yundong.jpg" width="160" height="120">
                    </td>
                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/yundong/", "yundong", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/yundong/", "yundong", 5)%>
                </ul>
            </div>
        </div>
        <div class="main-col-3 fr">
            <div class="tit2"><span><a href="<%=Common.getHref()%>">减肥</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/jianfei.jpg" width="160" height="120">
                    </td>
                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/jianfei/", "jianfei", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/jianfei/", "jianfei", 5)%>
                </ul>
            </div>
            <div class="tit2"><span><a href="<%=Common.getHref()%>">整形</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/zhengxing.jpg" width="160" height="120">
                    </td>
                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/zhengxing/", "zhengxing", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/zhengxing/", "zhengxing", 5)%>
                </ul>
            </div>
            <div class="tit2"><span><a href="<%=Common.getHref()%>">公益</a></span></div>
            <table width="340" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="47%" align="left" valign="middle">
                        <img src="http://jd.360cig.com/Public/Home/public/gongyi.jpg" width="160" height="120">
                    </td>
                    <td width="53%" align="left" valign="middle"><div class="list">
                        <ul class="blue">
                            <%=Home.getPart("news/gongyi/", "gongyi", 5)%>
                        </ul>
                    </div></td>
                </tr>
            </table>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("news/gongyi/", "gongyi", 5)%>
                </ul>
            </div>
        </div>
    </div>
    <div class="main-right">
        <div class="title">
            <h3><a href="<%=Common.getHref()%>">热门</a></h3>
        </div>
        <div class="list">
            <ul>
                <%=Home.getRandomPart("news/gongyi/", "gongyi", 5)%>
            </ul>
        </div>

        <div class="title">
            <h3><a href="<%=Common.getHref()%>">推荐</a></h3>
        </div>
        <div class="list">
            <ul>
                <%=Home.getRandomPart("news/jianfei/", "jianfei", 5)%>
            </ul>
        </div>

        <div class="title">
            <h3><a href="<%=Common.getHref()%>">活动</a></h3>
        </div>
        <div class="list">
            <ul>
                <%=Home.getRandomPart("news/yinshi/", "yinshi", 5)%>
            </ul>
        </div>

        <div class="title">
            <h3><a href="<%=Common.getHref()%>">美景</a></h3>
        </div>
        <div class="list">
            <ul>
                <%=Home.getRandomPart("news/gongyi/", "gongyi", 5)%>
            </ul>
        </div>

        <div class="title">
            <h3><a href="<%=Common.getHref()%>">社区</a></h3>
        </div>
        <div class="list">
            <ul>
                <%=Home.getRandomPart("news/yundong/","yundong", 5)%>
            </ul>
        </div>
    </div>
    <div class="flink mt10 c50">
        <div class="slideTxtBox5">
            <div class="hd">

            </div>
        </div>
    </div>
    <div class="footer"><a href="#">关于我们</a> | <a href="#">网站声明</a> | <a href="#">报社活动</a> |<a href="#"  target="_blank"  >联系我们</a> | <a href="map.html"  target="_blank" >网站地图</a></div>
    <div class="copyright">Copyright(C) 1999-2014 版权所有 违者必究<br/>
    </div>
</div>
</body>
</html>


