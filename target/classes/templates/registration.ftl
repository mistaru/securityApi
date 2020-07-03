<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <h2 align="center" style="color:Black">Register for SecurityApi</h2>
    </div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>
