<%@ page import="cn.judian.dao.Common" %>
<%@ page import="cn.judian.dao.Home" %>
<%--
  Created by IntelliJ IDEA.
  User: zhangruimin
  Date: 16/7/13
  Time: 下午4:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String type = (String) request.getAttribute("type");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${content.title}</title>
    <meta name="keywords" content="${content.title}">
    <meta name="description" content="">
    <link href="http://jd.360cig.com/Public/Home/public/common.css" rel="stylesheet" type="text/css" />
    <link href="http://jd.360cig.com/Public/Home/public/commonstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="wrap">
    <div class="sub-head lev3-head"><span
            class="lev3-on c50"><a href="${pageContext.request.contextPath}">首页</a><span> &gt; </span><a
            href="${pageContext.request.contextPath}">健康生活</a> > 正文</span>
        <div class="soRight">
            <form action="<%=Common.getHref()%>" method="get" target="_blank">
                <input type="hidden" name="m" value="search"/>
                <input type="hidden" name="c" value="index"/>
                <input type="hidden" name="a" value="init"/>
                <input type="hidden" name="typeid" value="1" id="typeid"/>
                <input type="hidden" name="siteid" value="1" id="siteid"/>
            </form>


        </div>
    </div>
    <p class="clear"></p>
    <div class="sub-left lev3-left">
        <div class="P_01"></div>
        <h1 class="news-tit">${content.title}</h1>
        <div class="P_02"></div>
        <div class="news-from"><span class="biaoqian"><%=Common.getDate("yyyy-MM-dd HH:mm:ss")%></span>
        </div>
        <div class="new-cont" id="fbox">
            　　原标题：${content.title}<br/>
            ${content.article}
            <p class="clear"></p></div>
        <p class="clear"></p>
        <div class="rel">
            <h3> 相关阅读:</h3>
            <div class="list f14">
                <ul>
                    <%=Home.getRandomPart("",type, 5)%>
                </ul>
            </div>
        </div>
    </div>
    <div class="sub-right lev3-right">
        <div class="sub-tit mt10"><h3>推荐阅读</h3></div>
        <div class="list bgf9 f14 c50">
            <ul>
                <%=Home.getRandomPart("",type, 5)%>
            </ul>
        </div>
        <div class="sub-tit mt10"><h3>热度排行</h3></div>
        <ul class="hot-list">
            <%=Home.getPart("",type, 5)%>
        </ul>
    </div>
    <p class="clear"></p>
    <div class="footer"><a href="#">关于我们</a> | <a
            href="#">网站声明</a>
        |<a href="#" target="_blank">联系我们</a> | <a
                href="#" target="_blank">网站地图</a></div>
    <div class="copyright">Copyright(C) 1999-2014 版权所有 违者必究<br/>
    </div>
</div>
</body>
</html>





