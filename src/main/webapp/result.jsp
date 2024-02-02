<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>成績</title>
    <style>
        .centered {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .button {
            padding: 10px 20px;
            font-size: 18px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="centered">
        <h1>成績</h1>
        <p><%= request.getAttribute("score") %> / 10</p>
        <p>ポイント: <%= request.getAttribute("points") %></p>
        <form action="Home" method="get">
            <input type="submit" value="Homeに戻る" class="button">
        </form>
    </div>
</body>
</html>