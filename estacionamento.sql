create database estacionamento;

use estacionamento;

create table carro(
placa char(7) not null,
cor   varchar (20),
descricao varchar (100),
primary key (placa));

desc carro;

select * from carro;