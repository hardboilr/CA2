<jsp:include page="/includes/header.jsp"/>

<div class="container">
    <h1>Dokumentation</h1>
    <div class="col-sm-12">
        <h3 class="text-muted">Design</h3>
        <hr/>
        <p>I den nedenstående model illustreres hvordan applikationen er opbygget.</p>
        <img src="pictures/design.png" alt="design">
        <p>For at opnå lav binding er der mellem forretningslaget og datalaget anvendt facadeklasser til hhv. Person og Company. Vores formål med at anvende to opdelte facadeklasser er skabe et bedre overblik samt at give mulighed for, at udskifte dele af applikationen uden det har effekt på den resterende del.</p>
        <br>
        <p>Til at adskille præsentationslaget og forretningslaget er der anvendt en MVC struktur, hvor der i kommunikationen mellem de to lag, bliver anvendt DTO?er i form af Json objekter. DTO?erne er modeller, der repræsentere data fra databasen, og som præsentationslaget anvender til at vise information. </p>
        <br>
        <p>Datalaget er bygget op således at det tager en EntityManagerFactory som dependency injection. Dette gør det muligt at koble forskellige databaser på systemet, som f.eks. en testdatabase.</p>
        <br>
        <h3 class="text-muted">Fejlhåndtering</h3>
        <hr/>
        <p>Til at håndtere fejl har vi implementeret hhv. en. PersonNotFound-, CompanyNotFound-, samt en PhoneExistException. Ved at anvende Exceptionmappers, har vi samtidig mulighed for at sende både stacktrace, samt en fejlbesked i Jsonformat. Json eksempel:</p>
        <p>{"Message" : "No Company found"}</p>
        <br>
        <h3 class="text-muted">Features</h3>
        <hr/>
        <p>Til udviklingen at dette system har vi  primært haft fokus på CRUD og har derfor implementeret flg. features i vores api?er:</p>
        <h4 class="text-muted">Features RestService Person</h4>
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
        <h3 class="text-muted">Test</h3>
        <hr/>
        <h4 class="text-muted">Strategi</h4>
        <p>I forbindelse med udviklingen af applikationen har vi haft fokus på at udvikle et enkelt lag af gangen.  Det har medført at vi i forbindelse med vores test, først har udført JUnit test af facade klasserne, og derefter brugt Rest Assured/Postman til test af vores api?er.</p>
        <br>
        <p>Til at sikre at vores facadeklasser virker har vi udført flg. testcases.</p>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Company</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <th>Test id</th>
                    <th>Metode</th>
                    <th>Test case</th>
                    <th>Input</th>
                    <th>Forventet resultat</th>
                    <th>Resultat</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>addCompany</td>
                    <td>Tilføj nyt company</td>
                    <td>Company, Company c</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>addCompanyWithExistingPhoneNumber</td>
                    <td>Tilføj nyt company med et eksisterende tlf. Nr.</td>
                    <td>Company, Company c</td>
                    <td>PhoneExistException</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>getCompany</td>
                    <td>Find company med cvr. Nr.</td>
                    <td>Cvr, 12345l</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>getCompanyError</td>
                    <td>Find et company med et cvr nr. der ikke eksistere</td>
                    <td>Cvr, 23l</td>
                    <td>CompanyNotFoundException</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>getCompanies</td>
                    <td>Find alle companies</td>
                    <td>Size, 1000</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>EditCompany</td>
                    <td>Opdater company</td>
                    <td>Company, Company c</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
            </tbody>
        </table>
        <br>
        <img src="pictures/companyfacadetest.png" alt="TestCompany">
        <br>
        <br>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Person</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <th>Test id</th>
                    <th>Metode</th>
                    <th>Test case</th>
                    <th>Input</th>
                    <th>Forventet resultat</th>
                    <th>Resultat</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>createGetPerson</td>
                    <td>Tilføj ny person, og hent den nye person</td>
                    <td>Person, Person p</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>editPerson</td>
                    <td>Opdater person</td>
                    <td>Person, Person p</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>deletePerson</td>
                    <td>Fjern person</td>
                    <td>Person, Person p</td>
                    <td>True</td>
                    <td>Passed</td>
                </tr>
            </tbody>
        </table>
        <br>
        <img src="pictures/personfacadetest.png" alt="TestPerson">
        <br>
        <br>
        <p><strong>RestAssured</strong></p>
        <img src="pictures/resttest.png" alt="RestAssured">
        <br>
        <h3 class="text-muted">Implementering af arv</h3>
        <hr/>
        <p>De to entiteter person og company nedarver fra InfoEntity via annotationen @Inheritance med strategien "InheritanceType.JOINED". Vi har valgt at bruge strategien JOINED, da den afspejler forretningsdomænet.  Dvs. de enkelte tabeller indeholder kun de lokale attributter,  samt en primærnøgle som oprettes i InfoEntitytabellen.</p>
        <br>
        <h3 class="text-muted">Guide til test af system</h3>
        <hr/>
         <h4 class="text-muted">Openshift</h4>
         <p>På grund af tidmangel er det kun muligt at teste funktionerne på Person siden, Company siden er kun lavet rent grafisk, som et eksempel på hvordan det ville se ud.</p>
         <p>Dog kan alle funktioner testes via "PostMan", se nedenstående eksempler.</p>
         <br>
         <h4 class="text-muted">Rest Assured</h4>
         <p>Tilføjelsen af Security Authentication har medført at vores Rest Assured test fejler. For at teste vores api'er er det derfor nødvendigt at slette alt Security i web.xml filen.  </p>
         <br>
         <h4 class="text-muted">PostMan</h4>
         <p>Eksempler på URL til postman med Basic Authentication 'test' 'test'</p>
         <h5 class="text-muted">CVR</h5>
         <p>https://localhost:8443/CA2/api/company/22350888</p>
         <h5 class="text-muted">Complete</h5>
         <p>https://localhost:8443/CA2/api/company/complete</p>
         <h5 class="text-muted">Marketvalue</h5>
         <p>https://localhost:8443/CA2/api/company/marketvalue/50000000</p>
         <h5 class="text-muted">Employee</h5>
         <p>https://localhost:8443/CA2/api/company/employees/41000</p>
         <h5 class="text-muted">Employee</h5>
         <p>https://localhost:8443/CA2/api/company/employees/46000</p>
         <h5 class="text-muted">Phone</h5>
         <p>https://localhost:8443/CA2/api/person/get/09144533</p>
         <h5 class="text-muted">ZipCode</h5>
         <p>https://localhost:8443/CA2/api/person/city/2800</p>
         <h5 class="text-muted">Hobby count</h5>
         <p>https://localhost:8443/CA2/api/person/hobbycount/mushroom hunting</p>
         <h5 class="text-muted">Delete</h5>
         <p>https://localhost:8443/CA2/api/person/delete/249</p>
         <br>
         <h3 class="text-muted">Hvem har lavet hvad</h3>
         <hr/>
         <p>Med projektets størrelse in mente, har vi alle arbejdet med lidt af det hele, og den nedenstående beskrivelse skal derfor anses som et overblik over hvem der har været den primære ansvarlige. Vi mener derfor også at alle har gjort sig fortjent til det fulde antal Studypoints</p>
         <h4 class="text-muted">Ansvarsfordeling</h4>
         <p>Oprettelse af Entitetsklasser og database: Sebastian</p>
         <p>Oprettelse af testdata: Tobias</p>
         <p>Facade + jUnit-test: Company: Sebastian</p>
         <p>Facade + jUnit-test: Person: Jonas</p>
         <p>REST-API Person: Jonas</p>
         <p>REST-API Company: Sebastian/Tobias</p>
         <p>Test af REST-API: Person: Tobias</p>
         <p>Test af REST-API: Company: Tobias</p>
         <p>FrontEnd: Javascript(jquery, ajax) + html + bootstrap: Jonas</p>
         <p>Documentation: Sebastian</p>
         <p>Security: Fælles</p>
         <p>Deployment: Fælles</p>
         
    </div>
</div>
<jsp:include page="/includes/footer.jsp"/>