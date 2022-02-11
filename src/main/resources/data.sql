-- This initial dataset is automatically loaded by String Data because we are using H2

insert into STORE (NAME, ADDRESS, CITY) values ('GAMMA Utrecht-West', 'Steenovenweg 2', 'Utrecht');
insert into STORE (NAME, ADDRESS, CITY) values ('GAMMA Amersfoort', 'Amsterdamseweg 33', 'Amersfoort');
insert into STORE (NAME, ADDRESS, CITY) values ('GAMMA Hilversum', 'Verlengde Zuiderloswal 30', 'Hilversum');

insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('TOOLS', 'Makita', 'boormachine DF457DWE');
insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('TOOLS', 'BLACK+DECKER', 'accuboormachine BDC718AS2O-QW + 80-delige accessoireset');
insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('TOOLS', 'Bosch ', 'accuboormachine UniversalDrill 18 incl accu');

insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('PAINT', 'Flexa', 'Creations muurverf early dew extra mat 1 liter');
insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('PAINT', 'Histor', 'Perfect Finish grondverf 7000 wit 2,5 liter');
insert into PRODUCT (PRODUCT_TYPE, BRAND, DESCRIPTION) values ('PAINT', 'Sigma', 'primer exterieur grijs 750 ml');

insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (1, 1, 14);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (1, 2, 21);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (1, 3, 7);

insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (2, 1, 5);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (2, 2, 31);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (2, 3, 17);

insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (3, 1, 25);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (3, 2, 0);
insert into STOCK (STORE_ID, PRODUCT_ID, ITEMS_IN_STOCK) values (3, 3, 9);

insert into RESERVATION (STORE_ID, PRODUCT_ID, RESERVATION_DATE_TIME, ITEMS_RESERVED, STATUS) values (1, 1, CURRENT_TIMESTAMP(), 3, 'OPEN');
insert into RESERVATION (STORE_ID, PRODUCT_ID, RESERVATION_DATE_TIME, ITEMS_RESERVED, STATUS) values (1, 1, CURRENT_TIMESTAMP(), 2, 'OPEN');
insert into RESERVATION (STORE_ID, PRODUCT_ID, RESERVATION_DATE_TIME, ITEMS_RESERVED, STATUS) values (1, 2, CURRENT_TIMESTAMP(), 10, 'OPEN');
