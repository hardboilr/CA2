<%-- 
    Document   : company
    Created on : 08-10-2015, 20:49:06
    Author     : Jonas
--%>

<jsp:include page="/includes/header.jsp"/>
<div class="container">
    <div class="col-md-6">

        <!--
        Search for a person
        -->
        <h2>Search For a Company Using CVR!</h2>
        <br>
        <div class="panel panel-default">
            <br>
            <div>
                <div class="input-group col-md-8">
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
            <div >
                <h4>Or delete one using cvr!</h4>
                <div class="input-group">
                    <input type="number" class="form-control" placeholder="Delete company with cvr" id="deletePersonInput">
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="button" id="deleteCompanyButton">Delete Company</button>
                    </span>
                </div>
            </div>
            <br>
        </div>

        <div class="panel panel-default" id="company">
            <div>
                <br>
                <blockquote id="getCompanyCvr">
                    <p># 1340</p>
                    <p>Name: Youspan</p>
                    <p>Description: dui maecenas tristique est et tempus semper est quam pharetra</p>
                    <p>CVR 53924216</p>
                    <p>Email: sphillips0@networkadvertising.org</p>
                    <p>NumOfEmployees: 20939</p>
                    <p>MarketValue: 62493832</p>
                    <p>Phone: 65603329</p> 
                    <p>Street: 7904 Hazelcrest Crossing</p>
                    <p>AdditionalInfo: 3.th</p>
                    <p>Zipcode: 1371</p>
                    <p>City: København K</p>
                </blockquote>
            </div>
        </div>

    </div>


    <div class=" col-md-6">
        <!--
        Create person input form
        -->
        <h2>Add Company to Database</h2>
        <br>
        <div class="panel panel-default">
            <br>


            <div >
                <div class="row">
                    <form role="form">
                        <div class="col-lg-12">
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
                                <label for="InputCompanyCity">Enter Zipcode</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="createCompanyCity" name="InputCompanyCity" placeholder="Zipcode" required>
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
                            <button  type="button" id="createButtonCompany"  class="btn btn-primary pull-right">create</button>
                            <button  type="button" id="editButtonCompany"  class="btn btn-primary pull-right">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
            <br>
        </div>
    </div>
</div>
<jsp:include page="/includes/footer.jsp"/>
