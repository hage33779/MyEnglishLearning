<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            text-align: center;
        }
        .point-display {
            position: absolute;
            top: 20px;
            left: 20px;
            font-size: 20px;
        }
        .button-large, .button-medium, .button-small {
            padding: 10px 20px;
            font-size: 16px;
            margin-top: 20px;
            cursor: pointer;
            outline: none;
            color: black; 
            background-color: white;
            border: 2px solid black;
            border-radius: 10px;
        }
        .button-large:hover, .button-medium:hover, .button-small:hover {
            background-color: #eee;
        }
        .character-container {
            margin: 30px 0; 
            display: block;
        }
        img.character {
            width: 300px;
            height: auto;
            margin: auto; 
        }
        img.food {
            width: 180px;
            height: auto;
            position: absolute;
            margin-left: 210px;
            left: 500px;
    		top: 300px;
        }
        .hidden {
            display: none;
        }
        .reduce-buttons {
            margin-top: 100px; 
        }
    </style>
    <script>
        var characterImages = ["character1.png", "character2.png", "character3.png"];
        var wordImages = ["word1.png", "word2.png", "word3.png"];
        var currentIndex = 0;

        function changeCharacterImage() {
            var imgElement = document.getElementById('characterImage');
            imgElement.src = characterImages[currentIndex];
            currentIndex = (currentIndex + 1) % characterImages.length;
        }

        function changeWordImage(imageName) {
            var wordImgElement = document.getElementById('wordImage');
            wordImgElement.src = imageName;
        }

        window.onload = function() {
            // 30秒ごとにキャラクター画像を変更
            setInterval(changeCharacterImage, 5000);

            document.querySelector('button[name="action"][value="reduce120"]').onclick = function() { changeWordImage('word1.png'); };
            document.querySelector('button[name="action"][value="reduce1200"]').onclick = function() { changeWordImage('word2.png'); };
            document.querySelector('button[name="action"][value="reduce12000"]').onclick = function() { changeWordImage('word3.png'); };
        };
    </script>
</head>
<body>
    <div class="points-display">
        累計ポイント: <%= request.getAttribute("totalPoints") %>
    </div>
    <form action="Learning" method="get">
        <input type="hidden" name="newSession" value="true">
        <button class="button-large" type="submit">学習をスタート</button>
    </form>
    <br>
    <form action="WordList" method="get">
        <button class="button-medium" type="submit">英単語一覧</button>
    </form>
    <div class="character-container">
        <img id="characterImage" src="character1.png" class="character" alt="キャラクター画像">
    </div>
    <div class="character-container">
        <img id="wordImage" src="word1.png" class="food" alt="単語画像">
    </div>
    <form action="Home" method="post" class="reduce-buttons">
        <button class="button-small" name="action" value="reduce120">安価なフード120円</button>
        <button class="button-small" name="action" value="reduce1200">一般的なフード1200円</button>
        <button class="button-small" name="action" value="reduce12000">高級なフード12000円</button>
    </form>
</body>
</html>



 <!--
<html>
<head>
    <title>Home</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
        }
        .button {
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            opacity: 0.8;
        }
        .buttons-container {
            display: flex;
            justify-content: center;
        }
        .points-display {
            margin-top: 20px;
        }
        img.character {
            max-width: 200px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <form action="Learning" method="get">
        <input type="hidden" name="newSession" value="true">
        <button class="button" type="submit">学習をスタート</button>
    </form>
    <form action="WordList" method="get">
        <button class="button" type="submit">英単語一覧</button>
    </form>
    <img src="path/to/character1.img" alt="キャラクター" class="character">
    <div class="buttons-container">
        <button class="button" onclick="alert('ポイントを-120減らします');">ボタン1</button>
        <button class="button" onclick="alert('ポイントを-1200減らします');">ボタン2</button>
        <button class="button" onclick="alert('ポイントを-12000減らします');">ボタン3</button>
    </div>
    <div class="points-display">
        累計ポイント: <%= request.getAttribute("totalPoints") %>
    </div>
</body>
</html>
-->

 <!--
<html>
<head>
    <title>Home</title>
    <style>
        .centered {
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .button-large {
            padding: 15px 25px;
            font-size: 24px;
            text-align: center;
            cursor: pointer;
            outline: none;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 15px;
            box-shadow: 0 9px #999;
        }

        .button-large:hover {background-color: #3e8e41}

        .button-large:active {
            background-color: #3e8e41;
            box-shadow: 0 5px #666;
            transform: translateY(4px);
        }

        .button-medium {
            padding: 10px 20px;
            font-size: 20px;
            text-align: center;
            cursor: pointer;
            outline: none;
            color: #fff;
            background-color: #008CBA;
            border: none;
            border-radius: 10px;
            box-shadow: 0 7px #999;
        }

        .button-medium:hover {background-color: #0076a3}

        .button-medium:active {
            background-color: #0076a3;
            box-shadow: 0 4px #666;
            transform: translateY(4px);
        }
    </style>
</head>
<body>
    <div class="centered">
	<form action="Learning" method="get">
    	<input type="hidden" name="newSession" value="true">
    	<button class="button-large" type="submit">学習をスタート</button>
	</form>
        <br>
        <form action="WordList" method="get">
            <button class="button-medium" type="submit">英単語一覧</button>
        </form>
    </div>
    <div>
        <p>累計ポイント: <%= request.getAttribute("totalPoints") %></p>
    </div>
</body>
</html>
-->