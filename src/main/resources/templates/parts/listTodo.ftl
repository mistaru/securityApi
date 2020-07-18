<#include "security.ftl">
<#import "pager.ftl" as p>

<table id="tblData" class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Title</th>
        <th scope="col">Status</th>
        <th scope="col">Closing Date</th>
        <th scope="col">Description</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <#list page.content as allTodo>
        <tr>
            <td>${allTodo.getTitle()}</td>
            <td>${allTodo.getStatus()}</td>
            <td>${allTodo.getClosingDate()}</td>
            <td>${allTodo.getDescription()}</td>
            <td>
                <a href="/todo/preUpdate/${allTodo.id}" class="card-link">Edit</a>
                <a href="/todo/delete?id=${allTodo.id}" class="card-link">Delete</a>
            </td>
        </tr>
    <#else>
        No todo
    </#list>
</table>


<@p.pager url page />
