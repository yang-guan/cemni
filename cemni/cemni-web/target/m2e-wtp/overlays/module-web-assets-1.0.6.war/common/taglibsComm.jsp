<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
	var s_userid = '<%=WebUtils.getUserId()%>';
	var s_roleTypes = '<%=WebUtils.getRoleType()%>'; 
	var recordId =  '<%=request.getParameter("id")%>';  
	var currentLang = '${lang}'; 
	var initStates = [${initStates}];
	var billState = [${billState}];  
	var billType = '${billType}'; 
	var synStatus = [${synStatus}];

	var s_userCode = '<%= WebUtils.getUserCode()%>';
	var s_userName = '<%= WebUtils.getUserName()%>';
	var s_erpCode = '<%= WebUtils.getErpCode() %>';
	var s_roleNames = '<%= WebUtils.getRoleNames() %>';
</script>

<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/resources/css/ext-all.css?datec=${datec}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/ux/css/q-all.css?datec=${datec}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/ux/css/ux-all.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/icon.css?datec=${datec}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/index.css?datec=${datec}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/ext3/ux/fileuploadfield/css/fileuploadfield.css"/>
 
<script type="text/javascript" src="${ctx}/plugins/locale/lang-${lang}.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ext-all.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/q-all.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/locale.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/fastjson.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/resources/locale/ext-lang-${lang}.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/q-lang-${lang}.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/fileuploadfield/FileUploadField.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/tooltipstextarea.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/tooltipstextfield.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/MultiSelect.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/ItemSelector.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/validateType.js"></script>

<script type="text/javascript" src="${ctx}/plugins/ext3/ux/Spinner.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/ux/SpinnerField.js"></script>
<script type="text/javascript" src="${ctx}/plugins/ext3/DateTimeField.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/plugins/upload/UploadPanel.css?datec=${datec}">
<script type="text/javascript" src="${ctx}/plugins/upload/uploader-all.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/common/splitTabPanel.js?datec=${datec}"></script>
 
<script type="text/javascript" src="${ctx}/js/common/initCommon.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/common/Q.common.SelectWin.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/common/Q.FlowProcessComm.js?datec=${datec}"></script>
<script type="text/javascript" src="${ctx}/js/common/Q.ViewLogItemOrmessageComm.js?datec=${datec}"></script>


