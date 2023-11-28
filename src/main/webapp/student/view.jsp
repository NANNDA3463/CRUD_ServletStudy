<%@ page import="com.nhnacademy.crud.Student" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: minsoo
  Date: 11/27/23
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>학생-조회</title>
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
<table>
    <tbody>
    <tr style="text-align: center">
        <td style="font-weight: bold">ID</td>
        <td style="text-align: left">
            <c:out value="${student.id}"/>
        </td>
    </tr>
    <tr style="text-align: center">
        <td style="font-weight: bold">이름</td>
        <td style="text-align: left">
            <c:out value="${student.name}"/>
        </td>
    </tr>
    <tr style="text-align: center">
        <td style="font-weight: bold">성별</td>
        <td style="text-align: left">
            <c:out value="${student.gender}"/>

        </td>
    </tr>
    <tr style="text-align: center">
        <td style="font-weight: bold">나이</td>
        <td style="text-align: left">
            <c:out value="${student.age}"/>

        </td>
    </tr>
    <tr style="text-align: center">
        <td style="font-weight: bold">등록일</td>
        <td style="text-align: left">
            <%--            <fmt:formatDate value="${requestScope.student.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
            <%=
            ((Student) request.getAttribute("student")).getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            %>

        </td>
    </tr>

    </tbody>
</table>
<ul>
    <li><a href="/student/list.do">리스트</a></li>
    <li>
        <c:url value="/student/update.do" var="update_link">
            <c:param name="id" value="${student.id}"/>
        </c:url>
        <a href="${update_link}">수정</a>
    </li>
    <li>
        <!-- todo 삭제버튼 구현, method=post-->
        <form method="POST" action="/student/delete.do">
            <input type="text" name="id" hidden="hidden" value="${student.id}">
            <input type="submit" value="삭제">
        </form>
    </li>

</ul>

</body>
</html>