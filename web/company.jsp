<%-- 
    Document   : company
    Created on : 08-10-2015, 20:49:06
    Author     : Jonas
--%>

<jsp:include page="/includes/header.jsp"/>

<div class="container-fluid col-md-4 ">

    <!--
    Search for a person
    -->
    <h2>Search For a Company Using CVR!</h2>
    <br>
    <div class="panel panel-default">
        <br>
        <div class="container">
            <div class="input-group col-md-6">
                <input type="number" class="form-control" id="getCompanyCvr" placeholder="Find company with cvr">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button" id="getCompanyCvrButton">Find Company</button>
                </span>
            </div>
        </div>

        <br>
        <!--
        Delete person input 
        -->
        <div class="container">
            <h4>Or delete one using cvr!</h4>
            <div class="input-group col-md-6">
                <input type="number" class="form-control" placeholder="Delete company with cvr" id="deletePersonInput">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button" id="deletePersonButton">Delete Company</button>
                </span>
            </div>
        </div>
        <br>
    </div>

    <div class="panel panel-default" id="company">
        <div class="container" >
            <br>
            <blockquote id="getPersonPhone">
                <p># 12</p>
                <p>First Name: Jonas</p>
                <p>Last Name: Rafn</p>
                <p>Email: jonaschrafn@gmail.com</p>
                <p>Phone: 53555358</p>
                <p>Hobby: Programming</p>
            </blockquote>
        </div>
    </div>

</div>


<div class="container-fluid col-md-4">
    <!--
    Create person input form
    -->
    <h2>Add Company to Database</h2>
    <br>
    <div class="panel panel-default">
        <br>


        <div class="container">
            <div class="row">
                <form role="form">
                    <div class="col-lg-6">
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                        <div class="form-group">
                            <label for="InputCompanyName">Enter Company Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="InputCompanyName" id="createCompanyname" placeholder="Company Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyDescription">Enter Company Description</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyDescription" name="InputCompanyDescription" placeholder="Company Description" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyCVR">Enter Company CVR</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyCVR" name="InputCompanyCVR" placeholder="Company CVR" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyEmail">Enter Company Email</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyEmail" name="InputCompanyEmail" placeholder="Company Email " required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyNumEmp">Enter Number of Employees</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="createCompanyNumEmp" name="InputCompanyNumEmp" placeholder="Number of Employees" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyMarketValue">Enter Market Value</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="createCompanyMarketValue" name="InputCompanyMarketValue" placeholder="Market Value" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputPhoneNumber">Enter Phone Number</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="createCompanyPhoneNumber" name="InputPhoneNumber" placeholder="Phone Number" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyStreet">Enter Street</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyStreet" name="InputCompanyStreet" placeholder="Street" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyAdditionalInfo">Enter AdditionalInfo</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyAdditionalInfo" name="InputCompanyAdditionalInfo" placeholder="AdditionalInfo" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCompanyCity">Enter City</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createCompanyCity" name="InputCompanyCity" placeholder="City" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputEmail">Enter Hobby</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createPersonHobby" name="InputHobby" placeholder="Hobby" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-primary pull-right">
                    </div>
                </form>
            </div>
        </div>
        <br>
    </div>
</div>
<jsp:include page="/includes/footer.jsp"/>
