-- Insert into apartment table if the id does not exist
INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 1, 74, '1', 0, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 1);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 2, 46, '2', 0, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 2);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 3, 74, '1', 1, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 3);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 4, 46, '2', 1, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 4);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 5, 73, '3', 1, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 5);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 6, 73, '1', 2, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 6);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 7, 46, '2', 2, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 7);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 8, 74, '3', 2, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 8);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 9, 74, '1', 3, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 9);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 10, 45, '2', 3, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 10);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 11, 74, '3', 3, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 11);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 12, 74, '1', 4, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 12);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 13, 46, '2', 4, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 13);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 14, 73, '3', 4, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 14);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 15, 73, '1', 5, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 15);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 16, 46, '2', 5, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 16);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 17, 73, '3', 5, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 17);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 18, 74, '1', 6, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 18);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 19, 45, '2', 6, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 19);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 20, 73, '3', 6, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 20);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 21, 73, '1', 7, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 21);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 22, 45, '2', 7, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 22);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 23, 74, '3', 7, 'Prusikova', 2434
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 23);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 24, 46, '5', 0, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 24);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 25, 74, '6', 0, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 25);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 26, 74, '4', 1, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 26);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 27, 45, '5', 1, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 27);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 28, 74, '6', 1, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 28);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 29, 74, '4', 2, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 29);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 30, 45, '5', 2, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 30);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 31, 73, '6', 2, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 31);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 32, 73, '4', 3, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 32);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 33, 46, '5', 3, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 33);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 34, 73, '6', 3, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 34);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 35, 74, '4', 4, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 35);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 36, 46, '5', 4, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 36);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 37, 73, '6', 4, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 37);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 38, 73, '4', 5, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 38);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 39, 45, '5', 5, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 39);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 40, 74, '6', 5, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 40);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 41, 74, '4', 6, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 41);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 42, 46, '5', 6, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 42);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 43, 74, '6', 6, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 43);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 44, 74, '4', 7, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 44);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 45, 46, '5', 7, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 45);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 46, 74, '6', 7, 'Prusikova', 2433
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 46);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 47, 74, '7', 0, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 47);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 48, 45, '9', 0, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 48);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 49, 79, '10', 0, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 49);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 50, 74, '7', 1, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 50);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 51, 44, '8', 1, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 51);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 52, 45, '9', 1, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 52);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 53, 79, '10', 1, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 53);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 54, 77, '11', 1, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 54);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 55, 72, '7', 2, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 55);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 56, 45, '8', 2, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 56);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 57, 45, '9', 2, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 57);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 58, 79, '10', 2, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 58);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 59, 77, '11', 2, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 59);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 60, 74, '7', 3, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 60);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 61, 44, '8', 3, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 61);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 62, 45, '9', 3, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 62);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 63, 79, '10', 3, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 63);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 64, 77, '11', 3, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 64);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 65, 74, '7', 4, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 65);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 66, 44, '8', 4, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 66);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 67, 45, '9', 4, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 67);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 68, 79, '10', 4, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 68);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 69, 77, '11', 4, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 69);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 70, 74, '7', 5, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 70);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 71, 45, '8', 5, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 71);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 72, 46, '9', 5, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 72);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 73, 79, '10', 5, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 73);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 74, 77, '11', 5, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 74);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 75, 74, '7', 6, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 75);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 76, 45, '8', 6, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 76);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 77, 46, '9', 6, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 77);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 78, 78, '10', 6, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 78);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 79, 77, '11', 6, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 79);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 80, 74, '7', 7, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 80);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 81, 45, '8', 7, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 81);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 82, 46, '9', 7, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 82);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 83, 79, '10', 7, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 83);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 84, 77, '11', 7, 'Precechtelova', 2432
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 84);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 85, 74, '12', 0, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 85);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 86, 45, '13', 0, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 86);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 87, 33, '14', 0, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 87);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 88, 74, '12', 1, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 88);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 89, 46, '13', 1, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 89);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 90, 74, '14', 1, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 90);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 91, 74, '12', 2, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 91);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 92, 46, '13', 2, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 92);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 93, 74, '14', 2, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 93);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 94, 74, '12', 3, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 94);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 95, 46, '13', 3, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 95);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 96, 73, '14', 3, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 96);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 97, 73, '12', 4, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 97);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 98, 46, '13', 4, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 98);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 99, 73, '14', 4, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 99);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 100, 73, '12', 5, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 100);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 101, 45, '13', 5, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 101);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 102, 73, '14', 5, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 102);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 103, 74, '12', 6, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 103);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 104, 46, '13', 6, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 104);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 105, 74, '14', 6, 'Precechtelova', 2431
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 105);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 106, 33, '15', 0, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 106);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 107, 45, '16', 0, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 107);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 108, 73, '17', 0, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 108);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 109, 73, '15', 1, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 109);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 110, 46, '16', 1, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 110);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 111, 74, '17', 1, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 111);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 112, 73, '15', 2, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 112);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 113, 46, '16', 2, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 113);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 114, 73, '17', 2, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 114);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 115, 73, '15', 3, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 115);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 116, 46, '16', 3, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 116);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 117, 74, '17', 3, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 117);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 118, 74, '15', 4, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 118);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 119, 49, '16', 4, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 119);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 120, 74, '17', 4, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 120);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 121, 74, '15', 5, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 121);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 122, 46, '16', 5, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 122);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 123, 74, '17', 5, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 123);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 124, 74, '15', 6, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 124);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 125, 46, '16', 6, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 125);

INSERT INTO apartment (id, vote_value, branch, flor, street, address_number)
SELECT 126, 74, '17', 6, 'Precechtelova', 2430
WHERE NOT EXISTS (SELECT 1 FROM apartment WHERE id = 126);
