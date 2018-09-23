 create table acct_tbl (
       id bigint not null auto_increment,
        acc_name varchar(20) not null unique ,
        pass varchar(20) not null,
        money bigint,
        up_acct bit,
        primary key (id)
    ) engine=MyISAM;

create table garden_tbl (
       id bigint not null auto_increment,
        check_pole bit,
        pole integer not null,
        sbor datetime,
        price_shell bigint,
        acct_id bigint,
        primary key (id),
        foreign key (acct_id)  references acct_tbl (id)
    ) engine=MyISAM;


