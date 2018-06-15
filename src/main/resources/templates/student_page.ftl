<#ftl encoding='UTF-8'>
<#import "spring.ftl" as spring />
<@spring.bind "model" />
<!DOCTYPE html>
<html>
<head>
    <title>Student page</title>
    <#include "/common/responsive_sidenav.ftl">
    <link rel="stylesheet" type="text/css" media="all" href="/css/user_profile_styles.css">
    <script type="text/javascript" src="/js/user_profile_script.min.js"></script>
</head>
<body>

<ul class="sidenav">
    <li><a class="active" href="/student/profile">Профиль</a></li>
    <li><a href="#home">Список студентов</a></li>
    <li><a href="#contact">Список олимпиад</a></li>
    <li><a href="#about">About</a></li>
    <li><a href="/logout">Logout</a></li>
</ul>
<div class="content">
    <#if model.user??>
        <div id="Home" class="tabcontent">
            <h3>Home</h3>
            <p>Home is where the heart is..</p>
        </div>

        <div id="News" class="tabcontent">
            <h3>News</h3>
            <p>Some news this fine day!</p>
        </div>

        <div id="Contact" class="tabcontent">
            <h3>Contact</h3>
            <p>Get in touch, or swing by for a cup of coffee.</p>
        </div>

        <div id="About" class="tabcontent">
            <h3>About</h3>
            <p>Who we are and what we do.</p>
        </div>

        <button class="tablink" onclick="openPage('Home', this, 'red')">Home</button>
        <button class="tablink" onclick="openPage('News', this, 'green')" id="defaultOpen">News</button>
        <button class="tablink" onclick="openPage('Contact', this, 'blue')">Contact</button>
        <button class="tablink" onclick="openPage('About', this, 'orange')">About</button>
    </#if>
</div>
</body>
</html>
