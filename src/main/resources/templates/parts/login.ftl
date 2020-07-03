<#macro login path isRegisterForm>
    <form action="${path}" method="post">

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <form class="form-horizontal" method="post" action="#">
                                <#if isRegisterForm>
                                <div class="form-group">
                                    <label for="name" class="cols-sm-2 control-label">Full Name</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                            <input type="text" name="fullName" class="form-control" placeholder="Full Name (Any latin letters)"
                                                   required pattern="^[a-zA-Z ]+$"/>
                                        </div>
                                    </div>
                                </div>
                                </#if>
                                <div class="form-group">
                                    <label for="username" class="cols-sm-2 control-label">Username</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                            <input type="text" name="username" class="form-control"
                                                   placeholder="Username (Any lowercase latin letters and numbers)" required pattern="^[a-z0-9]+$"/>
                                        </div>
                                    </div>
                                </div>

                                <#if usernameError??>
                                    <div>
                                        <br>
                                        <h6>${usernameError}</h6>
                                    </div>
                                </#if>

                                <div class="form-group">
                                    <label for="password" class="cols-sm-2 control-label">Password</label>
                                    <div class="cols-sm-10">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                            <input type="password" name="password" class="form-control" placeholder="Password" minlength="6" required/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <#if !isRegisterForm><a href="/registration">Register</a></#if>
                                    <a class="link float-right">
                                        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Register<#else>Sign In</#if></button></a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>
