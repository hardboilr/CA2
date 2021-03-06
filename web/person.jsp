<%-- 
    Document   : person
    Created on : 08-10-2015, 20:48:53
    Author     : Jonas
--%>

<jsp:include page="/includes/header.jsp"/>
<div class="container">
    <div class="col-md-6">

        <!--
        Search for a person
        -->
        <h2>Search For a Person Using Phone Number!</h2>
        <br>
        <div class="panel panel-default">
            <br>
            <div >
                <div class="input-group col-md-8">
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
            <div >
                <h4>Or delete one using id!</h4>
                <div class="input-group col-md-12">
                    <input type="number" class="form-control" placeholder="Delete person with id" id="deletePersonInput">
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="button" id="deletePersonButton">Delete Person</button>
                    </span>
                </div>
            </div>
            <br>

        </div>

        <div class="panel panel-default" id="person">
            <div  >
                <br>
                <blockquote id="getPersonPhone">
                </blockquote>

                <button class="btn btn-primary" type="button" id="editPersonButton">Edit person</button>

            </div>
            <br>
        </div>
    </div>


    <div class=" col-md-6">
        <!--
        Create person input form
        -->
        <h2>Add Person to Database</h2>
        <br>
        <div class="panel panel-default">
            <br>


            <div >
                <div class="row">
                    <form role="form">
                        <div class="col-lg-12">
                            <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                            <div class="form-group">
                                <label for="InputFirstName">Enter First Name</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" name="firstName" id="createPersonFirstName" placeholder="First Name" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputLastName">Enter Last Name</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="createPersonLastName" name="lastName" placeholder="Last Name" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputEmail">Enter Email</label>
                                <div class="input-group">
                                    <input type="email" class="form-control" id="createPersonEmail" name="email" placeholder="Email" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <label for="InputPhone">Enter Phone Number</label>
                            <div class="input-group">
                                <input type="number" class="phoneNumber form-control" id="createPersonPhoneNumber" name="number" placeholder="Phone Number" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                            <label for="InputPhone">Enter Phone Description</label>
                            <div class="input-group">
                                <input type="text" class="phoneDescription form-control" id="createPersonPhoneDescription" name="description" placeholder="Phone Description" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
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
                                <label for="InputCity">Enter Zipcode</label>
                                <div class="input-group">
                                    <input type="number" class="form-control" id="createPersonZipcode" name="InputCity" placeholder="Zipcode" required>
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
                                <label for="InputHobbyName">Enter Hobby Name</label>
                                <div class="input-group">
                                    <input type="text" class="hobbyName form-control" id="createPersonHobbyName" name="InputHobbyName" placeholder="Hobby Name" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="InputHobbyDescription">Enter Hobby Description</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" id="createPersonHobbyDescription" name="InputHobbyDescription" placeholder="Hobby Description" required>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                                </div>
                            </div>
                            <button  type="button" id="createButtonPerson"  class="btn btn-primary pull-right">create</button>
                            <button  type="button" id="editButtonPerson"  class="btn btn-primary pull-right">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
            <br>
        </div>
    </div>
</div>
<jsp:include page="/includes/footer.jsp"/>
