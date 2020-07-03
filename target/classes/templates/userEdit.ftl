<#import "parts/common.ftl" as c>

<@c.page>
    <h2 style="color:Black">User editor : ${user.fullName}</h2>
    <form action="/user" method="post">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="card" style="margin:50px 0">
                        <div class="card-header">Role switch</div>
                        <ul class="list-group list-group-flush">
                            <#list roles as role>
                            <li class="list-group-item">
                                ${role}
                                <label class="switch">
                                    <input type="checkbox" class="default"
                                           name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>
                                    <span class="slider"></span>
                                </label>
                                </#list>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-8 col-md-4">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="gender3">Activity</label>
                        </div>
                        <select name="active" class="custom-select">
                            <option selected value="true">Enabled</option>
                            <option value="false">Disabled</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>
