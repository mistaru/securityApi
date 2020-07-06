<#import "../parts/common.ftl" as c>
<@c.page>
    <#if TODO_ERROR_EDIT??>
        <div>
            <br>
            <h3 align="center"  style="color:#b80c09" >${TODO_ERROR_EDIT}</h3>
        </div>
    </#if>

    <#if TODO_ERROR_DELETE??>
        <div>
            <br>
            <h3 align="center"  style="color:#b80c09">${TODO_ERROR_DELETE}</h3>
        </div>
    </#if>

    <form action="/todo" method="post">
        <h1 align="center" style="color:Black">Todo edit - ${todo.title}</h1>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label for="title" class="cols-sm-2 control-label">Title</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input type="text" name="title" class="form-control" placeholder="title"
                                               value="${todo.title}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="description" class="cols-sm-2 control-label">Description</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input type="text" name="description" class="form-control"
                                               placeholder="description" value="${todo.description}"/>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="status" class="cols-sm-2 control-label">Status</label>
                                <select name="status" class="custom-select">
                                    <option>NEW</option>
                                    <option>DOING</option>
                                    <option>DONE</option>
                                </select>
                            </div>
                            <div align="center">
                                <input type="hidden" value="${todo.id}" name="todoId">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <h6 align="center">or</h6>

    <form action="/todo/deleteTodo" method="post">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card">
                        <input type="hidden" value="${todo.id}" name="todoId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</@c.page>
