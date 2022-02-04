# SpringbootMultiDatasourceAccess

Connecting Multiple DataSources / Databases with Spring Boot Application with MyBatis as Persistenec API

# Application Work Flow

Client Request an /Endpoint   ---->   controller catches the request calls particular service method , passing datasourceName and relevant parameters ----> Service get object of SqlSessionTemplate with configured mapper and datasources from DataSourceConfig class based on datasourceName and then extracts appropriate mappper repo calls the Repo-----> Repo interacts with particular table of a database and returns result which is subsequentially returned to client.

