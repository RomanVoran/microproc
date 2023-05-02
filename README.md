# Microproc
Interaction between the Android application and the microcontroller via Wi-Fi connection

## Structure
- microPythonIsnall - folder with micropython installation files and a script for blinking the LED
- nodeCode - folder with MicroPython project
- Nodemcuv3 - folder with Android applictaion project for connect to PyBoard

## Micropython instalation
1) Download and install the USB driver for your pyBoard. CH340G for my Node MCU v3.
2) Run install.bat to clear board memory and install micropython.
3) If the installation process was successful, run upload.bat. This script will upload a small python main.py code to the board to flash the LED.
4) After a successful boot, for the LED to flash, you must press the RST button on the pyboard to reset the processor.

## Upload project

Upload project from nodeCode folder to your board using next frameworks:
- [VS code Pymark extension](https://randomnerdtutorials.com/micropython-esp32-esp8266-vs-code-pymakr/)
- [PyCharm MicroPython Extension](https://plugins.jetbrains.com/plugin/9777-micropython)
- [EsPy IDE for ESP8266](https://github.com/jungervin/EsPy)
- [uPyCraft](https://github.com/DFRobot/uPyCraft), instalation [instruction](https://randomnerdtutorials.com/install-upycraft-ide-windows-pc-instructions/)
