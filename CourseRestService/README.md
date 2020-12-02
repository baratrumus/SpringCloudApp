
### Course Rest Service

endpoint GET http://localhost:8082/

Принимает на вход запрос с двумя параметрами, представляющими коды валют (RUB, USD, EUR), возвращает курс конвертации из валюты 1 в валюту 2 (справочник с курсами захардкожен в кофигурацию, получаемую данным сервисом из ConfigServer’а) 

Soap Conversion Service запрашивает отсюда курс конвертации

Регистрацию в Eureka можно проверить в Eureka console http://localhost:8761/

По адресу этого сервиса http://localhost:8082/show_ids реализован DiscoveryClient и можно увидеть все зарегистрированные в Eureka сервисы.


