<%--
  Created by IntelliJ IDEA.
  User: minsoo
  Date: 11/27/23
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css"/>
    <style>
        table {
            width: 800px;
            border-collapse: collapse;
            border: 1px #ccc solid;
        }

        table tr > td, th {
            padding: 5px;
            border: 1px #ccc solid;
        }
    </style>
</head>

<body>
<h1>학생 리스트</h1>
<!-- todo register.do 변경 -->
<p><a href="/student/register.do">학생(등록)</a></p>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <!--todo list 구현하기 c:foreach -->
    <c:forEach var="item" items="${studentList}">
        <tr>
            <td>${item.id}</td>
            <td style="text-align: center">${item.name}</td>
            <td style="text-align: center">${item.gender}</td>
            <td style="text-align: center">${item.age}</td>
            <td style="text-align: center">
                <c:url var="link" value="/student/view.do" scope="request">
                    <c:param name="id" value="${item.id}"/>
                </c:url>
                <a href="${link}">조회</a>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>