<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.judian.dao.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <meta http-equiv="Cache-Control" content="no-transform">
    <meta name="viewport" content="width=device-width, maximum-scale=1.0, user-scalable=no" />
    <title>名医养生</title>
    <link href="http://jd.360cig.com/Public/Home/css/css.css" rel="stylesheet" type="text/css">
    <meta name="desCrIPtion" content="" />
    <style type="text/css" media="screen">
        .tech.midBox{width:33%;margin: 10px 0;}
        .navCon{margin: 10px 100px;}
        .box-object{width: 30%;}
        .o_content>ul>li{ width:320px; padding-left:10px; float:left;}
    </style>
</head>
<body>
<div class="wrap">
    <!-- 导航功能区 begin-->
    <div class="navTop">
        <!-- 广告位：首页 全屏 -->
        <div class="navTopCon">
            <span class="phone">手机端</span>
            <span class="app">APP客户端</span>
            <em>/</em>
            <a href="#" class="linkHome" target="_self">设为首页</a>
            <a href="#" class="linkFav" target="_self" id="ui-markIndex">收藏本页</a>
            <a href="#" class="linkTopNews">滚动新闻</a>
            <span class="resLink" id="ui-login"></span>
        </div>
    </div>
    <!-- 导航功能区 end-->
    <!-- 导航 begin-->
    <div class="nav">
        <div class="navCon">
            <a href="#" target="_self"><div class="logoBox"></div></a>
            <div class="navMain">
                <ul>
                    <li class="pA">
                        <dl>
                            <dt><strong>健康</strong>    寻医  大夫  问答</dt>
                            <dt><strong>笃初</strong>    诚美  荣业  籍甚</dt>
                        </dl>
                    </li>
                    <li class="pB">
                        <dl>
                            <dt><strong>存以</strong>    甘棠  孔怀  兄弟</dt>
                            <dt><strong>交友</strong>    投分  社会  图片</dt>
                        </dl>
                    </li>
                    <li class="pC">
                        <dl>
                            <dt><strong>都邑</strong>    华夏  楼观  飞惊</dt>
                            <dt><strong>策功</strong>    茂实  勒碑  刻铭 </dt>
                        </dl>
                    </li>
                    <li class="pD">
                        <dl>
                            <dt><strong>九州</strong>    岳宗  泰岱   禹迹</dt>
                            <dt><strong>富强</strong>    勤俭  民主  创新</dt>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 导航 end-->
    <!-- 内容外侧 begin-->
    <div class="wrapBg">
        <!-- 内容部分 begin-->
        <div class="wrapCon">
            <!-- 第一部分 begin-->
            <div class="conFir">
                <!-- 左侧-第一部分 begin-->
                <div class="o_content">
                    <ul class="iconBoxT14">
                        <%=Common.getProjectData(10)%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- 尾巴 begin -->
    <div class="footNew">
        <div class="footOutNew">
        </div>
        <!-- 版权区 begin -->
        <div class="copyArea">
            <div class="copyLink">
                <ul>
                    <li class="copyLogo">
                        <a rel="nofollow" class="logoCa" title="经营性网站备案信息" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoCb" title="中国互联网违法和不良信息举报中心" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoCc" title="中国文明网传播文明" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoCd" title="网络110报警服务" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoCe" title="北京文化市场举报热线" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoCX" title="诚信网站" target="_blank" href="#"></a>
                        <a rel="nofollow" class="logoKx" title="可信网站" target="_blank" href="#"></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 版权区 end -->
    </div>
    <!-- 尾巴 end -->
</div>
</body>
</html>