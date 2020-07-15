<#import "../parts/common.ftl" as c>

<@c.page>

    <a class="btn btn-dark" data-toggle="collapse" href="#collapseExampleNewTodo" role="button"
       aria-expanded="false"
       aria-controls="collapseExampleNewTodo">
        Add
    </a>

    <a class="btn btn-secondary" data-toggle="collapse" href="#collapseExampleSearch" role="button"
       aria-expanded="false"
       aria-controls="collapseExampleNewTodo">
        Search
    </a>

    <button class="btn btn-success" onclick="exportTableToExcel('tblData')">Export to Excel</button>

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

    <div class="collapse" id="collapseExampleSearch">
        <div class="form-group mt-3">
            <form method="post" action="/todo/list2">
                <div class="row">
                    <div class="col-auto">
                        <input type="text" name="title" class="form-control" placeholder="Title"/>
                    </div>
                    <div class="col-auto">
                        <select name="status" class="custom-select">
                            <option value="">All Status</option>
                            <option>NEW</option>
                            <option>DOING</option>
                            <option>DONE</option>
                        </select>&nbsp;
                    </div>
                    <div class="col-auto">
                        <input type="date" name="from" class="form-control" placeholder="closingDate"/>
                    </div>
                    <div class="col-auto">
                        <input type="date" name="to" class="form-control" placeholder="closingDate"/>
                    </div>

                    <div class="col-auto">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-primary" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <h2 align="center" style="color:Black">List of Todo</h2>

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
        <#list allTodo as allTodo>
            <tr>
                <td>${allTodo.getTitle()}</td>
                <td>${allTodo.getStatus()}</td>
                <td>${allTodo.getClosingDate()}</td>
                <td>${allTodo.getDescription()}</td>
                <td><a href="/todo/${allTodo.id}" class="card-link">Edit</a></td>
            </tr>
        </#list>
    </table>

    <script>
        function exportTableToExcel(tableID, filename = '') {
            var downloadLink;
            var dataType = 'application/vnd.ms-excel';
            var tableSelect = document.getElementById(tableID);
            var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

            filename = filename ? filename + '.xls' : 'excel_data.xls';

            downloadLink = document.createElement("a");

            document.body.appendChild(downloadLink);

            if (navigator.msSaveOrOpenBlob) {
                var blob = new Blob(['\ufeff', tableHTML], {
                    type: dataType
                });
                navigator.msSaveOrOpenBlob(blob, filename);
            } else {
                downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
                downloadLink.download = filename;
                downloadLink.click();
            }
        }
    </script>

</@c.page>
