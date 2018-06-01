<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibsComm.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="application.title"/></title>
<script>
var vp;
var power = eval(${permissions}); //权限
Ext.onReady(function(){ 
	Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif";
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	vp = new (eval("Q.comm.CommModelVpList"))(cfg);
	vp.fireEvent("vpInstanceAfert");
})

</script>

<script type="text/javascript" src="${ctx}/plugins/ext3/ux/q-model.js?datec=${datec}"></script>
 <!-- <script type="text/javascript" src="${ctx}/js/common/Q.comm.CommModelEditWin.js?datec=${datec}"></script> 
<script type="text/javascript" src="${ctx}/js/common/Q.comm.CommModelVpList.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/common/Q.comm.CommSearchWin.js?datec=${datec}"></script>-->
<c:forEach items="${jsPath}" var="item">
	<script type="text/javascript" src="${ctx}${item}"></script> 
</c:forEach>
</head>
<body>
</body>
</html>