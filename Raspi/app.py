#!flask/bin/python
from flask import Flask, jsonify
# import lights

app = Flask(__name__)

# Lights start up being off
on = False

# Toggle the current state
@app.route('/lights/toggle', methods=['GET'])
def toggle():
	global on

	# Toggle the lights status
	# if on:
	# 	lights.off()
	# else:
	# 	lights.on()

	on = not on

	response = {
		'on': on
	}

	return jsonify(response)


# Return the current state
@app.route('/lights/status', methods=['GET'])
def status():
	global on

	response = {
		'on': on
	}

	return jsonify(response)


if __name__ == '__main__':
	app.run(host= '0.0.0.0')