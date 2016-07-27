<%@ page import="cn.judian.dao.Common" %>
<%@ page import="cn.judian.model.Project" %>
<%@ page import="cn.judian.util.RandomUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    int id = (int) request.getAttribute("id");
    Project project = Common.getProject(id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><%=Common.getTitle(id)%>
    </title>
    <meta name="keywords" content="<%=project.getName()%>">
    <meta name="description" content="<%=project.getDescription()%>">
    <link href="http://jd.360cig.com/Public/Home/public/common.css" rel="stylesheet" type="text/css" />
    <link href="http://jd.360cig.com/Public/Home/public/commonstyle.css" rel="stylesheet" type="text/css" />
    <base href="${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="<%=project.getAdjs()%>"></script>
</head>
<body>
<div class="wrap">
    <div class="sub-head lev3-head"><span
            class="lev3-on c50"><a href="${pageContext.request.contextPath}/">首页</a><span> &gt; </span><a
            href="${pageContext.request.contextPath}/<%=RandomUtil.getStringRandom(5)%>/<%=RandomUtil.getStringRandom(5)%>/list<%=project.getId()%>"><%=project.getName()%></a> ></span>
    </div>
    <p class="clear"></p>
    <div class="sub-left">
        <div>
            <div class="list f14">
                <ul class="main-list">
                    <%=Common.getListHrefTitle(id, 100)%>
                </ul>
            </div>
            <p class="clear"></p>

        </div>
        <p class="clear"></p>
        <div class="pagebox">
            <div class="aa">
                <div class="page">
                    <a href="<%=RandomUtil.getStringRandom(5)%>/<%=RandomUtil.getStringRandom(5)%>/list<%=project.getId()%>/" class="a1">上一页</a> <span>1</span>
                    <%=Common.getPage(id, 10)%>
                    <a href="<%=RandomUtil.getStringRandom(5)%>/<%=RandomUtil.getStringRandom(5)%>/list<%=project.getId()%>/" class="a1">下一页</a>
                    <p class="clear"></p>
                </div>
            </div>
        </div>
    </div>
    <div class="sub-right lev3-right">
        <div class="sub-tit mt10"><h3>推荐阅读</h3></div>
        <div class="list bgf9 f14 c50">
            <ul>
                <%=Common.getContentHrefTitle(project.getId(), 10)%>
            </ul>
        </div>
        <div class="sub-tit mt10"><h3>热度排行</h3></div>
        <ul class="hot-list">
            <%=Common.getContentHrefTitle(project.getId(), 10)%>
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





