<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <h2 align="center" style="color:Black">Authorization</h2>
    </div>
    <@l.login "/login" false/>
</@c.page>
