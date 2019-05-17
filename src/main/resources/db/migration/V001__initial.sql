CREATE TABLE transaction_type(
	cd_transaction_type BIGINT(9) primary key auto_increment,
	ds_transactional_type varchar(20) not null
)engine=InnoDB default charset=utf8;

CREATE TABLE reg_telefonico (
	cd_reg_telefonico BIGINT(9) primary key auto_increment,
	nr_telefone_origem BIGINT(13) not null,
	nr_telefone_destino BIGINT(13) not null,
	tp_transaction BIGINT(9) not null,
	FOREIGN KEY (tp_transaction) references transaction_type(cd_transaction_type)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

insert into transaction_type(ds_transactional_type) values ("SERIALIZABLE");
insert into transaction_type(ds_transactional_type) values ("REPEATABLE_READ");
insert into transaction_type(ds_transactional_type) values ("READ_UNCOMMITED");
insert into transaction_type(ds_transactional_type) values ("READ_COMMITTED");
