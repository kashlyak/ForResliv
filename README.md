Добрый день. 
Для запуска приложения требуется склонировать репозиторий и запустить. 

В папке Tourist-Program, проект с использованием HAL Explorer. В этой же папке на другой ветке написаны endpoint.
В папке rest_web_service проект, написанный в декабре.

В качестве базы данных использовал MySQL.
Конфигурационный файл для подключения базы данных есть в resources.

Данные для бота:
bot.username=CItyReference_bot; 
bot.token=1470746078:AAGVuUhGjF06GGOKJxPehOXqi-mEOR4bo6M

В боте реализован ответ исключительно на названия городов.
Если такой присутствует в базе - последует вывод описания.
Если город отсутствует - выводится текстовое сообщение на повторный ввод.
Также присутствует кнопка, которая выводит все известные города для бота.
