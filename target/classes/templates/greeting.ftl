<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <div class="jumbotron bg-light text-center">
        <h1 class="display-2 ">Hello, ${name}. This is my SecurityApi!</h1>
        <img class="mx-auto justify-content-center d-flex" src="https://www.prlog.org/12665991-ilocker-app-icon.jpg"
             width="150" alt="online store png">

        <hr color="lightblue">
        <h3>
            <a href="/login">Login</a> || <a href="/registration">Register</a>
        </h3>
    </div>
</@c.page>
