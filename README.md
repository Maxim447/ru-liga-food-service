# ru-liga-food-service
Программа эмитирует поведение взаимодействия ресторанов, курьеров и покупателей. Для работы программы используется 4 микросервиса delivery-service, kitchen-service, notification-service и order-service.
# order-service
Создание заказа происходит по URL "http://127.0.0.1:8080/order-service/api/v1/orders/", после запуска 4 микросервисов. Этот URL создаёт заказ и возвращает нам ссылку для оплаты, после оплаты заказа (http://127.0.0.1:8080/order-service/api/v1/orders/{id}/pay) заказ передаётся на кухню.
# kitchen-service
На кухне идет обработка заказа, есть возможность принять (http://127.0.0.1:8080/kitchen-service/api/v1/kitchen/{id}/accept) или отклоненить заказ (http://127.0.0.1:8080/kitchen-service/api/v1/kitchen/{id}/decline), заказ также может отмениться атоматически(если расстояние между рестораном и заказчиком больше 10 километров), если же заказ принят, то дальше после готовки кухни (http://127.0.0.1:8080/kitchen-service/api/v1/kitchen/{id}/ready) начинает работать сервис курьеров.
# delivery-service
После того как заказ приготовился начинается поиск свободного курьера, который находится ближе всего к ресторану. У курьера будет выбор принять заказ (http://127.0.0.1:8080/delivery-service/api/v1/delivery/{courierId}/take/{id}). 
# Notification-service
Данный сервер создает имитацию push-уведомлений

# Как запустить проект?

## 1. liquibase

Для создания таблиц и заполнения их тестовыми данными необходимо запустить плагин liquibase:update
## 2. RabbitMQ

Для корректной работы сервисов необходим контейнер RabbitMQ:

docker run -lt --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
## 3. Сервисы аутентификации

Для работы аутентификации необходимо запустить сначала Auth-Service, а затем CloudGateway
## 4. Основные сервисы

Для корректной работы RabbitMQ сначала необходимо запустить notification-service, delivery-service, kitchen-service и order-service

# Авторизация

username: user

password: password

## delivery-service

http://127.0.0.1:8080/delivery-service/
## kitchen-service

http://127.0.0.1:8080/kitchen-service/
## order-service

http://127.0.0.1:8080/order-service/
