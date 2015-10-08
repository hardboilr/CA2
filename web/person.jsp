<%-- 
    Document   : person
    Created on : 08-10-2015, 20:48:53
    Author     : Jonas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/includes/header.jsp"/>

<div class="container-fluid col-md-4">

    <!--
    Search for a person
    -->
    <h2>Search For a Person Using Phone Number!</h2>
    <br>
    <div class="panel panel-default">
        <br>
        <div class="container">
            <div class="input-group col-md-6">
                <input type="number" class="form-control" id="getPersonPhoneInput" placeholder="Find person with phone">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button" id="getPersonPhoneButton">Find Person</button>
                </span>
            </div>
        </div>

        <br>
        <!--
        Delete person input 
        -->
        <div class="container">
            <h4>Or delete one using id!</h4>
            <div class="input-group col-md-6">
                <input type="number" class="form-control" placeholder="Delete person with id" id="deletePersonInput">
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="button" id="deletePersonButton">Delete Person</button>
                </span>
            </div>
        </div>
        <br>

    </div>

    <div class="panel panel-default" id="person">
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
    <h2>Add Person to Database</h2>
    <br>
    <div class="panel panel-default">
        <br>


        <div class="container">
            <div class="row">
                <form role="form">
                    <div class="col-lg-6">
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                        <div class="form-group">
                            <label for="InputFirstName">Enter First Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="InputFirstName" id="createPersonFirstName" placeholder="First Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputLastName">Enter Last Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createPersonLastName" name="InputLastName" placeholder="Last Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputEmail">Enter Email</label>
                            <div class="input-group">
                                <input type="email" class="form-control" id="createPersonEmail" name="InputEmail" placeholder="Email" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputPhone">Enter Phone Number</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="createPersonPhone" name="InputPhone" placeholder="Phone Number" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputStreet">Enter Street</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createPersonStreet" name="InputStreet" placeholder="Street" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputAdditionalInfo">Enter AdditionalInfo</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createPersonAdditionalInfo" name="InputAdditionalInfo" placeholder="AdditionalInfo" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputCity">Enter City</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="createPersonCity" name="InputCity" placeholder="City" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="InputHobby">Enter Hobby</label>
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
