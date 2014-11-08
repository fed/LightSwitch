#!flask/bin/python
from flask import Flask, jsonify

app = Flask(__name__)

response = {
    'on': 1,
    'message': u'Status changed!',
}

@app.route('/lights/', methods=['GET'])
def get_tasks():
    return jsonify({'response': response})

if __name__ == '__main__':
    app.run(debug=True)