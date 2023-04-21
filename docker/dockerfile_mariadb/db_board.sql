create table tbl_board (
    seqno int not null auto_increment,
    writer varchar(50) not null,
    title varchar(2000) not null,
    regdate timestamp not null default current_timestamp(),
    hitno int null,
    content text not null,
    primary key(seqno)
);

INSERT INTO tbl_board(writer, title, content) VALUES ('John', 'First post', 'Hello, world!');