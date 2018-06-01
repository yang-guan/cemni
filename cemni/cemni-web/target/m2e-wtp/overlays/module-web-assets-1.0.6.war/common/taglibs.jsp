<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.huiju.module.context.Constants"%>
<%@page import="com.huiju.module.web.util.WebUtils"%>
<%
    pageContext.setAttribute("datec", "2015101001");
    pageContext.setAttribute("lang", request.getLocale());

    String root = WebUtils.getRootPath();
    
    String context = request.getContextPath();
    String coreRoot = (root.indexOf(context) > 0) ? root.substring(0, root.indexOf(context)) : root; 
    
    pageContext.setAttribute("ctx", root);
    pageContext.setAttribute("ctx_core", coreRoot);
    
%>
<script type="text/javascript">
	var path = '${ctx}';
	var path_core = '${ctx_core}';
	var lang = '${lang}'
</script>
