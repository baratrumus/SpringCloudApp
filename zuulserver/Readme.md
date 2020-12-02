### Spring Cloud Zuul Server

##### Сервис маршрутизации

При обращении по адресу zuul'a http://localhost:8090/convert/ перенаправляет на
Soap сервис http://127.0.0.1:8084/soap_ws

При обращении по адресу zuul'a http://localhost:8090/course/**/ перенаправляет на
REST сервис http://127.0.0.1:8082

