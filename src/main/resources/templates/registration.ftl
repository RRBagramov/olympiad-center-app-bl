<#ftl encoding='UTF-8'>
<#import "spring.ftl" as spring />
<@spring.bind "model" />
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="<@spring.url '/assets/js/ie/html5shiv.js' />"></script>
    <script src="<@spring.url '/assets/js/bootstrap.bundle.js'/>"></script>
    <script src="<@spring.url '/assets/js/bootstrap.js'/>"></script>
    <script src="<@spring.url '/assets/js/main.js'/>"></script>
    <script src="<@spring.url '/assets/js/util.js'/>"></script>
    <script src="<@spring.url '/assets/js/action.js'/>"></script>
    <script src="<@spring.url '/assets/js/jquery.min.js'/>"></script>
    <script src="<@spring.url '/assets/js/jquery.scrolly.min.js'/>"></script>
    <link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap.min.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/assets/css/bootstrap-theme.min.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/assets/css/ie8.css'/>"/>
    <link rel="stylesheet" href="<@spring.url '/assets/css/ie9.css'/>"/>
    <![endif]-->
    <link rel="stylesheet" href="<@spring.url '/css/reg_style.css'/>" media="screen" type="text/css"/>
</head>
<body>
<div id="registration">
    <form method="post" action="/registration">
        <fieldset class="clearfix">
            <#if model.badLogin??>
                <div role="alert">${model.badLogin}</div>
            </#if>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p>
                <span class="fontawesome-user"></span>
                <input type="text" name="username" id="username" placeholder="Логин"
                       required="required">
            </p>

            <p>
                <span class="fontawesome-lock"></span>
                <input type="password" id="password" name="password" placeholder="Пароль"
                       required="required">
            </p>
            <#if model.badPassword??>
                <div role="alert">${model.badPassword}</div>
            </#if>
            <p>
                <span class="fontawesome-lock"></span>
                <input type="password" id="repassword" name="repassword" placeholder="Повторите пароль"
                       required="required">
            </p>

            <p>
                <span>Имя</span>
                <input type="text" id="firstName" name="firstName" placeholder="Имя"
                       required="required">
            </p>

            <p>
                <span>Фамилия</span>
                <input type="text" id="lastName" name="lastName" placeholder="Фамилия"
                       required="required">
            </p>

            <p>
                <span>Отчество</span>
                <input type="text" id="middleName" name="middleName" placeholder="Отчество"
                       required="required">
            </p>

            <p>
                <input type="submit" value="Регистрация">
            </p>
        </fieldset>
    </form>
</div>
</body>
</html>