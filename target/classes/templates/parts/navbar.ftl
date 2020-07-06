<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="/main">
        <img src="https://www.epicit.com.au/wp-content/uploads/icon-data-breach-security.svg" width="30" height="30" alt="">
        SecurityApi</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">

            <#if !isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/todo">Todo list</a>
                </li>
            </#if>

            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User List</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-4">${name}</div>
        <#if known>
        <@l.logout />
        </#if>
        <#if !known>
            <a href="/login" class="btn btn-primary" role="button">Sign in</a>
        </#if>
    </div>
</nav>
