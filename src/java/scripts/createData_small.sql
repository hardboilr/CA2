INSERT INTO CITYINFO (zipcode, city) VALUES ('2000', 'Frederiksberg');
INSERT INTO CITYINFO (zipcode, city) VALUES ('1915', 'Frederiksberg C');

insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Søndre Fasanvej 90b', 'st.th.', '2000');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Lindevangs Alle 23', '5.tv.', '2000');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Smallegade 46A', '2.th.', '2000');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Smallegade 46', '1.th.', '2000');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Borups Alle 150', '4.th.', '2000');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Steenstrups Alle 15', '1.th.', '1915');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('H. C. Ørsteds Vej 74', 'st.tv.', '1915');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Carl Plougs Vej 10', '5.th.', '1915');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Niels Ebbesens Vej 55', '3.th.', '1915');
insert into ADDRESS (street, additionalInfo, cityinfo_zipcode) values ('Johnstrups Alle 16', '1.th.', '1915');

insert into HOBBY (name, description) values ('programming', 'software development');
insert into HOBBY (name, description) values ('chess', 'playing chess against other nerds');
insert into HOBBY (name, description) values ('gaming', 'playing computer games ');
insert into HOBBY (name, description) values ('grand theft auto', 'stealing cars');
insert into HOBBY (name, description) values ('coffee roasting', 'making ones own coffee from raw coffee beans');
insert into HOBBY (name, description) values ('dance', 'dancing and having a gay time');

insert into infoentity (id, dtype, email, additionalinfo, street) values (1, 'Person', 'niels@nielsen.com', 'st.th.', 'Søndre Fasanvej 90b');
insert into infoentity (id, dtype, email, additionalinfo, street) values (2, 'Person', 'jens@jensen.dk', '5.tv.', 'Lindevangs Alle 23');
insert into infoentity (id, dtype, email, additionalinfo, street) values (3, 'Person', 'mail@tobiasjacobsen.com', '2.th.', 'Smallegade 46A');
insert into infoentity (id, dtype, email, additionalinfo, street) values (4, 'Person', 'peter@petersen.dk', '1.th.', 'Smallegade 46');
insert into infoentity (id, dtype, email, additionalinfo, street) values (5, 'Person', 'julius@juliusen', '4.th.', 'Borups Alle 150');

insert into infoentity (id, dtype, email, additionalinfo, street) values (6, 'Company', 'lars@larsen.com', '1.th.', 'Steenstrups Alle 15');
insert into infoentity (id, dtype, email, additionalinfo, street) values (7, 'Company', 'palle@pallesen.dk', 'st.tv.', 'H. C. Ørsteds Vej 74');
insert into infoentity (id, dtype, email, additionalinfo, street) values (8, 'Company', 'karl@karlsen.com', '5.th.', 'Carl Plougs Vej 10');
insert into infoentity (id, dtype, email, additionalinfo, street) values (9, 'Company', 'fister@fistersen.com', '3.th.', 'Niels Ebbesens Vej 55');
insert into infoentity (id, dtype, email, additionalinfo, street) values (10, 'Company', 'johan@johansen.dk', '1.th.', 'Johnstrups Alle 16');

insert into person_HOBBY (hobbies_name, persons_id) values ('grand theft auto', 1);
insert into person_HOBBY (hobbies_name, persons_id) values ('gaming', 2);
insert into person_HOBBY (hobbies_name, persons_id) values ('gaming', 3);
insert into person_HOBBY (hobbies_name, persons_id) values ('dance', 4);
insert into person_HOBBY (hobbies_name, persons_id) values ('dance', 5);

insert into person (id, firstname, lastname) values (1, 'Niels', 'Nielsen');
insert into person (id, firstname, lastname) values (2, 'Jens', 'Jensen');
insert into person (id, firstname, lastname) values (3, 'Tobias', 'Jacobsen');
insert into person (id, firstname, lastname) values (4, 'Peter', 'Petersen');
insert into person (id, firstname, lastname) values (5, 'Julius', 'Juliusen');

insert into company (id, numemployees, cvr, description, marketvalue, name) values (6, 55, 53924216, 'The glorious company', 500000, 'Drnk-And-Pizzed');
insert into company (id, numemployees, cvr, description, marketvalue, name) values (7, 152, 52698711, 'We make the best coffee in the universe', 1500000, 'Goat Sheit Coffee');
insert into company (id, numemployees, cvr, description, marketvalue, name) values (8, 3, 56983231, 'Selling pigs', 150, 'YouSwine');
insert into company (id, numemployees, cvr, description, marketvalue, name) values (9, 1, 11258832, 'Producing loud noises', 9940000, 'Yelling Corp');
insert into company (id, numemployees, cvr, description, marketvalue, name) values (10, 10, 14559812, 'We clean house and make fun', 658713, 'Suc-A-Dig');

insert into phone (number, description, infoentity_id) values ('50206142', 'number to my iphone', 1);
insert into phone (number, description, infoentity_id) values ('12345678', 'use this number to reach me', 2);
insert into phone (number, description, infoentity_id) values ('87654321', 'use this between 0900AM to 1600PM only', 2);
insert into phone (number, description, infoentity_id) values ('31203065', 'fax', 3);
insert into phone (number, description, infoentity_id) values ('29564310', 'iphone', 4);
insert into phone (number, description, infoentity_id) values ('01234567', 'main number', 5);
insert into phone (number, description, infoentity_id) values ('02345678', 'work phone', 6);
insert into phone (number, description, infoentity_id) values ('87654213', 'never call this', 7);
insert into phone (number, description, infoentity_id) values ('15189878', 'reach me on this number', 8);
insert into phone (number, description, infoentity_id) values ('11256782', 'telefax', 9);
insert into phone (number, description, infoentity_id) values ('26587213', 'main number', 10);
insert into phone (number, description, infoentity_id) values ('68765431', 'for emergencies only', 10);