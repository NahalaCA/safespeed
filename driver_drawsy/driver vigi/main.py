from flask import *
from admin import admin
from public import public
from database import database
from carmodule import carmodule
from emergency import emergency
from api import api
app=Flask(__name__)

app.secret_key="abc"
app.register_blueprint(public)
app.register_blueprint(admin)
app.register_blueprint(carmodule)
app.register_blueprint(emergency,url_prefix='/emergency')
app.register_blueprint(api, url_prefix='/api')

app.run(debug=True,port=5255,host="0.0.0.0")
