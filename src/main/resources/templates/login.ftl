<#ftl encoding='UTF-8'>
<#import "spring.ftl" as spring />
<@spring.bind "model" />
<!DOCTYPE>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<div>
    <form method="POST" action="">
    <#if model.error.isPresent()>
        <div role="alert">Логин или пароль введены неверно</div>
    </#if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div>
            <label for="username">Логин</label>
            <div>
                <input type="text" name="username" id="username" placeholder="Логин" required="required">
            </div>
        </div>
        <div>
            <label for="password">Пароль</label>
            <div>
                <input type="password" id="password" name="password" placeholder="Пароль" required="required">
            </div>
        </div>
        <div>
            <div>
                <button type="submit">Войти</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>