from flask import *
from database import *
emergency=Blueprint('emergency',__name__)

@emergency.route('/emergencyhome')
def emergencyhome():

	return render_template('emergency.html')


@emergency.route('/emergency_changepassword',methods=['get','post'])
def emergency_changepassword():

	if "submit" in request.form:
		g=request.form['uname']
		l=request.form['password']
		k=request.form['newpass']
		q="update login set password='%s' where user_name='%s'"%(k,g)
		update(q)

	return render_template('emergency_changepassword.html')	

@emergency.route('/viewforemergency_location',methods=['get','post'])
def viewforemergency_location():
	data={}

	k="select * from emergencies"
	data['view']=select(k)

	return render_template('viewforemergency_location.html',data=data)
