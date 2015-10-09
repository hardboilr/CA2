var personObject;
$(document).ready(function () {
    $('#person').hide();
    $('#company').hide();
    $('#editButton').hide();
});

$('#getCompanyCvrButton').on('click', function () {
//    alert("hej");
    $('#company').hide();
});

$('#deletePersonButton').on('click', function () {
//    alert("hej");
    $('#company').show();
});

$('#getPersonPhoneButton').on('click', function () {
    findPerson($('#getPersonPhoneInput').val());
    $('#getPersonPhoneInput').val('');
    $('#person').show();
});

$('#deletePersonButton').on('click', function () {
    deletePerson($('#deletePersonInput').val());
    $('#person').hide();
});

$('#editPersonButton').on('click', function () {
    editPerson(personObject);
    $('#createButton').hide();
    $('#editButton').show();
});

$('#submit').on('click', function () {
    console.log($('.phone').val());
});

$('#addPhone').on('click', function () {
    addPhone();
});

$('#addHobby').on('click', function () {
    addHobby();
});

$('#createButton').on('click', function () {

});

$('#editButton').on('click', function () {
    var phonesJSON = [{
            number: $('#create').val()
        }];

    $('.email').map(function () {
        return $(this).val();
    }).get();

//    console.log(JSON.stringify($("form").serialize()));

        console.log(JSON.stringify($('form').serializeObject()));

    
//    $('form').submit(function() {
////        $('#result').text(JSON.stringify($('form').serializeObject()));
//        return false;
//    });

//    var personJSON = {
//        firstName: $('#createPersonFirstName').val(),
//        lastName: $('#createPersonLastName').val(),
//        email: $('#createPersonEmail').val(),
//        street: $('#createPersonStreet').val(),
//        additionalInfo: $('#createPersonAdditionalInfo').val(),
//        zipCode: $('#createPersonZipcode').val(),
//        phones :
//    };

//    console.log(JSON.stringify(personJSON));
});

function findPerson(phoneNumber) {
    var url = "/CA2/api/person/get/" + phoneNumber;
    $.getJSON(url, function (person) {
        updatePersonPanel(person);
        personObject = person;
    });
//    $.ajax({
//        url: url,
//        method: 'GET',
//        succes: function (person) {
//            updatePersonPanel(person);
//            personObject = person;
//        }
//    });
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
        if (phoneCount < 3) {
            addPhone();
        }
    });


    $('#createPersonStreet').val(person.street);
    $('#createPersonAdditionalInfo').val(person.additionalInfo);
    $('#createPersonZipcode').val(person.zipCode);

    $.each(person.hobbies, function (index, hobby) {
        hobbyCount++;
        $('#createPersonHobbyName').val(hobby.name);
        $('#createPersonHobbyDescription').val(hobby.description);
        if (hobbyCount < 3) {
            addHobby();
        }
    });
}

function addPhone() {
    $('#phoneNumberDescription').clone().appendTo('#phoneNumbers');
}

function addHobby() {
    $('#hobbyNameDescription').clone().appendTo('#hobbies');

}

function editPersonAPI(person) {

}


$.fn.serializeObject = function ()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};