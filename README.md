
### Порядок запуска:
#####   - Eureka
#####   - Config Server
#####   - Rest Course Service
#####   - Soap Conversion Service
#####   - Zuul Server



### Описание работы

Веб-сервис-1 - REST: принимает на вход запрос с двумя параметрами, представляющими коды валют (RUB, USD, EUR), возвращает курс конвертации из валюты 1 в валюту 2 (возможны любые комбинации пар, указанных выше кодов, справочник с курсами захардкожен в кофигурацию, получаемую данным сервисом из ConfigServer’а)

Веб-сервис-2 – SOAP: принимает на вход документ вида:

<conversionsList>
    <conversion>
      <from>RUB</from>
      <to>USD</to>
      <amount>1000</amount>
    </conversion>

    <conversion>
      <from>RUB</from>
      <to>EUR</to>
      <amount>1000</amount>
    </conversion>
</conversionsLIst>

Вложенных элементов conversion должно быть строго не меньше 1 и не более 10-ти, проверка должна обеспечиваться на уровне схемы, описанной в WSDL.

Веб-сервис 2 получает документ указанного вида от пользователя, разбивает его на отдельные записи по <conversion>, обращается к первому сервису для получения курса конвертации, обогащает каждую запись тегом <convertedAmount> со значением = amount * курс конвертации, полученные записи пишет в очередь любого брокера, работа с которым возможна через JMS (допустим, ActiveMQ).

То есть, для приведенного выше входящего запроса в очередь на выход попадает два сообщения:

    <conversion>
      <from>RUB</from>
      <to>USD</to>
      <amount>1000</amount>
      <convertedAmount>14.29</convertedAmount>
    </conversion>
и
    <conversion>
      <from>RUB</from>
      <to>EUR</to>
      <amount>1000</amount>
      <convertedAmount>12.5</convertedAmount>
    </conversion>


