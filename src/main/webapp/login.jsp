<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="spring_security_check" method="post">

		<input type="text" name="username" placeholder=用户名 maxlength="11"
			style="border-radius: 4px; outline: none;; width: 300px; height: 35px; text-indent: 10px" /><br />
		<input type="password" name="password" placeholder=密码 maxlength="11"
			style="border-radius: 4px; outline: none;; width: 300px; height: 35px; text-indent: 10px" /><br />
		<input id="_spring_security_remember_me"
			name="_spring_security_remember_me" type="checkbox" value="true" /><br />
		<input type="submit"
			style="width: 305px; height: 45px; background-color: orange"
			value="登录" οnmοusemοve="this.style.backgroundColor='darkorange';"
			οnmοuseοut="this.style.backgroundColor='orange   ';" />

	</form>
</body>
</html>