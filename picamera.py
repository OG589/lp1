from picamera import PiCamera
from time import sleep

camera = PiCamera()

try:
    sleep(2)
    camera.capture('/home/pi/Desktop/image.jpg')
    print("Image captured successfully!")
finally:
    camera.close()