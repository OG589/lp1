from flask import Flask, request, jsonify, render_template

app = Flask(__name__)
data_store = {}

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/publish', methods=['POST'])
def publish():
    content = request.json
    publisher_id, data = content.get('publisher_id'), content.get('data')
    if not publisher_id or not data:
        return jsonify({'error': 'Invalid input'}), 400
    data_store[publisher_id] = data
    return jsonify({'status': 'success'}), 200

@app.route('/subscribe/<publisher_id>', methods=['GET'])
def subscribe(publisher_id):
    data = data_store.get(publisher_id)
    return jsonify({'data': data}) if data else jsonify({'error': 'No data found'}), 404

if __name__ == '__main__':
    app.run(debug=True)


# for running
# pip install Flask
# /your-project-directory
#     /templates
#         index.html
#     app.py
# python app.py
#  * Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)


# app.run(debug=True, host='0.0.0.0', port=8080) if you encounter any error (optional)