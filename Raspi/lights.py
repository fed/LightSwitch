# The newest version of Raspbian has the RPi.GPIO library pre-installed
import RPi.GPIO as GPIO

def on():
	"Turn on GPIO #27 in order to active the light"
	GPIO.output(27, GPIO.HIGH)

def off():
	"Turn off GPIO #27 in order to deactivate the light"
	GPIO.output(27, GPIO.LOW)

def cleanup():
	"This method MUST be called when the program exits in order to return the GPIO to its default state"
	GPIO.cleanup()
