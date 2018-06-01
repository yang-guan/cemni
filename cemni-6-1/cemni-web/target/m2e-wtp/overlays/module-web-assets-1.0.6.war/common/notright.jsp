<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head><title>STATUS 401</title>
<meta http-equiv=content-type content="text/html; charset=utf-8">
<meta http-equiv=content-language content=utf-8>
<meta content="mshtml 6.00.2900.3314" name=generator>
<style type="text/css">
<!--
	body {
		background: #f1f6f8; font: 12px/2.4em arial, simsun, mingliu, arial, helvetica
	}
	.main {
		left: 50%; margin: -163px 0px 0px -240px; width: 480px; position: absolute; top: 50%; height: 326px
	}
	.content {
		background: url(${ctx}/images/bg_notright.jpg) no-repeat; width: 480px; height: 298px;
	}
	.copyright {
		color: #98bcca; text-align: center
	}
	.errormsg {
		left: 90px; top: 30px; width: 300px; position: relative; text-align: center; color: red;
	}
	.tip1 {
		left: 90px; top: 240px; top: 210px\9; width: 300px; position: relative; text-align: center;
	}
	.c1 { margin-left: 10px; color: blue; }
-->
</style>
</head>
<body>
    <div class="main">
        <div class="content">
            <div class="errormsg" style="font-size:20px"></div>
            <div class="tip1"><a href="javascript:;" onclick="history.length > 0 ? parent.history.back() : parent.location.href='${ctx_core}/login.jsp'; return false;"><s:text name="button.return"/></a> | <a href="javascript:;" onclick="parent.location.href='${ctx_core}/login.jsp';return false;"><s:text name="button.login"/></a></div>
        </div>
    </div>
</body>
</html>
