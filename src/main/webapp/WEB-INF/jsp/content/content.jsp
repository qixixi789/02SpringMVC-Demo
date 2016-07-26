<%@ page import="cn.judian.dao.Common" %>
<%@ page import="cn.judian.model.Content" %>
<%@ page import="cn.judian.util.RandomUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
  Content content = (Content)request.getAttribute("content");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${content.title}<%=Common.getTitle(content.getId())%>${content.keyWords}</title>
  <meta name="keywords" content="${content.title}" />
  <meta name="desCrIPtion" content="${content.desc}" />
  <meta http-equiv="Cache-Control" content="no-transform" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
  <%--<script type="text/javascript" src="${content.adjs}"></script>--%>
</head>
<body>
<div class="hdat bdb mb10">
  <div class="hdat">
    <a href="${pageContext.request.contextPath}/" target="_top">首页</a>&nbsp;&gt;&gt;&nbsp;<a
          href="${pageContext.request.contextPath}/<%=RandomUtil.getStringRandom(5)%>/<%=RandomUtil.getStringRandom(5)%>/list${content.id}" target="_top">新闻</a>&nbsp;&gt;&gt;&nbsp;正文
  </div>
</div>
<div class="HCT mt0">
  <div style="width: 680px; float: left;">
    <div class="hclc mb8">
      <div class="rct">
        <div></div>
      </div>
      <div class="hclc10">
        <div class="va">
          <h1 class=" BSHARE_POP">${content.title}</h1>
          <p class="pd10 fs12" style="color: #4c4c4c;">
            <span class="time" id="pubtime_baidu">
              <%=Common.getDate("yyyy-MM-dd HH:mm:ss")%>
            </span> <span
                  id="source_baidu">来源：兰州晨报</span> <span id="author_baidu"></span>
          </p>
        </div>
        <div class="vb BSHARE_POP article-content" id="pzoom"
             style="padding: 0 10px 10px;">
          <p style="text-indent: 2em">${content.title}</p>
          <br>
          <%=Common.getContent()%>
        </div>
        <div>
          <ul>
            <%=Common.getContentHrefTitle(content.getId(), 10)%>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>