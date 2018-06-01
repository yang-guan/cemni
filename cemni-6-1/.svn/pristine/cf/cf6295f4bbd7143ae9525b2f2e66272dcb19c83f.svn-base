<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/taglibsComm.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/resources/css/xtheme-blue.css?datec=${datec}" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/ux/css/ColumnHeaderGroup.css?datec=${datec}" />
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/ColumnHeaderGroup.js?datec=${datec}" charset="utf-8"></script>

<style type="text/css">
td.ux-grid-hd-group-cell .x-grid3-hd-checker {
	display: none;
}

td.ux-grid-hd-group-cell .x-grid3-hd-column {
	width: 500;
}
</style>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>千年珠宝CRM系统</title>
<script type="text/javascript" src="${ctx}/js/common/Q.common.js?datec=${datec}"></script>
<script type="text/javascript">
var vp;
var power = eval(${permissions}); // 权限
Ext.onReady(function() {
    Ext.BLANK_IMAGE_URL = "${ctx}/plugins/ext3/resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    vp = new (eval("Q.comm.CommModelVpList"))(cfg);
    vp.fireEvent("vpInstanceAfert");
});
</script>

<script type="text/javascript" src="${ctx}/plugins/ext3/ux/q-model.js?datec=${datec}"></script>
<c:forEach items="${jsPath}" var="item">
	<script type="text/javascript" src="${ctx}${item}"></script>
</c:forEach>
</head>
<body>
</body>
</html>