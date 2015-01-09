<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 21.12.2014
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>



<section>
    <div class="b-popup__close"><span class="text">Закрыть</span> <span class="cross">×</span></div>
    <div>


        <form method="post" onsubmit="checkLogin(); return false;" action="/index.php?login=yes">
            <h1>Вход</h1>
            <input type='hidden' name='backurl' value='/index.php' />
            <input type="hidden" name="AUTH_FORM" value="Y" />
            <input type="hidden" name="TYPE" value="AUTH" />


            <form action="do/login" method="post">

                <input type="text" name="login" value="" /><br/>
                <input type="text" name="password" value="" /><br/>
                <input type="submit" value="Нажать" /><br/>
            </form>

            <div class="b-form_line">
                <label class="title" for="enter_email">Логин</label>
                <span class="input"><input type="text" id="enter_email" name="USER_LOGIN" maxlength="50" value="" /></span>
            </div>

            <div class="b-form_line">
                <label class="title" for="enter_password">Пароль</label>
                <span class="input"><input type="password" id="enter_password" name="USER_PASSWORD" maxlength="50" /></span>
            </div>

            <div class="b-form_line remember">
                <div class="b-form_checkbox">
                    <input type="checkbox" id="enter_remember" name="USER_REMEMBER" value="Y" checked="checked" />
                    <label for="enter_remember">Запомнить меня</label>
                </div>
            </div>

            <div id="loginErrorDiv" class="b-form_line error-message" style="display: none;">
                <b>Неверная комбинация логина и пароля</b> Проверьте состояние клавиши Caps Lock и текущую языковую раскладку
            </div>

            <div class="b-form_line button">
                <i id="loginPreloader" class="b-ico b-ico_preloader_30x30" style="display: none;"></i>
                <a id="loginButton" href="#" class="b-button submit" style="display: inline-block;">Войти</a>
                <a rel="nofollow" class="lnk" href="/auth/?forgot_password=yes&amp;backurl=%2Findex.php">Забыли свой пароль?</a>
            </div>


        </form>
    </div>
    <div class="social_reg">
    </div>
</section>


</body>
</html>
