var personObject;
$(document).ready(function () {
    $('#person').hide();
    $('#company').hide();
    $('#editButtonPerson').hide();
});

$('#getCompanyCvrButton').on('click', function () {
    $('#company').show();
});

$('#deleteCompanyButton').on('click', function () {
    $('#company').hide();
});

$('#getPersonPhoneButton').on('click', function () {
    findPerson($('#getPersonPhoneInput').val());
    $('#getPersonPhoneInput').val('');
    $('#person').show();
});

$('#deletePersonButton').on('click', function () {
    deletePerson($('#deletePersonInput').val());
    $('#deletePersonInput').val('');
    $('#person').hide();
});

$('#editPersonButton').on('click', function () {
    editPerson(personObject);
    $('#createButtonPerson').hide();
    $('#editButtonPerson').show();
});

$('#createButtonPerson').on('click', function () {
    phones = [];
    var number = $('#createPersonPhoneNumber').val();
    var description = $('#createPersonPhoneDescription').val();
    item = {};
    item ['number'] = number;
    item ['description'] = description;
    phones.push(item);

    hobbies = [];
    var name = $('#createPersonHobbyName').val();
    var description = $('#createPersonHobbyDescription').val();
    item = {};
    item ['name'] = name;
    item ['description'] = description;
    hobbies.push(item);



    personJSON = {
        firstName: $('#createPersonFirstName').val(),
        lastName: $('#createPersonLastName').val(), email: $('#createPersonEmail').val(),
        street: $('#createPersonStreet').val(),
        additionalInfo: $('#createPersonAdditionalInfo').val(),
        zipCode: $('#createPersonZipcode').val(),
        city: $('#createPersonCity').val(),
        phones: phones,
        hobbies: hobbies
    };

    console.log(JSON.stringify(personJSON));

    createPersonAPI(JSON.stringify(personJSON));

    $('#createPersonFirstName').val('');
    $('#createPersonLastName').val('');
    $('#createPersonEmail').val('');
    $('#createPersonPhoneDescription').val('');
    $('#createPersonPhoneNumber').val('');
    $('#createPersonStreet').val('');
    $('#createPersonAdditionalInfo').val('');
    $('#createPersonZipcode').val('');
    $('#createPersonCity').val('');
    $('#createPersonHobbyName').val('');
    $('#createPersonHobbyDescription').val('');
});

$('#editButtonPerson').on('click', function () {
    phones = [];
    var number = $('#createPersonPhoneNumber').val();
    var description = $('#createPersonPhoneDescription').val();
    item = {};
    item ['number'] = number;
    item ['description'] = description;
    phones.push(item);

    hobbies = [];
    var name = $('#createPersonHobbyName').val();
    var description = $('#createPersonHobbyDescription').val();
    item = {};
    item ['name'] = name;
    item ['description'] = description;
    hobbies.push(item);


    personJSON = {
        firstName: $('#createPersonFirstName').val(),
        lastName: $('#createPersonLastName').val(), email: $('#createPersonEmail').val(),
        street: $('#createPersonStreet').val(),
        additionalInfo: $('#createPersonAdditionalInfo').val(),
        zipCode: $('#createPersonZipcode').val(),
        city: $('#createPersonCity').val(),
        phones: phones,
        hobbies: hobbies
    };

    console.log(JSON.stringify(personJSON));

    editPersonAPI(JSON.stringify(personJSON), $('#createPersonPhoneNumber').val());

    $('#createPersonFirstName').val('');
    $('#createPersonLastName').val('');
    $('#createPersonEmail').val('');
    $('#createPersonPhoneDescription').val('');
    $('#createPersonPhoneNumber').val('');
    $('#createPersonStreet').val('');
    $('#createPersonAdditionalInfo').val('');
    $('#createPersonZipcode').val('');
    $('#createPersonCity').val('');
    $('#createPersonHobbyName').val('');
    $('#createPersonHobbyDescription').val('');
    $('#getPersonPhone').children().remove();
    $('#person').hide();
});

function findPerson(phoneNumber) {
    var url = "/CA2/api/person/get/" + phoneNumber;
    $.getJSON(url, function (person) {
        updatePersonPanel(person);
        personObject = person;
    });
}

function updatePersonPanel(person) {
    $('#getPersonPhone').children().remove();
    $('#getPersonPhone').append('<p>' + "ID: " + person.id + '</p>');
    $('#getPersonPhone').append('<p>' + "First Name: " + person.firstName + '</p>');
    $('#getPersonPhone').append('<p>' + "Last Name: " + person.lastName + '</p>');
    $('#getPersonPhone').append('<p>' + "Email : " + person.email + '</p>');
    $('#getPersonPhone').append('<p>' + "Address: " + person.street + " " + person.additionalInfo + '</p>');
    $('#getPersonPhone').append('<p>' + "City: " + person.zipCode + " " + person.city + '</p>');
    $.each(person.phones, function (index, phone) {
        $('#getPersonPhone').append('<p class="text-capitalize">' + phone.description + ": " + phone.number + '</p>');
    });
    $.each(person.hobbies, function (index, hobby) {
        $('#getPersonPhone').append('<p class="text-capitalize">' + hobby.name + ": " + hobby.description + '</p>');
    });

    $('#getPersonPhone').append(person.Message);
}

function deletePerson(id) {
    var url = "/CA2/api/person/delete/" + id;
    console.log(id);
    $.ajax({
        url: url,
        method: 'DELETE'
    });
}

function editPerson(person) {
    phoneCount = 0;
    hobbyCount = 0;
    $('#createPersonFirstName').val(person.firstName);
    $('#createPersonLastName').val(person.lastName);
    $('#createPersonEmail').val(person.email);

    $.each(person.phones, function (index, phone) {
        phoneCount++;
        $('#createPersonPhoneNumber').val(phone.number);
        $('#createPersonPhoneDescription').val(phone.description);
    });

    $('#createPersonStreet').val(person.street);
    $('#createPersonAdditionalInfo').val(person.additionalInfo);
    $('#createPersonZipcode').val(person.zipCode);
    $('#createPersonCity').val(person.city);

    $.each(person.hobbies, function (index, hobby) {
        hobbyCount++;
        $('#createPersonHobbyName').val(hobby.name);
        $('#createPersonHobbyDescription').val(hobby.description);
    });
}


function editPersonAPI(person, phone) {
    var url = "/CA2/api/person/edit/" + phone;
    $.ajax({
        url: url,
        method: 'PUT',
        data: person,
        contentType: 'application/json',
        processData: false
    });
}


function createPersonAPI(person) {
    var url = "/CA2/api/person/";
    $.ajax({
        url: url,
        method: 'POST',
        data: person,
        contentType: 'application/json',
        processData: false
    });
}
