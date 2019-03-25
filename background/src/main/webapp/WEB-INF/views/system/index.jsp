<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
</head>
<frameset rows="60px,*" border="none">
	<frame src="${webRoot }/header" noresize="noresize" scrolling="no"/>
	<frameset cols="160px,*">
		<frame src="${webRoot }/left" name="left" noresize="noresize" scrolling="no"/>
		<frame src="${webRoot }/main" name="main"/>
	</frameset>
</frameset>

</html>