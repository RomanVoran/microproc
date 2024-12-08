pip install esptool
pip install adafruit-ampy
esptool --port COM9 erase_flash
esptool --port COM9 --baud 115200 write_flash --flash_size=detect 0 fw.bin
pause