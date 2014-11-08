#!flask/bin/python
from flask import Flask, jsonify

app = Flask(__name__)
on = 1

@app.route('/lights/toggle', methods=['GET'])
def toggle():
    global on

    on = not on

    response = {
        'on': on
    }

    return jsonify(response)

@app.route('/lights/status', methods=['GET'])
def status():
    global on

    response = {
        'on': on
    }

    return jsonify(response)

if __name__ == '__main__':
    app.run(host= '0.0.0.0')