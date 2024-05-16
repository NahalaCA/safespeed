from flask import *
from database import *
carmodule=Blueprint('carmodule',__name__)
@carmodule.route('/carhome')
def carhome():

	return render_template('carhome.html')



# @carmodule.route('/car_changepassword')
# def car_changepassword():
# 	return render_template("car_changepassword.html")


@carmodule.route('/car_changepassword',methods=['get','post'])
def car_changepassword():
	
	if "submit" in request.form:
		b=request.form['uname']
		c=request.form['password']
		v=request.form['password']
		q="update login set password='%s' where user_name='%s'"%(v,c)
		update(q)

	return render_template('car_changepassword.html')



	q="select * from register"
	data['view']=select(q)
		

@carmodule.route('/location_of_emergencies',methods=['get','post'])
def location_of_emergencies():
	data={}
	if "submit" in request.form:
		e=request.form['emergencies']
		f=request.form['latitude']
		c=request.form['longitude']
		w="insert into emergencies values(null,'%s','%s',curdate(),'%s','%s','pending')"%(session['user_id'],e,f,c)
		insert(w)


	q="select * from emergencies"
	data['view']=select(q)

	if 'action' in request.args:
		action=request.args['action']
		o=request.args['eid']
	else:
		action=None


	if action=='delete':
		k="delete from emergencies where emergency_id='%s'"%(o)
		print(k)
		delete(k)


	if action=='update':
		qp="select * from emergencies where emergency_id='%s'"%(o)
		data['up']=select(qp)
	if 'update' in request.form:

		r=request.form['emergencies']
		l=request.form['latitude']
		p=request.form['longitude']
		q="update emergencies set emergency='%s', latitude='%s', longitude='%s'  where emergency_id='%s'"%(r,l,p,o)
		update(q)

	s="select * from emergencies"
	data['view']=select(s)



	return render_template('location_of_emergencies.html',data=data)


	
		

	

