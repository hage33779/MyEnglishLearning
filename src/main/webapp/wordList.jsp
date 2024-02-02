<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myEnglishLearning.Word" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Word List</title>
    <style>
        .centered {
            text-align: center;
            margin-top: 50px;
        }
        table {
            margin-left: auto;
            margin-right: auto;
        }
        th, td {
            padding: 10px;
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <div class="centered">
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
        <h2>一覧</h2>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>explanation</th>
                <th>delete</th>
            </tr>
            <% 
                List<Word> words = (List<Word>) request.getAttribute("words");
                for (Word word : words) {
            %>
            <tr>
                <td><%= word.getId() %></td>
                <td><%= word.getName() %></td>
                <td><%= word.getExplanation() %></td>
                <td>
                    <form action="WordList" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="<%= word.getId() %>">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
        <h2>登録</h2>
        <form action="WordList" method="post" accept-charset="UTF-8">
            <input type="hidden" name="action" value="register">
            <table>
                <tr>
                    <td><input type="text" name="name" placeholder="単語"></td>
                    <td><input type="text" name="explanation" placeholder="日本語訳"></td>
                    <td><input type="submit" value="登録"></td>
                </tr>
            </table>
        </form>
    </div>
	<div style="text-align: center; margin-top: 20px;">
    	<a href="Home" style="text-decoration: none; color: white; background-color: #007bff; padding: 10px 20px; border-radius: 5px; font-weight: bold;">Homeに戻る</a>
	</div>
</body>
</html>