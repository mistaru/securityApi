<#import "../parts/common.ftl" as c>

<@c.page>
    <h1 align="center" style="color:Black">My Todo List</h1>

    <a class="btn1" data-toggle="collapse" href="#collapseExampleNewTodo" role="button"
       aria-expanded="false"
       aria-controls="collapseExampleNewTodo">
        +
    </a>

    <div class="collapse" id="collapseExampleNewTodo">
        <div class="form-group mt-3">
            <form method="post" action="/todo/todoList">
                <div class="row">
                    <div class="col-auto">
                        <input type="text" name="title" class="form-control" placeholder="Title"/>
                    </div>
                    <div class="col-auto">
                        <textarea class="form-control" name="description" rows="1" placeholder="Description"></textarea>
                    </div>
                    <div class="col-auto">
                        <input type="date" name="closingDate" class="form-control" placeholder="closingDate"/>
                    </div>
                    <div class="col-auto">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-primary" type="submit">Create</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="list">
        <div class="list__item">
            <div class="card-columns">
                <#if newTodo??>
                    <#list newTodo as newTodo>
                        <div class="card text-white bg-success mb-3" style="max-width: 18rem;">
                            <div class="card-body">
                                <div class="card-header">${newTodo.getStatus()}</div>
                                <h5 class="card-title">${newTodo.getTitle()}</h5>
                                <p class="card-text">${newTodo.getDescription()}</p>
                                <p class="card-text">${newTodo.getClosingDate()}</p>
                                <a class="btn btn-primary btn-lg" href="/todo/${newTodo.id}" role="button">Edit</a>
                            </div>
                        </div>
                    <#else>
                        No Todo
                    </#list>
                </#if>
            </div>
        </div>

        <div class="list__item">
            <div class="card-columns">
                <#if doingTodo??>
                    <#list doingTodo as doingTodo>
                        <div class="card text-white bg-danger mb-3" style="max-width: 18rem;">
                            <div class="card-body">
                                <div class="card-header">${doingTodo.getStatus()}</div>
                                <h5 class="card-title">${doingTodo.getTitle()}</h5>
                                <p class="card-text">${doingTodo.getDescription()}</p>
                                <p class="card-text">${doingTodo.getClosingDate()}</p>
                                <a class="btn btn-primary btn-lg" href="/todo/${doingTodo.id}" role="button">Edit</a>
                            </div>
                        </div>
                    <#else>
                        No Todo
                    </#list>
                </#if>
            </div>
        </div>

        <div class="list__item">
            <div class="card-columns">
                <#if doneTodo??>
                    <#list doneTodo as doneTodo>
                        <div class="card text-white bg-warning mb-3" style="max-width: 18rem;">
                            <div class="card-body">
                                <div class="card-header">${doneTodo.getStatus()}</div>
                                <h5 class="card-title">${doneTodo.getTitle()}</h5>
                                <p class="card-text">${doneTodo.getDescription()}</p>
                                <p class="card-text">${doneTodo.getClosingDate()}</p>
                                <a class="btn btn-primary btn-lg" href="/todo/${doneTodo.id}" role="button">Edit</a>
                            </div>
                        </div>
                    <#else>
                        No Todo
                    </#list>
                </#if>
            </div>
        </div>
    </div>

</@c.page>
