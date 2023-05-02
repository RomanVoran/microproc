# Complete project details at https://RandomNerdTutorials.com
import json

print("stat main.py!@#!@$")


def web_page():
    if led.value() == 1:
        gpio_state = "ON"
    else:
        gpio_state = "OFF"

    html = """<html><head> <title>ESP Web Server</title> <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="data:,"> <style>html{font-family: Helvetica; display:inline-block; margin: 0px auto; text-align: center;}
  h1{color: #0F3376; padding: 2vh;}p{font-size: 1.5rem;}.button{display: inline-block; background-color: #e7bd3b; border: none; 
  border-radius: 4px; color: white; padding: 16px 40px; text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}
  .button2{background-color: #4286f4;}</style></head><body> <h1>ESP Web Server</h1> 
  <p>GPIO state: <strong>""" + gpio_state + """</strong></p><p><a href="/?led=on"><button class="button">ON</button></a></p>
  <p><a href="/?led=off"><button class="button button2">OFF</button></a></p></body></html>"""
    return html


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('', 80))
s.listen(5)

if led.value() == 1:
    led_status = "LED OFF"
else:
    led_status = "LED ON"

while True:
    conn, addr = s.accept()
    print('Got a connection from %s' % str(addr))
    request = conn.recv(1024)
    request = str(request)
    print('Content = %s' % request)
    request_led_on = request.find('/?led=on')
    request_led_off = request.find('/?led=off')
    request_led_status = request.find('/?status')
    if request_led_on == 6:
        led_status = 'LED OFF'
        led.value(1)
    if request_led_off == 6:
        led_status = 'LED ON'
        led.value(0)
    print(led_status)
    response = web_page()
    data = {"ledStatus": led_status, "responseStatus": "OK"}
    conn.send('HTTP/1.1 200 {}\n'.format(json.dumps(data)))
    conn.send('Content-Type: text/html\n')
    conn.send('Connection: close\n\n')
    conn.sendall(response)
    conn.close()
