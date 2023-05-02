pip install esptool
pip install adafruit-ampy
esptool.py --port COM7 erase_flash
esptool.py --port COM7 --baud 115200 write_flash --flash_size=detect 0 fw.bin
pause