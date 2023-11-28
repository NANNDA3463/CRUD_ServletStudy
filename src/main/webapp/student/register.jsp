<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: minsoo
  Date: 11/27/23
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>student - register</title>
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

<c:choose>
    <c:when test="${empty student}">
        <c:url value="/student/register.do" var="action"/>
    </c:when>
    <c:otherwise>
        <c:url value="/student/update.do" var="action"/>
    </c:otherwise>
</c:choose>


<form method="post" action="${action}">
    <table>
        <tbody>
        <tr style="text-align: center">
            <td style="font-weight: bold">ID</td>
            <td style="text-align: left">
                <c:choose>
                    <c:when test="${empty student}">
                        <label>
                            <input name="id" type="text" width="500" value="${student.id}" required/>
                        </label>
                    </c:when>
                    <c:otherwise>
                        <label>
                            <input name="id" type="text" width="500" value="${student.id}" readonly/>
                        </label>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr style="text-align: center">
            <td style="font-weight: bold">이름</td>
            <td style="text-align: left">
                <label>
                    <input name="name" type="text" width="500" value="${student.name}" required/>
                </label>
            </td>
        </tr>
        <tr style="text-align: center">
            <td style="font-weight: bold">성별</td>
            <td style="text-align: left">
                <label>
                    <input name="gender" type="radio" value="M" ${student.gender eq 'M' ? 'checked' : '' } />
                    남
                </label>
                <label>
                    <input name="gender" type="radio" value="F"  ${student.gender eq 'F' ? 'checked' : '' }>
                    여
                </label>
            </td>
        </tr>
        <tr style="text-align: center">
            <td style="font-weight: bold">나이</td>
            <td style="text-align: left">
                <input name="age" type="number" width="500"
                       value="${Integer.valueOf(student.age)} "/>
            </td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>
