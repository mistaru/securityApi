<#import "../parts/common.ftl" as c>

<@c.page>

    <h2 align="center" style="color:Black">List of user</h2>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Full name</th>
            <th scope="col">User name</th>
            <th scope="col">Role</th>
            <th scope="col">Active</th>
            <th scope="col">Edit</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.fullName}</td>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td>${user.activity?string('Enabled', 'Disabled') }</td>
                <td><a href="/user/${user.id}">Edit</a></td>
            </tr>
        </#list>
    </table>
</@c.page>
