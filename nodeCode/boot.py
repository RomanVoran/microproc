# boot.py -- run on boot-up
# Complete project details at https://RandomNerdTutorials.com

print('start!')  
import socket

from machine import Pin
import network

import esp
esp.osdebug(None)

import gc
gc.collect()

ssid = 'Kalimdor-2,4'
password = 'katarakta'

station = network.WLAN(network.STA_IF)

station.active(True)
station.connect(ssid, password)

print('Start connection')

while station.isconnected() == False:
  pass

print('Connection successful')
print(station.ifconfig())

led = Pin(2, Pin.OUT)
led.value(1)