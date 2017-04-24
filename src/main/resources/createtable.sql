create table patent(
 id VARCHAR(40) NOT NULL,
 public_date DATE,
 apply_no VARCHAR(40),
 apply_date DATE,
 name VARCHAR(200) NOT NULL,
 primary_class_no TEXT,
 class_no TEXT,
 applier TEXT,
 author TEXT,
 address VARCHAR(200),
 agency VARCHAR(200),
 agent VARCHAR(100),
 abstract TEXT,
 protect_iterm mediumtext,
 path VARCHAR(100),
 page_num SMALLINT(3),
 code VARCHAR(20),
 international_apply VARCHAR(100),
 international_public VARCHAR(100),
 import_date DATE,
 PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

