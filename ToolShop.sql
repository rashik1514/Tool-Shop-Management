load DATA LOCAL INFILE '/github/ensf409project/items.txt' INTO TABLE Items
FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n';
load DATA LOCAL INFILE '/github/ensf409project/suppliers.txt' INTO TABLE Suppliers
FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'