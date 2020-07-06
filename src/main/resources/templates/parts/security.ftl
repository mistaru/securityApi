<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getFullName()
    isAdmin = user.isAdmin()
    >
<#else>
    <#assign
    name = "guest"
    isAdmin = false
    >
</#if>
