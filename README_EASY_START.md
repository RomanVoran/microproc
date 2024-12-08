### Ссылки
[Распиновка](https://randomnerdtutorials.com/esp8266-pinout-reference-gpios/)
[Работа через PyMark](https://www.youtube.com/watch?v=YOeV14SESls&ab_channel=DonskyTech)
[Репозиторий с кодом](https://github.com/RomanVoran/microproc)

### Заметки
- Пин для мигания диодом - D4
- перед установкой соединения убедиться что

### Установка микропитона на плату

- Установка esptool
	`pip install esptool`, если уже установлен тогда`pip install esptool --upgrade`
- Устанавливаем ampy
	`pip install ampy`, если уже установлен то обновляем по примеру сверху.
- Устанавливаем драйвер **CH340**. Можно скачать по любой ссылке, главное чтобы установился. Например по [ссылке](https://wiki.amperka.ru/_media/articles:driver-ch340:ch340ser-windows.zip).
- Качаем микропитон по [ссылке](https://micropython.org/download/ESP8266_GENERIC/)
	Именно `*.bin` файл!
- Можно воспользоваться скриптом из репозитория [microPythonIsnall/install.bat](https://github.com/RomanVoran/microproc/blob/main/microPythonIsnall/install.bat)
- Или можно сделать всё руками:
	- `esptool --port COM9 erase_flash` - чистим флэш память перед установкой микропитона. `COM9` - порт куда подключена плата.
	- `esptool --port COM9 --baud 115200 write_flash --flash_size=detect 0 fw.bin` - установка микропитона на камень. 
		- `fw.bin` - название скачанного  `*.bin` файла микропитона
		- `115200` - частота записи данных
- Файл [main.py](https://github.com/RomanVoran/microproc/blob/main/microPythonIsnall/main.py) - скрипт для мигания диодом для проверки работы контроллера и проверки корректного соединения. Пусть лежит.
- Батник [uppload.bat](https://github.com/RomanVoran/microproc/blob/main/microPythonIsnall/upload.bat) - батник для загрузки [main.py](https://github.com/RomanVoran/microproc/blob/main/microPythonIsnall/main.py) файла. 
- Можно сделать всё руками через командную строку: `ampy --port COM9 put main.py`

### Работа через VsCode и PyMark
[Работа через PyMark](https://www.youtube.com/watch?v=YOeV14SESls&ab_channel=DonskyTech)
- Качаем VS Code с официального [сайта](https://code.visualstudio.com/download).
- В VS code заходим в раздел расширений для IDE и качаем PyMark.
	![](/docs/Pasted%20image%2020241208235701.png)
- Создаём новый проект.
- В main.py файле размещаем свой код.
- Выбираем плату из списка и подключаемся к ней нажатием на молнию.
- Загружаем на плату файлы проекта:
	![](/docs/Pasted%20image%2020241209000451.png)



