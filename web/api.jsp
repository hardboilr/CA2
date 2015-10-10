<jsp:include page="/includes/header.jsp"/>
<div class="container">
    <h1>Beskrivelse af REST API</h1>
    <h4 class="text-muted">Features RestService Person</h4>
    <hr/>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>GET</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>URL</th>
                <th>Return</th>
                <th>Response</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>api/person/complete</td>
                <td>Returnere alle personer, med alle detaljer i et json format</td>
                <td>Succes: Statuscode: 200<br>Error: Statuscode 404, fejlbesked i</td>
            </tr>
            <tr>
                <td>api/person/get/{phone nr}</td>
                <td>Returnere person, med  det givne tlf nr, med alle detaljer</td>
                <td>Succes: Statuscode: 200<br>Error: Statuscode 404, fejlbesked i</td>
            </tr>
            <tr>
                <td>api/person/hobby/{hobby}</td>
                <td>Returnere alle personer, med den givne hobby i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td>api/person/city/{zipcode}</td>
                <td>Returnere alle personer, som bor i det givne post nr.</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td>api/person/hobbycount/{hobby}</td>
                <td>Returne det antal personer som dyrker den givne hobby i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>POST</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/person</td>
                <td>Returnere den nyoprettede person i json format</td>
                <td>Succes: Statuscode:  201<br>Error: Statuscode 403, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>PUT</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/person/edit/{phone}</td>
                <td>Returnere den opdaterede person i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>DELETE</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/person/delete/{id}</td>
                <td>Returnere den slettede person i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
        </tbody>
    </table>
    <br>
    <h4 class="text-muted">Features RestService Company</h4>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>GET</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th>URL</th>
                <th>Return</th>
                <th>Response</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>api/company/complete</td>
                <td>Returnere alle virksomheder, med alle detaljer i et json format</td>
                <td>Succes: Statuscode: 200<br>Error: Statuscode 404, fejlbesked i</td>
            </tr>
            <tr>
                <td>api/company/{cvr}</td>
                <td>Returnere virksomheden med  det givne cvr nr, med alle detaljer</td>
                <td>Succes: Statuscode: 200<br>Error: Statuscode 404, fejlbesked i</td>
            </tr>
            <tr>
                <td>api/company/marketvalue/{marketvalue}</td>
                <td>Returnere alle virksomheder som har en højere markedsværdi end den givne i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td>api/company/employees/{employees}</td>
                <td>Returnere alle virksomheder der har flere ansatte end det givne i json format </td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>POST</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/company</td>
                <td>Returnere den nyoprettede virksomhed i json format</td>
                <td>Succes: Statuscode:  201<br>Error: Statuscode 403, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>PUT</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/company</td>
                <td>Returnere den opdaterede virksomhed i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
            <tr>
                <td><Strong>DELETE</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>api/company/delete/{cvr}</td>
                <td>Returnere den slettede person i json format</td>
                <td>Succes: Statuscode:  200<br>Error: Statuscode 404, fejlbesked i json</td>
            </tr>
        </tbody>
    </table>
    <h3 class="text-muted">Eksempel på brug af api i JQuery og AJAX</h3>
    <hr/>
    <h4 class="text-muted">Find person med tlf nr</h4>
    <img src="pictures/findperson.png">
    <h4 class="text-muted">Slet person med id</h4>
    <img src="pictures/deleteperson.png">
    
</div>

<jsp:include page="/includes/footer.jsp"/>