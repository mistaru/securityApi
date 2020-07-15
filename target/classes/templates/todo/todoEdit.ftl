<#import "../parts/common.ftl" as c>
<@c.page>

    <form action="/todo/update" method="post">
        <input type="hidden" value="${todo.id}" name="id">

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
                                <label for="description" class="cols-sm-2 control-label">Closing Date</label>
                                <div class="cols-sm-10">
                                    <div class="input-group">
                                        <input type="date" name="closingDate" class="form-control"
                                               placeholder="closingDate" value="${todo.closingDate}"/>
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
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-primary">Save</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

</@c.page>
