# Итоговый проект | Фреймворк Spring и работа с REST API

В рамках данного проекта реализован стек приложения для бронирования номеров отелей.
Приложение состоит из 7 компонентов

- __bd__ - Персистентное хранилище на Postgres
- __auth__ - KeyCloak сервис авторизации
- __gateway__ - Гейтвей и точка входа в микросервисы 
- __discovery__ - Сервис обнаружения сервисов ([http://localhost:8008](http://localhost:8008/)/)
- __tracer__ - Сервис трассировки запросов ([http://localhost:9411](http://localhost:9411/)/)
- __booking__ -  API для бронирования комнат ([http://localhost:8083](http://localhost:8083/)/)
- __hotels__ - API для работы с отелями ([http://localhost:808](http://localhost:8082/)/)

## Установка и запуск

для запуска всех модулей потребуется только Docker
> docker compose up -d

## Типичный flow использования

Перед обращением в ендпоинтам, нужно авторизоваться, либо с помощью веб-интерфейса, либо консольным клиентом

```
curl -X POST \
    http://auth:8080/realms/Booker/protocol/openid-connect/token \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -d username=test \
    -d password=222 \
    -d grant_type=password \
    -d client_id=gateway \
    -d client_secret=33zDXKgvosS93VY1v5WwzT7lYDUisMIE
```

Для удобства использования, `access_token` ответа сохраняется в env переменной 

> export token={token}

Для старта бронирования можно выбрать отель вручную, или воспользоваться рекомендациями. 

```
curl -i -H "Authorization: Bearer $token" \ 
http://localhost:8000/hotels-service/api/rooms/recommended
```

Выбрав даты, отель и комнату - создается бронирование

```
curl -i -X POST -H "Content-type: application/json" -H "Authorization: Bearer $token" \
-d '{"date_from": "2024-12-12", "date_to": "2024-12-20", "room_id": "8058eea8-de4d-4708-9e03-1fe4846edb25"}' \
http://localhost:8000/booking-service/api/bookings
```

После создания, бронь попадает в состояние ожидания. 
Ее можно подтвердить 

```
curl -i -X POST -H "Content-type: application/json" -H "Authorization: Bearer $token" \
http://localhost:8000/booking-service/api/bookings/b6146179-d66a-49d6-a348-15907107fb16/confirm
```

или отменить
```
curl -i -X POST -H "Content-type: application/json" -H "Authorization: Bearer $token" \
http://localhost:8000/booking-service/api/bookings/72eabe09-7cc1-40a9-83d7-9f98a1a06c79/cancel
```


## Дополнительные API


Просмотр всех бронирований

```
curl -i -H "Authorization: Bearer $token" \ 
http://localhost:8000/booking-service/api/bookings    
```

Просмотр бронировани по id
```
curl -i -H "Authorization: Bearer $token" \
http://localhost:8000/booking-service/api/bookings/77f483e8-cc8f-48b0-9753-f0767e31fddb
```

Просмотр статистики букирования
```
curl -i -H "Authorization: Bearer $token" \
http://localhost:8000/booking-service/api/statistics/rooms
```

Создание отеля 
```
curl -i -X POST -H "Content-type: application/json" -H "Authorization: Bearer $token" -d '{"name": "foo", "address": "we"}' \
http://localhost:8000/hotels-service/api/hotels
```
Удаление отеля 
```
curl -X DELETE -H "Content-type: application/json" -H "Authorization: Bearer $token" -i http://localhost:8000/hotels-service/api/hotels/ee345d30-e3e4-41e3-b204-460d52da7c8c
```
Создание комнаты в отеле
```
curl -i -X POST -H "Content-type: application/json" -H "Authorization: Bearer $token" -d '{"number": "a88", "available": true}' \
http://localhost:8000/hotels-service/api/hotels/82540a6f-acb0-4169-9b32-75af54e0ad7e/rooms
```
Удаление комнаты отеля
```
curl -X DELETE -H "Content-type: application/json" -H "Authorization: Bearer $token" -i http://localhost:8000/hotels-service/api/hotels/ee345d30-e3e4-41e3-b204-460d52da7c8c/rooms/8804ef71-0e4e-4d18-90e0-aa0b0b99705b
```
## Документация

По прямым адресам сервисов доступна Swagger документация 

> [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)
> [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

## Трассировка

Zipkin сервис для трассировки запрос доступе по прямому url

> [http://localhost:8082/swagger-ui/index.html](http://localhost:9411/zipkin/)
