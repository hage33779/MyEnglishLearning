<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myEnglishLearning.Word" %>
<!DOCTYPE html>
<html>
<head>
    <title>Learning</title>
    <style>
        .centered {
            text-align: center;
            position: absolute;
            top: 30%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .answer-field {
            font-size: 24px;
            text-align: center;
            margin: 20px;
            margin-bottom: 40px;
        }
        #exerciseImage {
            width: 200px;
            height: auto;
            margin-bottom: 20px;
        }
    </style>
    <script>
        function switchImage() {
            var input = document.getElementById('userAnswer').value.length;
            var imagePath = input % 2 === 0 ? 'muscle1.png' : 'muscle2.png';
            document.getElementById('exerciseImage').src = imagePath;
        }
        // ページが読み込まれたら解答入力フォームにフォーカスを当てる
        function focusOnInput() {
            document.getElementById('userAnswer').focus();
        }
    </script>
</head>
<body onload="focusOnInput()">
    <div class="centered">
        <h1>問題</h1>
        <p><%= ((Word) request.getSession().getAttribute("currentWord")).getExplanation() %></p>
        <img id="exerciseImage" src="muscle1.jpg" alt="Exercise Image"/>
        <form action="Learning" method="post">
            <div class="answer-field">
                <input type="text" id="userAnswer" name="userAnswer" placeholder="解答入力" onkeyup="switchImage()">
            </div>
            <input type="submit" value="送信">
        </form>
    </div>
</body>
</html>
