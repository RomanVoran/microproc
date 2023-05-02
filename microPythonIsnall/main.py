import machine
from time import sleep
led = machine.Pin(2, machine.Pin.OUT)

while True:
    led.on()
    sleep(0.5)
    led.off()
    sleep(0.5)