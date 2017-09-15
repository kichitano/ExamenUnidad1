create database DB_Recetas
go
use DB_Recetas
go

create table Tb_Recetas(
codigo_receta int identity primary key,
nombre_receta varchar(100) not null,
descripcion_receta varchar(150) not null,
preparacion_receta varchar(max) not null,
foto_receta nvarchar(max) null
)
go

create table Tb_Ingredientes(
codigo_ingrediente int identity primary key,
codigo_receta int,
detalle_ingrediente varchar(100) not null
)

alter table Tb_Ingredientes
 add constraint FK_codigo_receta
 foreign key (codigo_receta)
 references Tb_Recetas (codigo_receta)
 go
