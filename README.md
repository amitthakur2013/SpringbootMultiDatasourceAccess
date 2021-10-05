# SpringbootMultiDatasourceAccess

Connecting Multiple DataSources / Databases with Spring Boot Application with MyBatis as Persistenec API


Client Request an /Enpdpoint   ---->   controller catches the request calls particular service method , passing datasourceName ----> Service get object of SqlSessionTemplate with configured mapper and datasources from DataSourceConfig class and then calls appropriate Repo-----> Repo interacts with particular table of a database and returns result which is subsequentially shown to client.

